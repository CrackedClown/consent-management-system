import { useState } from "react";
import './CSS/form.css';
import Navbar from './Navbar';
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Footer from "./Footer";
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
          fetch("http://1091-119-161-98-68.ngrok.io/consent", requestOptions)
            .then(response => response.json())
            .then(res => {
                console.log(res);
                setConsent({
                    hid:user.user.hospitalInformation.hid,
                    healthProfessionalId:user.user.id,
                    patientId:"",
                    fromDate:"",
                    toDate:"",
                    remarks:"",
                    validUpto:""
                });
                if("id" in res)
                {
                    toast.success('Consent Request Generated'); 
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
            <h1>Create Consent</h1>
            <form onSubmit={createConsent}>
                <div className="row">
                    <div className="col-25"><label>Patient ID</label></div>
                    <div className="col-75"><input type="number" className="in"
                    value={consent.patientId} required
                    onChange={(e) => setConsent({ ...consent, patientId: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>From Date</label></div>
                    <div className="col-75"><input type="date" className="in"
                    value={consent.fromDate} required
                    onChange={(e) => setConsent({ ...consent, fromDate: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>To Date</label></div>
                    <div className="col-75"><input type="date" className="in" min={consent.fromDate}
                    value={consent.toDate} required
                    onChange={(e) => setConsent({ ...consent, toDate: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Remarks</label></div>
                    <div className="col-75"><input type="text" className="in"
                    value={consent.remarks} required
                    onChange={(e) => setConsent({ ...consent, remarks: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Valid Upto</label></div>
                    <div className="col-75"><input type="date" className="in" min={consent.toDate}
                    value={consent.validUpto} required
                    onChange={(e) => setConsent({ ...consent, validUpto: e.target.value })}/> </div>
                </div>
                <input type="submit" className="sb" value="Create Consent"/>
            </form>
            </div>
            <Footer/>
        </div>
    );
}
export default CreateConsent;