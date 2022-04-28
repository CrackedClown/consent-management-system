import { useState } from "react";
import './patientRegistration.css';
import { format } from 'date-fns';
import AdminNavbar from "./AdminNavbar"

function AddDoctor(){
    const user = JSON.parse(localStorage.getItem('user'));
    const [doctor,setDoctor]=useState({
        hid:user.user.hospitalInformation.hid,
        name:"",
        age:"",
        gender:"",
        email:"",
        governmentId:"",
        degree:"",
        department:"",
        mobileNum:"",
        role:""
      });
    const addDoctor = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(doctor));
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json",
                "Authorization": "Bearer "+user.jwtToken },
            body: JSON.stringify(doctor)
          };
          fetch("http://48b6-119-161-98-68.ngrok.io/healthcare/register", requestOptions)
            .then(response => response.json())
            .then(res => console.log(res));
    } 
    return(
        <div class="container">
            <AdminNavbar/>
            <h1>Add Health Professional</h1>
            <div class="row">
                <div class="col-25"><label>Name</label></div>
                <div class="col-75"><input type="text" 
                value={doctor.name}
                onChange={(e) => setDoctor({ ...doctor, name: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Age</label></div>
                <div class="col-75"><input type="number" 
                value={doctor.age}
                onChange={(e) => setDoctor({ ...doctor, age: e.target.value })}/> </div>
            </div>

            <div class="row">
                <div class="col-25"><label>Gender</label></div>
                <input type="radio" id="male" name="gender" value="Male"
                onChange={(e) => setDoctor({ ...doctor, gender: e.target.value })}/>
                <label for="male">Male</label>
                <input type="radio" id="female" name="gender" value="Female"
                onChange={(e) => setDoctor({ ...doctor, gender: e.target.value })}/>
                <label for="female">Female</label>
            </div>

            <div class="row">
                <div class="col-25"><label>Email</label></div>
                <div class="col-75"><input type="email" 
                value={doctor.email}
                onChange={(e) => setDoctor({ ...doctor, email: e.target.value })}/> </div>
            </div>

            <div class="row">
                <div class="col-25"><label>Government ID</label></div>
                <div class="col-75"><input type="number" 
                value={doctor.governmentId}
                onChange={(e) => setDoctor({ ...doctor, governmentId: e.target.value })}/> </div>
            </div>
            
            <div class="row">
                <div class="col-25"><label>Degree</label></div>
                <div class="col-75"><input type="text" 
                value={doctor.degree}
                onChange={(e) => setDoctor({ ...doctor, degree: e.target.value })}/> </div>
            </div>

            <div class="row">
                <div class="col-25"><label>Department</label></div>
                <div class="col-75"><input type="text" 
                value={doctor.department}
                onChange={(e) => setDoctor({ ...doctor, department: e.target.value })}/> </div>
            </div>

            <div class="row">
                <div class="col-25"><label>Mobile Number</label></div>
                <div class="col-75"><input type="number" 
                value={doctor.mobileNum}
                onChange={(e) => setDoctor({ ...doctor, mobileNum: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Role</label></div>
                <input type="radio" id="doctor" name="role" value="DOCTOR"
                onChange={(e) => setDoctor({ ...doctor, role: e.target.value })}/>
                <label for="doctor">Doctor</label>
                <input type="radio" id="nurse" name="role" value="NURSE"
                onChange={(e) => setDoctor({ ...doctor, role: e.target.value })}/>
                <label for="nurse">Nurse</label>
            </div>
            <input type="submit" onClick={addDoctor} value="Add"/>
        </div>
    );
}
export default AddDoctor;