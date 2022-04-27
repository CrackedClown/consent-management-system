import { useState,useEffect } from "react";
import './patientRegistration.css';
import { useLocation } from 'react-router-dom'
import Navbar from './Navbar';

function DelegateConsent(){
    const user = JSON.parse(localStorage.getItem('user'));
    const location = useLocation();
    const item=location.state;
    const [delegate,setDelegate] = useState({
        id:"",
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
        fetch("http://f2cb-103-156-19-229.ngrok.io/consent/delegate", requestOptions)
            .then(response => response.json())
            .then(res => console.log(res));
    }
    return(
        <div>
            <Navbar/>
            <h1>Delegate Consent</h1>
            <div class="row">
                <div class="col-25"><label>Healthcare Professional ID</label></div>
                <div class="col-75"><input type="number" 
                value={delegate.id}
                onChange={(e) => setDelegate({ ...delegate, id: e.target.value })}/> </div>
            </div>
            <div class="row">
                <div class="col-25"><label>Valid Upto</label></div>
                <div class="col-75"><input type="date" 
                value={delegate.validUpto}
                onChange={(e) => setDelegate({ ...delegate, validUpto: e.target.value })}/> </div>
            </div>
            <input type="submit" onClick={delegateConsent} value="Delegate Consent"/>
        </div>        
    );
}

export default DelegateConsent;