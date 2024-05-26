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
                        <span>+40749316040</span>
                    </div>
                    <div className="contact-item">
                        <label>Email:</label>
                        <span>adrianvlose6@gmail.com</span>
                    </div>
                    <div className="contact-item">
                        <label>Address:</label>
                        <span>Strada Observatorului 34, Cluj, Romania</span>
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
