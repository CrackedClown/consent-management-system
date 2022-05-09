import { useState,useEffect } from "react";
import './CSS/form.css';
import { useLocation } from 'react-router-dom'
import Navbar from './Navbar';
import Footer from './Footer';
function ViewEHR(){
    const user = JSON.parse(localStorage.getItem('user'));
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
            "fromDate":item.fromDate,"toDate":item.toDate,"patientId":item.patientId,
            "Authorization": "Bearer "+user.jwtToken}
          };
          
        fetch('http://1091-119-161-98-68.ngrok.io/ehr',requestOptions)
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
            <Navbar/>
            <div className="container">
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
            <Footer/>
        </div>
    );
}

export default ViewEHR;