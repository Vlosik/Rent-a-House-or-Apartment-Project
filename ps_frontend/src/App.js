import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Register from "./components/login/Register";
import Login from "./components/login/Login";
import ForgotPassword from "./components/login/ForgotPassword";
import Home from "./components/admin/Home";
import ShowUsers from "./components/admin/ShowUsers";
import EditUsers from "./components/admin/EditUsers";
import ShowProperty from "./components/admin/ShowProperty";
import AddProperty from "./components/admin/AddProperty";
import EditProperty from "./components/admin/EditProperty";
import Profile from "./components/admin/Profile";
import Contact from "./components/admin/Contact";
import HomeUser from "./components/user/HomeUser"
import Property from "./components/user/Property";
import ProfileUser from "./components/user/ProfileUser"
import ContactUser from "./components/user/ContactUser"
import ShowRentals from "./components/user/ShowRentals";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/updatePassword" element={<ForgotPassword />} />
                <Route path="/home" element={<Home />} />
                <Route path="/showUsers" element={<ShowUsers />} />
                <Route path="/editUsers" element={<EditUsers />} />
                <Route path="/showProperty" element={<ShowProperty />} />
                <Route path="/addProperty" element={<AddProperty />} />
                <Route path="/editProperty" element={<EditProperty />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/profileUser" element={<ProfileUser />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/contactUser" element={<ContactUser />} />
                <Route path="/homeUser" element={<HomeUser />} />
                <Route path="/property" element={<Property />} />
                <Route path="/userRentals" element={<ShowRentals />} />
            </Routes>
        </Router>
    );
}

export default App;