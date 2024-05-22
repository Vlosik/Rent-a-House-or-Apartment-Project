import React, {Component} from 'react';
import './HomeUser.css';
import NavBarUser from "../NavbarUser";
import MenuUser from "./MenuUser.jsx";

class Home extends Component {
    render() {
        return (
            <div className="home-container">
                <NavBarUser className ="navbar"/>
                <MenuUser className ="menu" />
            </div>
        );
    }
}

export default Home;