import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './ShowProperty.css';
import NavBar from "../NavBar";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";

class ShowProperty extends Component {
    constructor(props) {
        super(props);
        this.state = {
            properties: [],
            loading: true,
            error: null,
        };
    }

    componentDidMount() {
        this.fetchProperties();
    }

    fetchProperties = () => {
        axiosInstance.get("property/findAll")
            .then(response => {
                this.setState({ properties: response.data, loading: false });
            })
            .catch(error => {
                this.setState({ error: error.message, loading: false });
            });
    }

    render() {
        const { properties } = this.state;
        return (
            <div>
                <NavBar />
                <div className="back">
                    <Link to="/home" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className='property-list'>
                    <h1>Property List</h1>
                    <table>
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Availability</th>
                            <th>Type</th>
                            <th>Location</th>
                            <th>Image</th>
                            <th>Created At</th>
                        </tr>
                        </thead>
                        <tbody>
                        {properties.map(property => (
                            <tr key={property.id}>
                                <td>{property.title}</td>
                                <td>{property.description}</td>
                                <td>{property.price}</td>
                                <td>{property.available ? "Available" : "Not Available"}</td>
                                <td>{property.type}</td>
                                <td>{property.location}</td>
                                <td>
                                    {property.image && (
                                        <img
                                            src={`data:image/jpeg;base64,${property.image}`}
                                            alt="Property"
                                            width="100"
                                        />
                                    )}
                                </td>
                                <td>{new Date(property.createdAt).toLocaleString()}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ShowProperty;
