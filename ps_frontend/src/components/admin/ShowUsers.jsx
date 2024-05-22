import React, { Component } from 'react';
import axiosInstance from "../../axios";
import './ShowUsers.css';
import NavBar from "../NavBar";
import {Link} from "react-router-dom";
import { IoArrowBack } from "react-icons/io5";

class ShowUsers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
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

    render() {
        const {users} = this.state;
        return (
            <div>
                <NavBar/>
                <div className="back">
                    <Link to="/home" className="link-box">Back</Link>
                    <IoArrowBack className="icon"/>
                </div>
                <div className='user-list'>
                    <h1>User List</h1>
                    <table>
                        <thead>
                        <tr>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Created At</th>
                        </tr>
                        </thead>
                        <tbody>
                        {users.map(user => (
                            <tr key={user.id}>
                                <td>{user.username}</td>
                                <td>{user.password}</td>
                                <td>{user.email}</td>
                                <td>{user.role}</td>
                                <td>{new Date(user.createdAt).toLocaleString()}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>

        );
    }
}

export default ShowUsers;
