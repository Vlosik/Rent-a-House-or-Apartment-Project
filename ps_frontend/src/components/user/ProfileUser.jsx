import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './ProfileUser.css';
import NavBarUser from "../NavbarUser";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";

class ProfileUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            role: '',
            password: '',
            loading: true,
            error: null,
        };
    }

    componentDidMount() {
        const username = localStorage.getItem('usernameUser');
        if (username) {
            axiosInstance.post("user/findUser", { username })
                .then(response => {
                    const user = response.data;
                    this.setState({
                        username: user.username,
                        email: user.email,
                        role: user.role,
                        password: user.password,
                        loading: false,
                    });
                })
                .catch(error => {
                    console.error('There was an error fetching the user data!', error);
                    this.setState({ error: error.message, loading: false });
                });
        }
    }

    render() {
        const { username, email, role, password } = this.state;

        return (
            <div>
                <NavBarUser />
                <div className="back">
                    <Link to="/homeUser" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className="profile-container">
                    <div className="profile-header">
                        <h1>User Profile</h1>
                    </div>
                    <div className="profile-field">
                        <label>Username:</label>
                        <span>{username}</span>
                    </div>
                    <div className="profile-field">
                        <label>Email:</label>
                        <span>{email}</span>
                    </div>
                    <div className="profile-field">
                        <label>Role:</label>
                        <span>{role}</span>
                    </div>
                    <div className="profile-field">
                        <label>Password:</label>
                        <span>{password}</span>
                    </div>
                    <Link to="/userRentals" className="show-rentals-button">Show Rentals</Link>
                </div>
            </div>
        );
    }
}

export default ProfileUser;
