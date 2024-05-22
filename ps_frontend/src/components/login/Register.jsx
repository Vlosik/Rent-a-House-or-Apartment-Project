import React,{Component} from 'react';
import './Register.css';
import { FaUser,FaLock } from "react-icons/fa";
import { MdEmail } from "react-icons/md";
import axiosInstance from "../../axios";
import { Link } from 'react-router-dom';
import history from "../../history";

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            email: '',
            role: 'USER',
            registerSuccess: false
        };
    }

    register = (event) => {
        event.preventDefault();
        const { username, password, email, role } = this.state;
        const user = {
            username,
            password,
            email,
            role
        };
        axiosInstance.post("/user/insert", user)
            .then(response => {
                this.setState({ registerSuccess: true });
                history.push('/');
                window.location.reload();
            })
            .catch(error => {
                if (error.response) {
                    console.log('Problem with response:', error.response.status);
                } else if (error.request) {
                    console.log('Problem with request:', error.request);
                } else {
                    console.log('Error', error.message);
                }
                console.log(error.config);
            });
    }

    render() {
        return (
            <div className='wrapper'>
                <div className="register-form">
                    <form onSubmit={this.register}>
                        <h1>Registration</h1>
                        <div className="input-box">
                            <input type="text" placeholder='Username' required
                                   onChange={e => this.setState({ username: e.target.value })} />
                            <FaUser className='icon'/>
                        </div>

                        <div className="input-box">
                            <input type="email" placeholder='Email' required
                                   onChange={e => this.setState({ email: e.target.value })}/>
                            <MdEmail className='icon'/>
                        </div>

                        <div className="input-box">
                            <input type="text" placeholder='Password' required
                                   onChange={e => this.setState({ password: e.target.value })} />
                            <FaLock className='icon'/>
                        </div>

                        <button type="submit" className="btn" >Register</button>

                        <div className="login-link">
                            <p>Already have an account?<Link to="/" className= "Link-login">Login</Link></p>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default Register;