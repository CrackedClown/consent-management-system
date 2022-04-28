import React from "react";
import { Link } from "react-router-dom";
import AdminNavbar from "./AdminNavbar"
const AdminHome = () => {
  return (
    <div>
      <AdminNavbar/>
      <h1>Home Page</h1>
      <br />
      <ul>
        <li>
          <Link to="/admin/addDoctor">Add Health Professional</Link>
        </li>
        <li>
          <Link to="/admin/removeDoctor">Remove Health Professional</Link>
        </li>
      </ul>
    </div>
  );
};
  
export default AdminHome;