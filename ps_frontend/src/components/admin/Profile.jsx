import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './Profile.css';
import NavBar from "../NavBar";
import { Link } from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            role: '',
            password: '',
            editMode: false,
            loading: true,
            error: null,
        };
    }

    componentDidMount() {
        const username = localStorage.getItem('username');
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

    handleInputChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    }

    toggleEditMode = () => {
        this.setState({ editMode: !this.state.editMode });
    }

    updateUser = (event) => {
        event.preventDefault();
        const { username, email, role, password } = this.state;
        const user = { username, email, role, password };

        axiosInstance.put("user/update", user)
            .then(response => {
                console.log(response.data);
                alert('User updated successfully');
                this.setState({ editMode: false });
            })
            .catch(error => {
                console.error('Error updating user:', error);
                alert('Failed to update user');
            });
    }

    render() {
        const { username, email, role, password, editMode, loading, error } = this.state;

        if (loading) {
            return <p>Loading...</p>;
        }

        if (error) {
            return <p>Error: {error}</p>;
        }

        return (
            <div>
                <NavBar />
                <div className="back">
                    <Link to="/home" className="link-box">Back</Link>
                    <IoArrowBack className="icon" />
                </div>
                <div className="profile-container">
                    <div className="profile-header">
                        <h1>User Profile</h1>
                        {!editMode && (
                            <button type="button" onClick={this.toggleEditMode} className="edit-button">Edit</button>
                        )}
                    </div>
                    <form onSubmit={this.updateUser}>
                        <div className="profile-field">
                            <label>Username:</label>
                            {editMode ? (
                                <input type="text" name="username" value={username} onChange={this.handleInputChange} />
                            ) : (
                                <span>{username}</span>
                            )}
                        </div>
                        <div className="profile-field">
                            <label>Email:</label>
                            {editMode ? (
                                <input type="email" name="email" value={email} onChange={this.handleInputChange} />
                            ) : (
                                <span>{email}</span>
                            )}
                        </div>
                        <div className="profile-field">
                            <label>Role:</label>
                            {editMode ? (
                                <input type="text" name="role" value={role} onChange={this.handleInputChange} />
                            ) : (
                                <span>{role}</span>
                            )}
                        </div>
                        <div className="profile-field">
                            <label>Password:</label>
                            {editMode ? (
                                <input type="text" name="password" value={password} onChange={this.handleInputChange} />
                            ) : (
                                <span>{password}</span>
                            )}
                        </div>
                        {editMode && (
                            <div className="button-container">
                                <button type="submit" className="update-button">Update</button>
                                <button type="button" onClick={this.toggleEditMode} className="cancel-button">Cancel</button>
                            </div>
                        )}
                    </form>
                </div>
            </div>
        );
    }
}

export default Profile;
