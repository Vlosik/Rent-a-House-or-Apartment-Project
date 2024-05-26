import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './ShowRentals.css';
import NavBarUser from "../NavbarUser";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";
import history from "../../history";

class ShowRentals extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rentals: [],
            loading: true,
            error: null,
            showReviewField: false,
            reviewMessage: '',
            rating: 1,
            selectedRentalId: null,
        };
    }

    componentDidMount() {
        const username = localStorage.getItem('usernameUser');
        if (username) {
            axiosInstance.post("rental/getRentals", { username })
                .then(response => {
                    this.setState({
                        rentals: response.data,
                        loading: false,
                    });
                })
                .catch(error => {
                    console.error('There was an error fetching the rentals data!', error);
                    this.setState({ error: error.message, loading: false });
                });
        }
    }

    toggleReviewField = (rentalId) => {
        this.setState(prevState => ({
            showReviewField: !prevState.showReviewField,
            selectedRentalId: rentalId,
        }));
    }

    handleReviewInputChange = (event) => {
        this.setState({ reviewMessage: event.target.value });
    }

    handleRatingChange = (event) => {
        this.setState({ rating: event.target.value });
    }

    submitReview = () => {
        const {reviewMessage, rating, selectedRentalId} = this.state;
        const username_user = localStorage.getItem('usernameUser');
        const currentRental = this.state.rentals.find(rental => rental.id === selectedRentalId);

        const review = {
            username: username_user,
            propertyTitle: currentRental.property.title,
            message: reviewMessage,
            rating: rating,
        }
        axiosInstance.post("/reviews/insert", review)
            .then(response => {
                console.log(response.data);
                history.push("/profileUser");
                window.location.reload();
            })
            .catch(error => {
                console.log('Review submission error:', error);
            });
    }

    cancelRental = (rentalId) => {
        const username_user = localStorage.getItem('usernameUser');
        const currentRental = this.state.rentals.find(rental => rental.id === rentalId);

        const rent = {
            username_user: username_user,
            property_title: currentRental.property.title,
            startDate: currentRental.startDate,
            endDate: currentRental.endDate,
        };
        console.log(rent);
        axiosInstance({
            method: 'delete',
            url: '/rental/delete',
            data: rent
        }).then(response => {
            console.log(response.data);
            history.push("/profileUser");
            window.location.reload();
        })
            .catch(error => {
                console.log('Review submission error:', error);
            });
    }

    render() {
        const { rentals, showReviewField, reviewMessage, rating } = this.state;
        const currentDate = new Date().toISOString().split('T')[0]; // Get current date in YYYY-MM-DD format
        return (
            <div>
                <NavBarUser />
                <div className="back">
                    <Link to="/profileUser" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className='property-list'>
                    <h1>Rentals</h1>
                    <table>
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Type</th>
                            <th>Location</th>
                            <th>Image</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {rentals.map(rental => (
                            <tr key={rental.id}>
                                <td>{rental.property.title}</td>
                                <td>{rental.property.description}</td>
                                <td>{rental.property.price}</td>
                                <td>{rental.property.type}</td>
                                <td>{rental.property.location}</td>
                                <td>
                                    {rental.property.image && (
                                        <img
                                            src={`data:image/jpeg;base64,${rental.property.image}`}
                                            alt="Property"
                                            width="100"
                                        />
                                    )}
                                </td>
                                <td>{rental.startDate}</td>
                                <td>{rental.endDate}</td>
                                <td>
                                    <div className="action-buttons">
                                        <button onClick={() => this.cancelRental(rental.id)}>Cancel</button>
                                        {new Date(rental.endDate) < new Date(currentDate) && (
                                            <button onClick={() => this.toggleReviewField(rental.id)}>Review</button>
                                        )}
                                    </div>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    {showReviewField && (
                        <div className="review-field">
                            <textarea
                                value={reviewMessage}
                                onChange={this.handleReviewInputChange}
                                placeholder="Write your review..."
                            />
                            <select value={rating} onChange={this.handleRatingChange}>
                                {[...Array(10).keys()].map(num => (
                                    <option key={num + 1} value={num + 1}>{num + 1}</option>
                                ))}
                            </select>
                            <div className="cancel-review-container">
                                <button onClick={this.submitReview}>Submit</button>
                                <button onClick={() => this.setState({ showReviewField: false })}>Close</button>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}

export default ShowRentals;
