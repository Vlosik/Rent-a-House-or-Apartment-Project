import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './MenuUser.css';
import { Link } from "react-router-dom";
import { IoArrowBack, IoArrowForward } from "react-icons/io5";
import { CiHeart } from "react-icons/ci";
import { FaHeart } from "react-icons/fa";
import history from "../../history";

class MenuUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            properties: [],
            favoriteProperties: [],
            currentPage: 1,
            propertiesPerPage: 5,
            loading: true,
            error: null,
        };
    }

    componentDidMount() {
        this.fetchProperties();
        this.fetchFavoriteProperties();
    }

    fetchProperties = () => {
        const searchQuery = localStorage.getItem('searchQuery');

        if (searchQuery && searchQuery.trim() !== '') {
            const searchQueryObject = { title: searchQuery };
            axiosInstance.post("property/search", searchQueryObject)
                .then(response => {
                    this.setState({ properties: response.data, loading: false });
                })
                .catch(error => {
                    this.setState({ error: error.message, loading: false });
                });
        } else {
            axiosInstance.get("property/findAll")
                .then(response => {
                    this.setState({ properties: response.data, loading: false });
                })
                .catch(error => {
                    this.setState({ error: error.message, loading: false });
                });
        }
    }

    fetchFavoriteProperties = () => {
        const username = localStorage.getItem('usernameUser');
        if (username) {
            axiosInstance.post("/favorite/getFavorite", { username })
                .then(response => {
                    this.setState({ favoriteProperties: response.data });

                })
                .catch(error => {
                    this.setState({ error: error.message });
                });
        }
    }

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

    handlePropertyClick = (property) => {
        localStorage.setItem('selectedProperty', JSON.stringify(property));
    }

    render() {
        const { properties, favoriteProperties, currentPage, propertiesPerPage, loading, error } = this.state;
        const indexOfLastProperty = currentPage * propertiesPerPage;
        const indexOfFirstProperty = indexOfLastProperty - propertiesPerPage;
        const currentProperties = properties.slice(indexOfFirstProperty, indexOfLastProperty);

        if (loading) {
            return <div>Loading...</div>;
        }

        if (error) {
            return <div>Error: {error}</div>;
        }

        return (
            <div className="menu-user-container">
                <div className='property-list'>
                    <h1>Properties</h1>
                    <div className="property-cards">
                        {currentProperties.map(property => {
                            const isFavorite = favoriteProperties.some(fav => fav.title === property.title);
                            console.log(`Property ID: ${property.id}, isFavorite: ${isFavorite}`);
                            return (
                                <div className="property-detail-container" key={property.id}>
                                    <Link
                                        to={`/property`}
                                        className="property-link"
                                        onClick={() => this.handlePropertyClick(property)}
                                    >
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
                                                    {isFavorite ? <FaHeart className="icon"/> : <CiHeart className="icon" />}
                                                    <span className="property-price">${property.price}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </Link>
                                </div>
                            );
                        })}
                    </div>
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
                            disabled={indexOfLastProperty >= properties.length}
                            className="pagination-button"
                        >
                            <IoArrowForward />
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}

export default MenuUser;
