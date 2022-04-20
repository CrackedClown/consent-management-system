import { useState,useEffect } from "react";
import './patientRegistration.css';
import { useLocation } from 'react-router-dom'

function DelegateConsent(){
    const location = useLocation()
    const item=location.state;
    const [delegate,setDelegate] = useState({
        id:item.id,
        validUpto:"",
        consentStatus:"DELEGATED"
    })
    const delegateConsent = (e) => {
        e.preventDefault()
        console.log(JSON.stringify(delegate));
        const requestOptions = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },    
            body: JSON.stringify(delegate)
          };
        fetch("http://daee-103-156-19-229.ngrok.io/consent/delegate", requestOptions)
            .then(response => response.json())
            .then(res => console.log(res));
    }
    return(
        <div>
            <h1>Delegate Consent</h1>
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