import React, { Component } from 'react';
import './NavBarUser.css';
import { Link } from "react-router-dom";
import { ReactComponent as Logo } from '../images/logo.svg';

class NavBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchQuery: ''
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

    render() {
        return (
            <div className="navbar">
                <div className="container">
                    <div className="logo">
                        <Link to="/home" className="box"><Logo /></Link>
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
                            Search
                        </button>
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
