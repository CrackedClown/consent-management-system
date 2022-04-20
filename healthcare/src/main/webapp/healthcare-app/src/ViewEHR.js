import { useState,useEffect } from "react";
import './patientRegistration.css';
import { useLocation } from 'react-router-dom'

function ViewEHR(){
    const location = useLocation()
    const item=location.state;
    useEffect(() => {
        console.log("loaded");
        viewEHR();
    },[]);
    const [ehr,setEhr]=useState({
        data:[],
        isDataLoaded:false
    });
    const viewEHR = () => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application/json",
            "fromDate":item.fromDate,"toDate":item.toDate,"patientId":item.patientId}
          };
          
        fetch('http://daee-103-156-19-229.ngrok.io/ehr',requestOptions)
            .then(response => response.json())
            .then(res => setEhr({
                data:res,
                isDataLoaded:true
            }));
        console.log(ehr);
    };
    if(!ehr.isDataLoaded)
        return <h1>Please wait some time....</h1>;
    return(
        <div>
            <h1>E Health Records</h1>
            <table>
                <tr>
                    <th>Patient ID</th>
                    <th>Hospital ID</th>
                    <th>Healthcare Professional ID</th>
                    <th>Consultation Time</th>
                    <th>Symptoms</th>
                    <th>Prescription</th>
                    <th>Remarks</th>
                </tr>
                {
                    ehr.data.map((d) => (
                        <tr key={d.id}>
                            <td>{d.patientId}</td>
                            <td>{d.hospitalId}</td>
                            <td>{d.healthcareProfessionalId}</td>
                            <td>{d.consultationTime}</td>
                            <td>{d.symptoms}</td>
                            <td>{d.prescription}</td>
                            <td>{d.remarks}</td>
                        </tr> 
                    ))
                }
            </table>
        </div>
    );
}

export default ViewEHR;