import { useState } from "react";
import './CSS/form.css';
import { format } from 'date-fns';
import AdminNavbar from "./AdminNavbar"
import Footer from "./Footer";
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function RemoveDoctor(){
    const user = JSON.parse(localStorage.getItem('user'));
    const [doctor,setDoctor]=useState({
        healthProfessionalId:""
      });
    const removeDoctor = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(doctor));
        const requestOptions = {
            method: "DELETE",
            headers: { "Content-Type": "application/json",
                "healthProfessionalId":doctor.healthProfessionalId,
                "Authorization": "Bearer "+user.jwtToken }
          };
          fetch("http://1091-119-161-98-68.ngrok.io/healthcare/remove", requestOptions)
            .then(response => response.json())
            .then(res => {
                console.log(res);
                setDoctor({
                    healthProfessionalId:""
                })
                if("id" in res)
                {
                    toast.success('Health Professional Removed Successfully'); 
                } 
                else
                {
                    toast.error(res.error);
                }

            });
    } 
    return(
        <div>
            <AdminNavbar/>
            <ToastContainer/>
            <div className="container">
            <h1>Remove Health Professional</h1>
            <form onSubmit={removeDoctor}>
                <div className="row">
                    <div className="col-25"><label>health Professional ID</label></div>
                    <div className="col-75"><input type="number" className="in"
                    value={doctor.healthProfessionalId} required
                    onChange={(e) => setDoctor({ ...doctor, healthProfessionalId: e.target.value })}/> </div>
                </div>
                
                <input type="submit" className="sb" value="Remove"/>
            </form>
            </div>
            <Footer/>
        </div>
    );
}
export default RemoveDoctor;