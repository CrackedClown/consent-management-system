import { useState } from "react";
import './CSS/form.css';
import { format } from 'date-fns';
import Navbar from './Navbar';
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Footer from "./Footer";

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
    const addRecord = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(record));
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json",
                "Authorization": "Bearer "+user.jwtToken },
            body: JSON.stringify(record)
          };
          fetch("http://1091-119-161-98-68.ngrok.io/ehr", requestOptions)
            .then(response => response.json())
            .then(res => {
                console.log(res);
                setRecord({
                    patientId:"",
                    symptoms:"",
                    prescription:"",
                    remarks:"",
                    consultationTime:format(new Date(), 'yyyy-MM-dd'),
                    healthProfessionalId:user.user.id
                });
                if("id" in res)
                {
                    toast.success('Record Added Successfully'); 
                } 
                else
                {
                    toast.error(res.error);
                }
            });
    } 
    return(
        <div>
            <Navbar/>
            <ToastContainer/>
            <div className="container">
            <h1>Add New EHR</h1>
            <form onSubmit={addRecord}>
                <div className="row">
                    <div className="col-25"><label>Patient ID</label></div>
                    <div className="col-75"><input type="number" className="in"
                    value={record.patientId} required
                    onChange={(e) => setRecord({ ...record, patientId: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Symptoms</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={record.symptoms} required
                    onChange={(e) => setRecord({ ...record, symptoms: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Prescription</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={record.prescription} required
                    onChange={(e) => setRecord({ ...record, prescription: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Remarks</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={record.remarks} required
                    onChange={(e) => setRecord({ ...record, remarks: e.target.value })}/> </div>
                </div>
                <input type="submit" className="sb" value="Add"/>
            </form>
            </div>
            <Footer/>
        </div>
    );
}
export default AddPatientEHR;