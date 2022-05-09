import { useState,useEffect } from "react";
import './CSS/form.css';
import { Link } from "react-router-dom";
import AdminNavbar from './AdminNavbar';
import { format } from 'date-fns';
import Footer from "./Footer";
function HealthProfessionalDetails(){
    const user = JSON.parse(localStorage.getItem('user'));
    const [details,setDetails]=useState({
        items:[],
        isDataLoaded:false
    });
    useEffect(() => {
        console.log("loaded");
        viewDetails();
    },[]);
    const viewDetails = (e) => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application/json",
            "Authorization": "Bearer "+user.jwtToken },
          };
          
        fetch('http://localhost:8080/healthcare',requestOptions)
            .then(response => response.json())
            .then(res => setDetails({
                items:res,
                isDataLoaded:true
            }));
        console.log(details);
    } 
    
    if (!details.isDataLoaded) 
    return <div> <h1> Pleses wait some time.... </h1> </div> ;
    return(
        <div>
            <AdminNavbar/>
            <div className="container">
            <h1>Health Professionals User Details</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Degree</th>
                    <th>Department</th>
                    <th>Mobile Number</th>
                    <th>Role</th>
                </tr>  
                {
                details.items.map((item) => (
                    <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        <td>{item.age}</td>
                        <td>{item.gender}</td>
                        <td>{item.email}</td>
                        <td>{item.degree}</td>
                        <td>{item.department}</td>
                        <td>{item.mobileNum}</td>
                        <td>{item.role}</td>
                    </tr> 
                ))
                }      
            </table>
            </div>
            <Footer/>
        </div>        
    );
}
export default HealthProfessionalDetails;