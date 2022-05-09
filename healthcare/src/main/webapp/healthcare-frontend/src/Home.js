import React from "react";
import { Link } from "react-router-dom";
import Footer from "./Footer";
import Navbar from "./Navbar"
const Home = () => {
  return (
    <div>
      <Navbar/>
      <div className="menu">
        <h1>Home Page</h1>
        <br />
        
        <li className="menu-item">
          <Link to="/patientRegistration" className="l">Patient Registration</Link>
        </li>
        <li className="menu-item">
          <Link to="/addPatientEHR" className="l">Add Patient EHR</Link>
        </li>
        <li className="menu-item">
          <Link to="/createConsent" className="l">Create Consent</Link>
        </li>
        <li className="menu-item">
          <Link to="/viewConsents" className="l">View Consents</Link>
        </li>
      </div>
      <Footer/>
    </div>
  );
};
  
export default Home;