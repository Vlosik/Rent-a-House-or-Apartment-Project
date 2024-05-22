import React, {Component} from 'react';
import './Login.css';
import { FaUser,FaLock } from "react-icons/fa";
import { Link } from 'react-router-dom';
import axiosInstance from "../../axios";
import history from "../../history";

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',

            LoginSuccess: false
        };
    }

    login = (event) => {
        event.preventDefault();
        const { username, password} = this.state;
        const verify = {
            username,
            password
        };
        console.log(verify);
        axiosInstance.post("/user/login",verify)
            .then(response => {
                console.log(response.data);
                if (response.data !== 0) {
                    if(response.data === 1){
                        localStorage.setItem('username', username);
                        history.push("/home");
                        window.location.reload();
                    }
                    else{
                        localStorage.setItem('usernameUser', username);
                        history.push("/homeUser");
                        window.location.reload();
                    }
                } else {
                    // Handle login failure
                    console.log("Login failed");
                }
            })
            .catch(error => {
                console.log('Login error:', error);
            });
    }

    render() {
        return (
            <div className='wrapper'>
                <div className='login-form'>
                    <form onSubmit={this.login}>
                        <h1>Login</h1>
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
                        <div className="links">
                            <div>
                                <Link to="/updatePassword" className="updatePassword">Forgot Password?</Link>
                            </div>
                            <div>
                                <Link to="/register" className="sign">Sign Up</Link>
                            </div>
                        </div>

                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default Login;