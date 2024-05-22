import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './AddProperty.css';
import NavBar from "../NavBar";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";
import history from "../../history";

class AddProperty extends Component {
    constructor(props) {
        super(props);
        this.state = {
            admin_username: localStorage.getItem('username'),
            title: '',
            description: '',
            price: '',
            free: '',
            type: '',
            location: '',
            image: null,
            registerSuccess: false,
            error: null,
        };
    }

    handleInputChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    }

    handleImageChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            this.setState({ image: file });
        }
    }

    register = async (event) => {
        event.preventDefault();

        const { admin_username, title, description, price, free, type, location, image } = this.state;

        const formData = new FormData();
        formData.append('admin_username', admin_username);
        formData.append('title', title);
        formData.append('description', description);
        formData.append('price', price);
        formData.append('free', free);
        formData.append('type', type);
        formData.append('location', location);
        formData.append('image', image);

        axiosInstance.post("/property/insert", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(response => {
                console.log(response.data);
                if (response.status === 200) {
                    alert('Property registered successfully');
                    history.push("/home");
                    window.location.reload();
                } else {
                    // Handle register failure
                    console.log("Property registration failed");
                }
            })
            .catch(error => {
                console.log('Property registration error:', error);
            });
    }

    render() {
        const { admin_username, title, description, price, free, type, location } = this.state;
        return (
            <div>
                <NavBar />
                <div className="back">
                    <Link to="/home" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className='property-editor'>
                    <h1>Add Property</h1>
                    <form onSubmit={this.register}>
                        <div className="form-group">
                            <label htmlFor="admin_username">Admin Username</label>
                            <input type="text" id="admin_username" name="admin_username" value={admin_username} onChange={this.handleInputChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="title">Title</label>
                            <input type="text" id="title" name="title" value={title} onChange={this.handleInputChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="description">Description</label>
                            <input type="text" id="description" name="description" value={description} onChange={this.handleInputChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="price">Price</label>
                            <input type="number" id="price" name="price" value={price} onChange={this.handleInputChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="free">Availability</label>
                            <select id="free" name="free" value={free} onChange={this.handleInputChange}>
                                <option value="" disabled>Select availability</option>
                                <option value={true}>Available</option>
                                <option value={false}>Not Available</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="type">Type</label>
                            <select id="type" name="type" value={type} onChange={this.handleInputChange}>
                                <option value="" disabled>Select Type</option>
                                <option value={"APARTMENT"}>Apartment</option>
                                <option value={"HOUSE"}>House</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="location">Location</label>
                            <input type="text" id="location" name="location" value={location} onChange={this.handleInputChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="image">Image</label>
                            <input type="file" id="image" name="image" onChange={this.handleImageChange} />
                        </div>
                        <div className="button-container">
                            <button type="submit">Add Property</button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default AddProperty;
