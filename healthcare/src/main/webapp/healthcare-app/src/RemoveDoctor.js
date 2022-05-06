import { useState } from "react";
import './patientRegistration.css';
import { format } from 'date-fns';
import AdminNavbar from "./AdminNavbar"

function RemoveDoctor(){
    const user = JSON.parse(localStorage.getItem('user'));
    const [doctor,setDoctor]=useState({
        healthProfessionalId:""
      });
    const removeDoctor = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(doctor));
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json",
                "healthProfessionalId":doctor.healthProfessionalId,
                "Authorization": "Bearer "+user.jwtToken },
            body: JSON.stringify(doctor)
          };
          fetch("http://2646-103-156-19-229.ngrok.io/healthcare/remove", requestOptions)
            .then(response => response.json())
            .then(res => console.log(res));
    } 
    return(
        <div class="container">
            <AdminNavbar/>
            <h1>Remove Health Professional</h1>
            
            <div class="row">
                <div class="col-25"><label>health Professional ID</label></div>
                <div class="col-75"><input type="number" 
                value={doctor.healthProfessionalId}
                onChange={(e) => setDoctor({ ...doctor, healthProfessionalId: e.target.value })}/> </div>
            </div>
           
            <input type="submit" onClick={removeDoctor} value="Remove"/>
        </div>
    );
}
export default RemoveDoctor;