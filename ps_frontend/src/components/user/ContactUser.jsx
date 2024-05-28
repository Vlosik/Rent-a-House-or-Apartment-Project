import React, { Component } from 'react';
import './ContactUser.css';
import NavBarUser from "../NavbarUser";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";
import { IoChatboxEllipses } from "react-icons/io5";
import axiosInstance from "../../axios";
import history from "../../history";

class ContactUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showMessageField: false,
            message: ''
        };
    }

    toggleMessageField = () => {
        this.setState(prevState => ({ showMessageField: !prevState.showMessageField }));
    }

    handleInputChange = (event) => {
        this.setState({ message: event.target.value });
    }

    sendMessage = () => {
        const { message } = this.state;
        const user = localStorage.getItem('usernameUser');
        const mes = {
            username : "Adrian",
            message: `${message} (${user})`
        }
        console.log("Message sent to admin: ", mes);
        axiosInstance.post("/notification/insert",mes)
            .then(response => {
                console.log(response.data);
                history.push("/homeUser");
                window.location.reload();
            })
            .catch(error => {
                console.log('Login error:', error);
            });
    }

    render() {
        const { showMessageField, message } = this.state;
        return (
            <div>
                <NavBarUser />
                <div className="back">
                    <Link to="/homeUser" className="link-box">Back</Link>
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
                <div className="message-icon" onClick={this.toggleMessageField}>
                    <IoChatboxEllipses size={40} />
                </div>
                {showMessageField && (
                    <div className="message-field">
                        <textarea
                            value={message}
                            onChange={this.handleInputChange}
                            placeholder="Write your message to the admin..."
                        />
                        <button onClick={this.sendMessage}>Send</button>
                    </div>
                )}
            </div>
        );
    }
}

export default ContactUser;
