import { render } from "@testing-library/react";
import { useState,useEffect } from "react";
import './patientRegistration.css';

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
          
        fetch('http://5bbc-119-161-98-68.ngrok.io/consent/healthcare',requestOptions)
            .then(response => response.json())
            .then(res => setConsent({
                items:res,
                isDataLoaded:true
            }));
        console.log(consent);
    } 
    const [ehr,setEhr]=useState({
        data:[]
    });
    const viewEHR = (item) => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application/json",
            "fromDate":item.fromDate,"toDate":item.toDate,"patientId":item.patientId}
          };
          
        fetch('http://5bbc-119-161-98-68.ngrok.io/ehr',requestOptions)
            .then(response => response.json())
            .then(res => setEhr({
                data:res,
                //isDataLoaded:true
            }));
        console.log(ehr);
    };
    const delegateConsent = (key) => {

    };
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
                                ?<input type="submit" onClick={()=>viewEHR(item)} value="View EHR"/>
                                :<></>
                            }
                        </td>
                        <td>
                            {
                                item.status==='APPROVED'
                                ?<input type="submit" onClick={()=>delegateConsent(item.id)} value="Delegate Consent"/>
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