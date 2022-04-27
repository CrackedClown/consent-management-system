import React from "react";
import { Link } from "react-router-dom";
import Navbar from "./Navbar"
const Home = () => {
  return (
    <div>
      <Navbar/>
      <h1>Home Page</h1>
      <br />
      <ul>
        <li>
          <Link to="/home">Home</Link>
        </li>
        <li>
          <Link to="/patientRegistration">Patient Registration</Link>
        </li>
        <li>
          <Link to="/addPatientEHR">Add Patient EHR</Link>
        </li>
        <li>
          <Link to="/createConsent">Create Consent</Link>
        </li>
        <li>
          <Link to="/viewConsents">View Consents</Link>
        </li>
      </ul>
    </div>
  );
};
  
export default Home;