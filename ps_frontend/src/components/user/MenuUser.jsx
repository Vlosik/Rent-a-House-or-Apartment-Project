import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './MenuUser.css';
import { Link } from "react-router-dom";
import { IoArrowBack, IoArrowForward } from "react-icons/io5";

class MenuUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            properties: [],
            currentPage: 1,
            propertiesPerPage: 5,
            loading: true,
            error: null,
        };
    }

    componentDidMount() {
        this.fetchProperties();
    }

    fetchProperties = () => {
        const searchQuery = localStorage.getItem('searchQuery');

        if (searchQuery && searchQuery.trim() !== '') {
            const searchQueryObject = { title: searchQuery };
            axiosInstance.post("property/search",searchQueryObject)
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

    render() {
        const { properties, currentPage, propertiesPerPage, loading, error } = this.state;
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
                        {currentProperties.map(property => (
                            <Link to={`/property/${property.id}`} className="property-link" key={property.id}>
                                <div className="property-card">
                                    <img
                                        src={`data:image/jpeg;base64,${property.image}`}
                                        alt="Property"
                                        className="property-image"
                                    />
                                    <div className="property-details">
                                        <div className="property-header">
                                            <h2 className="property-title">{property.title}</h2>
                                            <span className="property-type">{property.type}</span>
                                        </div>
                                        <p className="property-description">{property.description}</p>
                                        <div className="property-footer">
                                            <span className="property-price">${property.price}</span>
                                        </div>
                                    </div>
                                </div>
                            </Link>
                        ))}
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
