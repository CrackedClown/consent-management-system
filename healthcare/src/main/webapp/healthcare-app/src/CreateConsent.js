import { useState } from "react";
import './patientRegistration.css';
import Navbar from './Navbar';

function CreateConsent(){
    const user = JSON.parse(localStorage.getItem('user'));
    const [consent,setConsent]=useState({
        hid:user.user.hospitalInformation.hid,
        healthProfessionalId:user.user.id,
        patientId:"",
        fromDate:"",
        toDate:"",
        remarks:"",
        validUpto:""
      });
    const createConsent = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(consent));
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json",
                "Authorization": "Bearer "+user.jwtToken },
            body: JSON.stringify(consent)
          };
          fetch("http://48b6-119-161-98-68.ngrok.io/consent", requestOptions)
            .then(response => response.json())
            .then(res => console.log(res));
    } 
    return(
        <div class="container">
            <Navbar/>
            <h1>Create Consent</h1>
            <div class="row">
                <div class="col-25"><label>Patient ID</label></div>
                <div class="col-75"><input type="number" 
                value={consent.patientId}
                onChange={(e) => setConsent({ ...consent, patientId: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>From Date</label></div>
                <div class="col-75"><input type="date" 
                value={consent.fromDate}
                onChange={(e) => setConsent({ ...consent, fromDate: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>To Date</label></div>
                <div class="col-75"><input type="date" 
                value={consent.toDate}
                onChange={(e) => setConsent({ ...consent, toDate: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Remarks</label></div>
                <div class="col-75"><input type="text" 
                value={consent.remarks}
                onChange={(e) => setConsent({ ...consent, remarks: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Valid Upto</label></div>
                <div class="col-75"><input type="date" 
                value={consent.validUpto}
                onChange={(e) => setConsent({ ...consent, validUpto: e.target.value })}/> </div>
            </div>
            <input type="submit" onClick={createConsent} value="Create Consent"/>
        </div>
    );
}
export default CreateConsent;