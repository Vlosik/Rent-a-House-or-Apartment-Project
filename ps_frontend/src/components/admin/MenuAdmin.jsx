import React, {Component} from 'react';
import './MenuAdmin.css';
import {Link} from "react-router-dom";
import { FaUser } from "react-icons/fa";
import { FaUserEdit } from "react-icons/fa";
import { BsFillHouseAddFill } from "react-icons/bs";
import { BsFillHouseExclamationFill } from "react-icons/bs";
import { BsHouses } from "react-icons/bs";
class MenuAdmin extends Component {
    render() {
        return (
            <div className="meniu">
                <div className="instructions">
                    <Link to="/editUsers" className="link-box">Edit Users</Link>
                    <FaUserEdit className="icon"/>
                </div>
                <div className="instructions">
                    <Link to="/showUsers" className="link-box">Show Users</Link>
                    <FaUser className="icon"/>
                </div>
                <div className="instructions">
                    <Link to="/addProperty" className="link-box">Add Property</Link>
                    <BsFillHouseAddFill className="icon"/>
                </div>
                <div className="instructions">
                    <Link to="/showProperty" className="link-box">Show Property</Link>
                    <BsHouses className="icon"/>
                </div>
                <div className="instructions">
                    <Link to="/editProperty" className="link-box">Edit Property</Link>
                    <BsFillHouseExclamationFill className="icon"/>
                </div>
            </div>
        );
    }
}

export default MenuAdmin;