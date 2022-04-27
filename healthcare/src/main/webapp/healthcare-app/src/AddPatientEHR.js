import { useState } from "react";
import './patientRegistration.css';
import { format } from 'date-fns';
import Navbar from './Navbar';

function AddPatientEHR(){
    const user = JSON.parse(localStorage.getItem('user'));
    const [record,setRecord]=useState({
        patientId:"",
        symptoms:"",
        prescription:"",
        remarks:"",
        consultationTime:format(new Date(), 'yyyy-MM-dd'),
        healthProfessionalId:user.user.id
      });
    //if (user && user.jwtToken) {
      //  return { Authorization: 'Bearer ' + user.accessToken };
    //}
    const addRecord = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(record));
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json",
                "Authorization": "Bearer "+user.jwtToken },
            body: JSON.stringify(record)
          };
          fetch("http://f2cb-103-156-19-229.ngrok.io/ehr", requestOptions)
            .then(response => response.json())
            .then(res => console.log(res));
    } 
    return(
        <div class="container">
            <Navbar/>
            <h1>Add New EHR</h1>
            <div class="row">
                <div class="col-25"><label>Patient ID</label></div>
                <div class="col-75"><input type="number" 
                value={record.patientId}
                onChange={(e) => setRecord({ ...record, patientId: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Symptoms</label></div>
                <div class="col-75"><input type="text" 
                value={record.symptoms}
                onChange={(e) => setRecord({ ...record, symptoms: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Prescription</label></div>
                <div class="col-75"><input type="text" 
                value={record.prescription}
                onChange={(e) => setRecord({ ...record, prescription: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Remarks</label></div>
                <div class="col-75"><input type="text" 
                value={record.remarks}
                onChange={(e) => setRecord({ ...record, remarks: e.target.value })}/> </div>
            </div>
            <input type="submit" onClick={addRecord} value="Add"/>
        </div>
    );
}
export default AddPatientEHR;