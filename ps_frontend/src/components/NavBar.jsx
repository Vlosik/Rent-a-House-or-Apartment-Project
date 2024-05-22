import React, { Component } from 'react';
import './NavBar.css';
import { Link } from "react-router-dom";
import { ReactComponent as Logo } from '../images/logo.svg';

class NavBar extends Component {
    render() {
        return (
            <div className="navbar">
                <div className="container">
                    <div className="logo">
                        <Link to="/home" className="box"><Logo /></Link>
                    </div>
                    <div className="nav-elements">
                        <ul>
                            <li>
                                <Link to="/home" className="box">Home</Link>
                            </li>
                            <li>
                                <Link to="/profile" className="box">Profile</Link>
                            </li>
                            <li>
                                <Link to="/contact" className="box">Contact</Link>
                            </li>
                            <li className="sign-out">
                                <Link to="/" className="box">Sign Out</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

export default NavBar;
