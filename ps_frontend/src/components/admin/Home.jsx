import React, {Component} from 'react';
import './Home.css';
import NavBar from "../NavBar";
import MenuAdmin from "./MenuAdmin";

class Home extends Component {
    render() {
        return (
            <div className="home-container">
                <NavBar className ="navbar"/>
                <MenuAdmin className ="menu" />
            </div>
        );
    }
}

export default Home;