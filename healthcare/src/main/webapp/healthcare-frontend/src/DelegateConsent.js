import { useState,useEffect } from "react";
import './CSS/form.css';
import { useLocation } from 'react-router-dom'
import Navbar from './Navbar';
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Footer from "./Footer";

function DelegateConsent(){
    const user = JSON.parse(localStorage.getItem('user'));
    const location = useLocation();
    const item=location.state;
    const [delegate,setDelegate] = useState({
        healthProfessionalId:"",
        id:item.id,
        validUpto:"",
        consentStatus:"DELEGATED"
    })
    const delegateConsent = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(delegate));
        const requestOptions = {
            method: "PUT",
            headers: { "Content-Type": "application/json",
                "Authorization": "Bearer "+user.jwtToken },    
            body: JSON.stringify(delegate)
          };
        fetch("http://localhost:8080/consent/delegate", requestOptions)
            .then(response => response.json())
            .then(res => {
                console.log(res);
                setDelegate({
                    healthProfessionalId:"",
                    id:item.id,
                    validUpto:"",
                    consentStatus:"DELEGATED"
                });
                if("id" in res)
                {
                    toast.success('Consent Delegated Successfully'); 
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
            <h1>Delegate Consent</h1>
            <form onSubmit={delegateConsent}>
                <div className="row">
                    <div className="col-25"><label>Healthcare Professional ID</label></div>
                    <div className="col-75"><input type="number" className="in"
                    value={delegate.healthProfessionalId} required
                    onChange={(e) => setDelegate({ ...delegate, healthProfessionalId: e.target.value })}/> </div>
                </div>
                <div className="row">
                    <div className="col-25"><label>Valid Upto</label></div>
                    <div className="col-75"><input type="date" className="in"
                    value={delegate.validUpto} required
                    onChange={(e) => setDelegate({ ...delegate, validUpto: e.target.value })}/> </div>
                </div>
                <input type="submit" className="sb" value="Delegate Consent"/>
            </form>
            </div>
            <Footer/>
        </div>        
    );
}

export default DelegateConsent;