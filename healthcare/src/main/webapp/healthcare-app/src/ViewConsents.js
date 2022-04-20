import { useState,useEffect } from "react";
import './patientRegistration.css';
import { Link } from "react-router-dom";

function ViewConsents(){
    //const [id,setId]=useState({
      //  healthProfessionalId:1,
    //});
    const [consent,setConsent]=useState({
        items:[],
        isDataLoaded:false
    });
    useEffect(() => {
        console.log("loaded");
        viewConsents();
    },[]);
    const viewConsents = (e) => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application/json",
            "healthProfessionalId":1 },
          };
          
        fetch('http://daee-103-156-19-229.ngrok.io/consent/healthcare',requestOptions)
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
                                item.status==='APPROVED'
                                ?<Link to="/viewConsents/viewEHR" state={item}>
                                    <input type="submit" value="View EHR"/>
                                 </Link>
                                :<></>
                            }
                        </td>
                        <td>
                            {
                                item.status==='APPROVED'
                                ?<Link to="/viewConsents/delegateConsent" state={item}>
                                    <input type="submit" value="Delegate Consent"/>
                                 </Link>
                                :<></>
                            }
                        </td>
                    </tr> 
                ))
                }      
            </table>
            
        </div>        
    );
}
export default ViewConsents;