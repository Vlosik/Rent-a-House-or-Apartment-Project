import React, { Component } from 'react';
import './ForgotPassword.css';
import { FaUser, FaLock } from "react-icons/fa";
import axiosInstance from "../../axios";
import history from "../../history";
import {Link} from "react-router-dom";

class ForgotPassword extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            LoginSuccess: false
        };
    }

    forgot = (event) => {
        event.preventDefault();
        const { username, password} = this.state;
        const change = {
            username,
            password
        };
        console.log(change);
        axiosInstance.put("/user/updatePassword", change)
            .then(response => {
                console.log(response.data);
                history.push('/');
                window.location.reload();
            })
            .catch(error => {
                console.log('Forgot error:', error);
            });
    }

    render() {
        return (
            <div className='wrapper'>
                <form onSubmit={this.forgot}>
                    <h1>Update Password</h1>
                    <div className="input-box">
                        <input type="text" placeholder='Username' required
                               onChange={e => this.setState({username: e.target.value})}/>
                        <FaUser className='icon'/>
                    </div>
                    <div className="input-box">
                        <input type="text" placeholder='Password' required
                               onChange={e => this.setState({password: e.target.value})}/>
                        <FaLock className='icon'/>
                    </div>
                    <button type="submit">Change Password</button>
                </form>
                <div className="back-container">
                    <Link to="/" className="back">Back to login</Link>
                </div>
            </div>
        )
    }
}

export default ForgotPassword;
