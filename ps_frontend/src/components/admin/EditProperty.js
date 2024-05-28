import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './EditProperty.css';
import NavBar from "../NavBar";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";
import history from "../../history";

class EditProperty extends Component {
    constructor(props) {
        super(props);
        this.state = {
            properties: [],
            selectedProperty: null,
            title: '',
            description: '',
            price: '',
            free: '',
            type: '',
            location: '',
            image: null,
            updateImage: false,
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

    handlePropertySelection = (event) => {
        const selectedProperty = this.state.properties.find(property => property.title === event.target.value);
        this.setState({
            selectedProperty,
            title: selectedProperty.title,
            description: selectedProperty.description,
            price: selectedProperty.price,
            free: selectedProperty.free,
            type: selectedProperty.type,
            location: selectedProperty.location,
        });
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

    handleUpdateImageChange = (event) => {
        this.setState({ updateImage: event.target.checked });
    }

    logFormData = (formData) => {
        for (let [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }
    }

    updateProperty = (event) => {
        event.preventDefault();
        const { properties, selectedProperty, title, description, price, free, type, location, image, updateImage } = this.state;
        const existingProperty = properties.find(property => property.id === selectedProperty.id);
        const availability = (free !== true && free !== false) ? existingProperty.available : free;
        console.log('Selected Property:', selectedProperty);
        console.log('Title:', title);
        console.log('Description:', description);
        console.log('Price:', price);
        console.log('Free:', availability);
        console.log('Type:', type);
        console.log('Location:', location);
        console.log('Update Image:', updateImage);
        console.log('Image:', image);

        const formData = new FormData();
        formData.append('id', selectedProperty.id);
        formData.append('title', title);
        formData.append('description', description);
        formData.append('price', price);
        formData.append('free', availability);
        formData.append('type', type);
        formData.append('location', location);
        if (updateImage && image) {
            formData.append('image', image);
        }

        const url = updateImage ? "property/updatePhoto" : "property/update";

        axiosInstance.put(url, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(response => {
                console.log(response.data);
                alert('Property updated successfully');
                history.push("/home");
                window.location.reload();
            })
            .catch(error => {
                console.error('Error updating property:', error);
                alert('Failed to update property');
            });
    }


    deleteProperty = () => {
        const { selectedProperty, title } = this.state;
        const property = {
            title
        };
        console.log(property);

        axiosInstance({
            method: 'delete',
            url: '/property/delete',
            data: property
        })
            .then(response => {
                console.log(response.data);
                alert('Property deleted successfully');
                this.setState({ selectedProperty: null, title: '', description: '', price: '', free: '', type: '', location: '' });
                this.fetchProperties();
                history.push("/home");
                window.location.reload();
            })
            .catch(error => {
                console.error('Error deleting property:', error);
                alert('Failed to delete property');
            });
    }

    render() {
        const { properties, selectedProperty, title, description, price, free, type, location, updateImage } = this.state;
        return (
            <div>
                <NavBar />
                <div className="back">
                    <Link to="/home" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className='property-editor'>
                    <h1>Edit Property</h1>
                    <div className="form-group">
                        <label htmlFor="title">Select Property</label>
                        <select id="title" name="title" onChange={this.handlePropertySelection} value={selectedProperty ? selectedProperty.title : ''}>
                            <option value="" disabled>Select a property</option>
                            {properties.map(property => (
                                <option key={property.id} value={property.title}>
                                    {property.title}
                                </option>
                            ))}
                        </select>
                    </div>
                    {selectedProperty && (
                        <form onSubmit={this.updateProperty}>
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
                                <label>
                                    <input type="checkbox" checked={updateImage} onChange={this.handleUpdateImageChange} />
                                    Update Image
                                </label>
                                {updateImage && (
                                    <input type="file" id="image" name="image" onChange={this.handleImageChange} />
                                )}
                            </div>
                            <div className="button-container">
                                <button type="submit">Update Property</button>
                                <button type="button" onClick={this.deleteProperty} className="delete-button">Delete Property</button>
                            </div>
                        </form>
                    )}
                </div>
            </div>
        );
    }
}

export default EditProperty;
