import { useState,useEffect } from "react";
import './CSS/form.css';
import { Link } from "react-router-dom";
import Navbar from './Navbar';
import { format } from 'date-fns';
import Footer from "./Footer";
function ViewConsents(){
    //const [id,setId]=useState({
      //  healthProfessionalId:1,
    //});
    var now = format(new Date(), 'yyyy-MM-dd');
    const user = JSON.parse(localStorage.getItem('user'));
    var role = user.user.role[0].name;
    const [consent,setConsent]=useState({
        items:[],
        isDataLoaded:false
    });
    useEffect(() => {
        console.log("loaded");
        viewConsents();
        console.log(role);
    },[]);
    const viewConsents = (e) => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application/json",
            "healthProfessionalId":user.user.id,
            "Authorization": "Bearer "+user.jwtToken },
          };
          
        fetch('http://localhost:8080/consent/healthcare',requestOptions)
            .then(response => response.json())
            .then(res => setConsent({
                items:res,
                isDataLoaded:true
            }));
        console.log(consent);
    } 
    
    if (!consent.isDataLoaded) 
    return <div> <h1> Pleses wait some time.... </h1> </div> ;
    return(
        <div>
            <Navbar/>
            <div className="container">
            <h1>Consents</h1>
            <table>
                <tr>
                    <th>Patient ID</th>
                    <th>Status</th>
                    <th>From Date</th>
                    <th>To Date</th>
                    <th>Valid Upto</th>
                    <th>Remarks</th>
                    <th>View EHR</th>
                    <th>Delegate Consent</th>
                </tr>  
                {
                consent.items.map((item) => (
                    <tr key={item.id}>
                        <td>{item.patientId}</td>
                        <td>{item.status}</td>
                        <td>{item.fromDate}</td>
                        <td>{item.toDate}</td>
                        <td>{item.validUpto}</td>
                        <td>{item.remarks}</td>
                        <td>
                            {
                                (item.status==='APPROVED' || item.status==='DELEGATED')
                                ?
                                    now<item.validUpto ?
                                    <Link to="/viewConsents/viewEHR" state={item}>
                                        <input type="submit" value="View EHR"/>
                                    </Link>
                                    :<h5 style={{color:"red"}}>Consent Expired</h5>
                                :<></>
                            }
                        </td>
                        <td>
                            {
                                (item.status==='APPROVED' && role==='DOCTOR')
                                ?
                                    now<item.validUpto ?
                                    <Link to="/viewConsents/delegateConsent" state={item}>
                                        <input type="submit" value="Delegate Consent"/>
                                    </Link>
                                    :<h5 style={{color:"red"}}>Consent Expired</h5>
                                :<></>
                            }
                        </td>
                    </tr> 
                ))
                }      
            </table>
            </div>
            <Footer/>
        </div>        
    );
}
export default ViewConsents;