import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './EditUsers.css';
import NavBar from "../NavBar";
import { Link} from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";
import history from "../../history";

class EditUsers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            selectedUser: null,
            username: '',
            password: '',
            email: '',
            role: '',
            loading: true,
            error: null,
        };
    }

    componentDidMount() {
        this.fetchUsers();
    }

    fetchUsers = () => {
        axiosInstance.get("user/findAll")
            .then(response => {
                const currentUsername = localStorage.getItem('username');
                const filteredUsers = response.data.filter(user => user.username !== currentUsername);
                this.setState({ users: filteredUsers, loading: false });
            })
            .catch(error => {
                this.setState({ error: error.message, loading: false });
            });
    }

    handleUserSelection = (event) => {
        const selectedUser = this.state.users.find(user => user.username === event.target.value);
        this.setState({
            selectedUser,
            username: selectedUser.username,
            password: selectedUser.password,
            email: selectedUser.email,
            role: selectedUser.role,
        });
    }

    handleInputChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    }

    updateUser = (event) => {
        event.preventDefault();
        const { selectedUser, username, password, email, role } = this.state;
        const user = {
            username,
            password,
            email,
            role
        };

        axiosInstance.put("user/update", user)
            .then(response => {
                console.log(response.data);
                alert('User updated successfully');
                history.push("/home");
                window.location.reload();
            })
            .catch(error => {
                console.error('Error updating user:', error);
                alert('Failed to update user');
            });
    }

    deleteUser = () => {
        const { selectedUser,username } = this.state;
        const user = {
            username
        };
        console.log(user);

        axiosInstance({
            method: 'delete',
            url: '/user/delete',
            data: user
        })
            .then(response => {
                console.log(response.data);
                alert('User deleted successfully');
                this.setState({ selectedUser: null, username: '', password: '', email: '', role: '' });
                this.fetchUsers();
                history.push("/home");
                window.location.reload();
            })
            .catch(error => {
                console.error('Error deleting user:', error);
                alert('Failed to delete user');
            });
    }

    render() {
        const { users, selectedUser, username, password, email, role, loading, error } = this.state;

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
                <div className='user-editor'>
                    <h1>Edit User</h1>
                    <div className="form-group">
                        <label htmlFor="username">Select User</label>
                        <select id="username" name="username" onChange={this.handleUserSelection} value={selectedUser ? selectedUser.username : ''}>
                            <option value="" disabled>Select a user</option>
                            {users.map(user => (
                                <option key={user.id} value={user.username}>
                                    {user.username}
                                </option>
                            ))}
                        </select>
                    </div>
                    {selectedUser && (
                        <form onSubmit={this.updateUser}>
                            <div className="form-group">
                                <label htmlFor="username">Username</label>
                                <input type="text" id="username" name="username" value={username} onChange={this.handleInputChange} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="password">Password</label>
                                <input type="text" id="password" name="password" value={password} onChange={this.handleInputChange} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="email">Email</label>
                                <input type="email" id="email" name="email" value={email} onChange={this.handleInputChange} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="role">Role</label>
                                <input type="text" id="role" name="role" value={role} onChange={this.handleInputChange} />
                            </div>
                            <div className="button-container">
                                <button type="submit">Update User</button>
                                <button type="button" onClick={this.deleteUser} className="delete-button">Delete User</button>
                            </div>
                        </form>
                    )}
                </div>
            </div>
        );
    }
}

export default EditUsers;
