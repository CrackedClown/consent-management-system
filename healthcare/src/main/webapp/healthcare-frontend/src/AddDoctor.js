import { useState } from "react";
import './CSS/form.css';
import { format } from 'date-fns';
import AdminNavbar from "./AdminNavbar"
import Footer from "./Footer";
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

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
          fetch("http://localhost:8080/healthcare/register", requestOptions)
            .then(response => response.json())
            .then(res => {
                console.log(res);
                setDoctor({hid:user.user.hospitalInformation.hid,
                    name:"",
                    age:"",
                    gender:"",
                    email:"",
                    governmentId:"",
                    degree:"",
                    department:"",
                    mobileNum:"",
                    role:""});
                if("id" in res)
                {
                    toast.success('Health Professional Added Successfully'); 
                } 
                else
                {
                    toast.error(res.error);
                }

            });
    } 
    return(
        <div className="root">
            <AdminNavbar/>
            <ToastContainer/>
            <div className="container">
            <h1>Add Health Professional</h1>
            <form onSubmit={addDoctor}>
                <div className="row">
                    <div className="col-25"><label>Name</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={doctor.name} required
                    onChange={(e) => setDoctor({ ...doctor, name: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Age</label></div>
                    <div className="col-75"><input type="number" className="in"
                    value={doctor.age} required
                    onChange={(e) => setDoctor({ ...doctor, age: e.target.value })}/> </div>
                </div>

                <div className="row">
                    <div className="col-25"><label>Gender</label></div>
                    <input type="radio" id="male" name="gender" value="Male" required
                    onChange={(e) => setDoctor({ ...doctor, gender: e.target.value })}/>
                  <label for="male">Male</label>
                    <input type="radio" id="female" name="gender" value="Female" required
                    onChange={(e) => setDoctor({ ...doctor, gender: e.target.value })}/>
                  <label for="female">Female</label>
                </div>

                <div className="row">
                    <div className="col-25"><label>Email</label></div>
                    <div className="col-75"><input type="email" className="in"
                    value={doctor.email} required
                    onChange={(e) => setDoctor({ ...doctor, email: e.target.value })}/> </div>
                </div>

                <div className="row">
                    <div className="col-25"><label>Government ID</label></div>
                    <div className="col-75"><input type="text" className="in" pattern="[0-9]{12}" title="must be in 12 digits"
                    value={doctor.governmentId} required
                    onChange={(e) => setDoctor({ ...doctor, governmentId: e.target.value })}/> </div>
                </div>
                
                <div className="row">
                    <div className="col-25"><label>Degree</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={doctor.degree} required
                    onChange={(e) => setDoctor({ ...doctor, degree: e.target.value })}/> </div>
                </div>

                <div className="row">
                    <div className="col-25"><label>Department</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={doctor.department} required
                    onChange={(e) => setDoctor({ ...doctor, department: e.target.value })}/> </div>
                </div>

                <div className="row">
                    <div className="col-25"><label>Mobile Number</label></div>
                    <div className="col-75"><input type="text" className="in" pattern="[0-9]{10}" title="must be in 10 digits"
                    value={doctor.mobileNum} required
                    onChange={(e) => setDoctor({ ...doctor, mobileNum: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Role</label></div>
                    <input type="radio" id="doctor" name="role" value="DOCTOR" required
                    onChange={(e) => setDoctor({ ...doctor, role: e.target.value })}/>
                  <label for="doctor">Doctor</label>
                    <input type="radio" id="nurse" name="role" value="NURSE" required
                    onChange={(e) => setDoctor({ ...doctor, role: e.target.value })}/>
                  <label for="nurse">Nurse</label>
                </div>
                <input type="submit" className="sb" value="Add"/>
            </form>
            </div>
            <Footer/>
        </div>
    );
}
export default AddDoctor;