import React from "react";
import { Link } from "react-router-dom";
import AdminNavbar from "./AdminNavbar"
import Footer from "./Footer";
const AdminHome = () => {
  return (
    <div>
      <AdminNavbar/>
      <div className="menu">
        <h1>Home Page</h1>
        <br />
        
        <li className="menu-item">
          <Link to="/admin/addDoctor" className="l">Add Health Professional</Link>
        </li>
        <li className="menu-item">
          <Link to="/admin/removeDoctor" className="l">Remove Health Professional</Link>
        </li>
        <li className="menu-item">
          <Link to="/admin/details" className="l">Health Professional User Details</Link>
        </li>
      </div>
      <Footer/>
    </div>
  );
};
  
export default AdminHome;