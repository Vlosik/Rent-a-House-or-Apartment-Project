import React, { Component } from 'react';
import './Property.css';
import NavbarUser from "../NavbarUser";
import { Link } from "react-router-dom";
import { IoArrowBack, IoArrowForward } from "react-icons/io5";
import { CiHeart } from "react-icons/ci";
import { FaHeart } from "react-icons/fa";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import { parseISO, isSameDay, isBefore, startOfDay } from 'date-fns';
import axiosInstance from "../../axios";
import history from "../../history";
import { FaUser } from "react-icons/fa";

class Property extends Component {
    constructor(props) {
        super(props);
        this.state = {
            property: {},
            startDate: null,
            endDate: null,
            invalidDates: [],
            reviews: [],
            currentPage: 1,
            reviewsPerPage: 2,
            isFavorite: false,
            loading: true,
        };
    }

    componentDidMount() {
        this.fetchProperty();
    }

    fetchProperty = () => {
        const selectedProperty = JSON.parse(localStorage.getItem('selectedProperty'));
        if (!selectedProperty || !selectedProperty.title) {
            console.error("No property title found in local storage");
            return;
        }

        axiosInstance.post("/property/findByTitle", { title: selectedProperty.title })
            .then(propertyResponse => {
                const property = propertyResponse.data;

                axiosInstance.post("/favorite/getFavorite", { username: localStorage.getItem('usernameUser') })
                    .then(favoriteResponse => {
                        const favoriteProperties = favoriteResponse.data;
                        const isFavorite = favoriteProperties.some(fav => fav.title === property.title);

                        this.setState({ property, isFavorite, loading: false }, () => {
                            this.fetchInvalidDates();
                            this.fetchReviews();
                        });
                    })
                    .catch(error => {
                        console.error("Error fetching favorite properties:", error);
                    });
            })
            .catch(error => {
                console.error("Error fetching property details:", error);
            });
    };

    fetchInvalidDates = () => {
        const { property } = this.state;
        axiosInstance.post("rental/getDays", { title: property.title })
            .then(response => {
                const data = response.data;
                const invalidDates = data.map(dateString => startOfDay(parseISO(dateString)));
                this.setState({ invalidDates });
            })
            .catch(error => {
                console.error("Error fetching invalid dates:", error);
            });
    };

    fetchReviews = () => {
        const { property } = this.state;
        axiosInstance.post("reviews/getReviews", { title: property.title })
            .then(response => {
                this.setState({ reviews: response.data });
            })
            .catch(error => {
                console.error("Error fetching reviews:", error);
            });
    };

    handleFavoriteToggle = () => {
        const { property, isFavorite } = this.state;
        const username = localStorage.getItem('usernameUser');
        const fav = {
            user: username,
            propertyTitle: property.title
        }
        console.log(fav);
        if (isFavorite) {
            axiosInstance({
                method: 'delete',
                url: '/favorite/delete',
                data: fav
            }).then(() => {
                this.setState({ isFavorite: false });
            })
                .catch(error => {
                    console.error("Error removing favorite:", error);
                });
        } else {
            axiosInstance.post("/favorite/insert",  fav)
                .then(() => {
                    this.setState({ isFavorite: true });
                })
                .catch(error => {
                    console.error("Error adding favorite:", error);
                });
        }
    };

    onDateChange = (dates) => {
        const [start, end] = dates;
        this.setState({ startDate: start, endDate: end || start });
    };

    tileClassName = ({ date, view }) => {
        const { invalidDates } = this.state;
        if (view === 'month') {
            const today = startOfDay(new Date());
            if (isBefore(date, today)) {
                return 'invalid-date';
            }
            if (invalidDates.some(invalidDate => isSameDay(invalidDate, date))) {
                return 'invalid-date';
            }
        }
        return null;
    };

    tileDisabled = ({ date, view }) => {
        const { invalidDates } = this.state;
        if (view === 'month') {
            const today = startOfDay(new Date());
            if (isBefore(date, today)) {
                return true;
            }
            if (invalidDates.some(invalidDate => isSameDay(invalidDate, date))) {
                return true;
            }
        }
        return false;
    };

    handleRent = () => {
        const { property, startDate, endDate } = this.state;
        const formatISOWithMilliseconds = (date) => {
            return date.toISOString();
        };
        const formattedStartDate = formatISOWithMilliseconds(startDate);
        const formattedEndDate = formatISOWithMilliseconds(endDate);
        const username_user = localStorage.getItem('usernameUser');
        const rentDetails = {
            username_user,
            property_title: property.title,
            startDate: formattedStartDate,
            endDate: formattedEndDate
        };
        alert(`Renting ${property.title} from ${formattedStartDate} to ${formattedEndDate}`);
        axiosInstance.post("/rental/insert", rentDetails)
            .then(response => {
                console.log(response.data);
                history.push("/homeUser");
                window.location.reload();
            })
            .catch(error => {
                console.log('Error:', error);
            });
    };

    handleCancel = () => {
        this.setState({ startDate: null, endDate: null });
    };

    handleNextPage = () => {
        this.setState(prevState => ({
            currentPage: prevState.currentPage + 1
        }));
    }

    handlePrevPage = () => {
        this.setState(prevState => ({
            currentPage: prevState.currentPage - 1
        }));
    }

    renderReviews = () => {
        const { reviews, currentPage, reviewsPerPage } = this.state;
        const indexOfLastReview = currentPage * reviewsPerPage;
        const indexOfFirstReview = indexOfLastReview - reviewsPerPage;
        const currentReviews = reviews.slice(indexOfFirstReview, indexOfLastReview);

        return (
            <div className="reviews-section">
                <h3>Reviews</h3>
                {currentReviews.map(review => (
                    <div key={review.id} className="review">
                        <div className="review-header">
                            <div className="user">
                                <FaUser className="user-icon" />
                                <h4>{review.user.username}</h4>
                            </div>
                            <span className="review-rating">Rating: {review.rating}</span>
                        </div>
                        <p>{review.message}</p>
                    </div>
                ))}
                <div className="pagination">
                    <button
                        onClick={this.handlePrevPage}
                        disabled={currentPage === 1}
                        className="pagination-button"
                    >
                        <IoArrowBack />
                    </button>
                    <button
                        onClick={this.handleNextPage}
                        disabled={indexOfLastReview >= reviews.length}
                        className="pagination-button"
                    >
                        <IoArrowForward />
                    </button>
                </div>
            </div>
        );
    }

    render() {
        const { property, startDate, endDate, isFavorite, loading } = this.state;

        if (loading) {
            return <div>Loading...</div>;
        }

        if (!property || !property.title) {
            return <div>No property selected</div>;
        }

        const calculateCost = (start, end, pricePerDay) => {
            if (!start || !end) return 0;
            const days = (end - start) / (1000 * 60 * 60 * 24);
            return Math.ceil(days) * pricePerDay; // Using Math.ceil to avoid decimal places
        };

        return (
            <div>
                <NavbarUser/>
                <div className="back">
                    <Link to="/homeUser" className="link-box">Back</Link>
                    <IoArrowBack className="icon"/>
                </div>
                <div className="prop-container">
                    <div className="property-detail-container">
                        <div className="property-card">
                            <div className="property-image-container">
                                <img
                                    src={`data:image/jpeg;base64,${property.image}`}
                                    alt="Property"
                                    className="property-image"
                                />
                            </div>
                            <div className="property-details">
                                <div className="property-header">
                                    <h2 className="property-title">{property.title}</h2>
                                    <span className="property-type">{property.type}</span>
                                </div>
                                <p className="property-description">{property.description}</p>
                                <div className="property-location">
                                    <strong>Location:</strong> {property.location}
                                </div>
                                <div className="property-footer">
                                    {isFavorite ? <FaHeart className="icon" onClick={this.handleFavoriteToggle}/> :
                                        <CiHeart className="icon" onClick={this.handleFavoriteToggle}/>}
                                    <span className="property-price">${property.price}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="details-container">
                        <div className="calendar-container">
                            <div className="calendar-and-buttons">
                                <Calendar
                                    selectRange
                                    onChange={this.onDateChange}
                                    tileClassName={this.tileClassName}
                                    tileDisabled={this.tileDisabled}
                                    value={[startDate, endDate]}
                                />
                                <div className="rent-details">
                                    <div className="cost">
                                        <strong>Cost:</strong> ${calculateCost(startDate, endDate, property.price)}
                                    </div>
                                    <button className="rent-button" onClick={this.handleRent}>Rent</button>
                                    <button className="cancel-button" onClick={this.handleCancel}>Cancel</button>
                                </div>
                            </div>
                        </div>
                        <div className="reviews-container">
                            {this.renderReviews()}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Property;
