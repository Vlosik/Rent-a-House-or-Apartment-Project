import React, { Component } from 'react';
import './Contact.css';
import NavBar from "../NavBar";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";

class Contact extends Component {
    render() {
        return (
            <div>
                <NavBar />
                <div className="back">
                    <Link to="/home" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className='contact-info'>
                    <h1>Contact Us</h1>
                    <div className="contact-item">
                        <label>Phone Number:</label>
                        <span>+1 (555) 123-4567</span>
                    </div>
                    <div className="contact-item">
                        <label>Email:</label>
                        <span>contact@example.com</span>
                    </div>
                    <div className="contact-item">
                        <label>Address:</label>
                        <span>123 Main St, Anytown, USA</span>
                    </div>
                    <div className="contact-item">
                        <label>Working Hours:</label>
                        <span>Monday - Friday: 9:00 AM - 5:00 PM</span>
                    </div>
                </div>
            </div>
        );
    }
}

export default Contact;
