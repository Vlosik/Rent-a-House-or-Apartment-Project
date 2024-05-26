import React, { Component } from 'react';
import './NavBarUser.css';
import { Link } from "react-router-dom";
import { ReactComponent as Logo } from '../images/logo.svg';
import { FaBell } from 'react-icons/fa';
import axiosInstance from "./../axios";

class NavBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchQuery: '',
            notifications: [],
            showNotifications: false,
        };
    }

    handleInputChange = (event) => {
        this.setState({ searchQuery: event.target.value });
    }

    handleSearch = () => {
        localStorage.setItem('searchQuery', this.state.searchQuery);
        console.log('Search query saved:', this.state.searchQuery);
        window.location.reload();
    }

    toggleNotifications = () => {
        if (!this.state.showNotifications) {
            this.fetchNotifications();
        }
        this.setState(prevState => ({
            showNotifications: !prevState.showNotifications
        }));
    }

    fetchNotifications = async () => {
        const username = localStorage.getItem('usernameUser');
        console.log(username);
        axiosInstance.post("/notification/getNotifications", { username })
            .then(response => {
                this.setState({ notifications: response.data });
            })
            .catch(error => {
                console.log('Error:', error);
            });
    }

    render() {
        const { notifications, showNotifications } = this.state;

        return (
            <div className="navbar">
                <div className="container">
                    <div className="logo">
                        <Link to="/homeUser" className="box"><Logo /></Link>
                    </div>
                    <div className="search-container">
                        <input
                            type="text"
                            className="search-field"
                            placeholder="Search..."
                            value={this.state.searchQuery}
                            onChange={this.handleInputChange}
                        />
                        <button
                            className="search-button"
                            onClick={this.handleSearch}
                        >
                            Submit
                        </button>
                    </div>
                    <div className="nav-elements">
                        <ul>
                            <li className="notifications">
                                <FaBell className="icon" onClick={this.toggleNotifications} />
                                {showNotifications && (
                                    <div className="notifications-list">
                                        <h4>Notifications</h4>
                                        <ul>
                                            {notifications.length === 0 ? (
                                                <li>No notifications</li>
                                            ) : (
                                                notifications.map(notification => (
                                                    <li key={notification.id}>{notification.message}</li>
                                                ))
                                            )}
                                        </ul>
                                    </div>
                                )}
                            </li>
                            <li>
                                <Link to="/homeUser" className="box">Home</Link>
                            </li>
                            <li>
                                <Link to="/profileUser" className="box">Profile</Link>
                            </li>
                            <li>
                                <Link to="/contactUser" className="box">Contact</Link>
                            </li>
                            <li className="sign-out">
                                <Link to="/" className="box">Log out</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

export default NavBar;
