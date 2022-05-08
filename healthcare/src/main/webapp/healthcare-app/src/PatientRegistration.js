import { useState } from "react";
import firebase from './firebase';
import './CSS/form.css';
import { getAuth,signInWithPhoneNumber,RecaptchaVerifier } from "firebase/auth";
import Navbar from './Navbar';
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Footer from "./Footer";
function PatientRegistration(){
  const user = JSON.parse(localStorage.getItem('user'));
  const [patient,setPatient]=useState({
    hid:user.user.hospitalInformation.hid,
    patientName:"",
    governmentId:"",
    mobileNum:"",
    mothersName:"",
    fathersName:"",
    gender:"",
    age:"",
    maritalStatus:"",
    email:""
  });
    const [otp, setOTP] = useState();
    const [verified,setVerified]=useState(false);
    const auth = getAuth();
    const configureCaptcha = () =>{
        window.recaptchaVerifier = new RecaptchaVerifier('sign-in-button', {
          'size': 'invisible',
          'callback': (response) => {
            // reCAPTCHA solved, allow signInWithPhoneNumber.
            handleOTP();
            console.log("Recaptca verified")
          },
          defaultCountry: "IN"
        },auth);
      }
    const handleOTP = (e) => {
        e.preventDefault()
        configureCaptcha()
        const phoneNumber = "+91" + patient.mobileNum;
        console.log(phoneNumber)
        const appVerifier = window.recaptchaVerifier;
        signInWithPhoneNumber(auth,phoneNumber, appVerifier)
        .then((confirmationResult) => {
          // SMS sent. Prompt user to type the code from the message, then sign the
          // user in with confirmationResult.confirm(code).
          window.confirmationResult = confirmationResult;
          console.log("OTP has been sent")
        }).catch((error) => {
          console.log("SMS not sent")
        });
        
      }
    const onSubmitOTP = (e) =>{
        e.preventDefault()
        const code = otp
        console.log(code)
        window.confirmationResult.confirm(code).then((result) => {
          // User signed in successfully.
          const user = result.user;
          //console.log(JSON.stringify(user))
          setVerified(true);
          // ...
        }).catch((error) => {
          // User couldn't sign in (bad verification code?)
          // ..
        });
      }
    
    const register = (e) =>{
      e.preventDefault();
      console.log(JSON.stringify(patient));
         
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json",
        "Authorization": "Bearer "+user.jwtToken },
        body: JSON.stringify(patient)
      };
      fetch("http://1091-119-161-98-68.ngrok.io/patient/register", requestOptions)
        .then(response => response.json())
        .then(res => {
          console.log(res);
          setPatient({
            hid:user.user.hospitalInformation.hid,
            patientName:"",
            governmentId:"",
            mobileNum:"",
            mothersName:"",
            fathersName:"",
            gender:"",
            age:"",
            maritalStatus:"",
            email:""
          });
          if("id" in res)
          {
              toast.success('Patient Registered Successfully'); 
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
          <h1>Patient Registration</h1>
          
          <div id="sign-in-button"></div>
          <form onSubmit={register}>
            <div className="row">
            <div className="col-25"><label>Patient Name</label></div>
            <div className="col-75"><input type="text" className="in"
            value={patient.patientName} required
            onChange={(e) => setPatient({ ...patient, patientName: e.target.value })}/></div>
            </div>

            <div className="row">
            <div className="col-25"><label>Government ID</label></div>
            <div className="col-75"><input type="text" className="in" pattern="[0-9]{12}" title="must be in 12 digits"
            value={patient.governmentId} required
            onChange={(e) => setPatient({ ...patient, governmentId: e.target.value })}/></div>
            </div>
          
            <div className="row">
            <div className="col-25"><label>Mobile No.</label></div>
            <div className="col-75"><input type="text" className="ins" pattern="[0-9]{10}" title="must be in 10 digits"
              value={patient.mobileNum} required
              onChange={(e) => setPatient({ ...patient, mobileNum: e.target.value })}/>
                <input type="submit" onClick={handleOTP} value="Send OTP"/></div> 
            </div>

            <div className="row">
              <div className="col-25"><label>OTP</label></div>
              <div className="col-75"><input type="number" name="otp" placeholder="OTP Number" className="ins" required 
              onChange={(e) => setOTP(e.target.value)}/><input type="submit" onClick={onSubmitOTP} value="Verify OTP"/>
              {verified?<h3 style={{color:"green"}}>verified</h3>:<h3 style={{color:"red"}}>Not verified</h3>}</div>
            </div>
            
            <hr></hr>
            <div>
              {
                verified ?
                <div>

                  <div className="row">
                  <div className="col-25"><label>Patient Name</label></div>
                  <div className="col-75"><input type="text" className="in"
                  value={patient.patientName} required
                  disabled/></div>
                  </div>

                  <div className="row">
                  <div className="col-25"><label>Gender</label></div>
                  <input type="radio" id="male" name="gender" value="Male" required
                  onChange={(e) => setPatient({ ...patient, gender: e.target.value })}/>
                  <label htmlFor="male">Male</label>
                  <input type="radio" id="female" name="gender" value="Female" required
                  onChange={(e) => setPatient({ ...patient, gender: e.target.value })}/>
                  <label htmlFor="female">Female</label>
                  </div>

                  <div className="row">
                  <div className="col-25"><label>Age</label></div>
                  <div className="col-75"><input type="number" className="in"
                  value={patient.age} required
                  onChange={(e) => setPatient({ ...patient, age: e.target.value })}/></div>
                  </div>

                  <div className="row">
                  <div className="col-25"><label>Father's Name</label></div>
                  <div className="col-75"><input type="text" className="in"
                  value={patient.fathersName} required
                  onChange={(e) => setPatient({ ...patient, fathersName: e.target.value })}/></div>
                  </div>

                  <div className="row">
                  <div className="col-25"><label>Mother's Name</label></div>
                  <div className="col-75"><input type="text" className="in"
                  value={patient.id} required
                  onChange={(e) => setPatient({ ...patient, mothersName: e.target.value })}/></div>
                  </div>

                  <div className="row">
                  <div className="col-25"><label>Government ID</label></div>
                  <div className="col-75"><input type="text" className="in"
                  value={patient.governmentId} required
                  disabled/></div>
                  </div>
                  
                  <div className="row">
                  <div className="col-25"><label>Marital Status</label></div>
                  <input type="radio" id="Yes" name="maritalStatus" value="Yes" required
                  onChange={(e) => setPatient({ ...patient, maritalStatus: e.target.value })}/>
                  <label htmlFor="Yes">Yes</label>
                  <input type="radio" id="No" name="maritalStatus" value="No" required
                  onChange={(e) => setPatient({ ...patient, maritalStatus: e.target.value })}/>
                  <label htmlFor="No">No</label>
                  </div>

                  <h3>Contact Details</h3>
                  <div className="row">
                  <div className="col-25"><label>Email</label></div>
                  <div className="col-75"><input type="email" className="in"
                  value={patient.email} required
                  onChange={(e) => setPatient({ ...patient, email: e.target.value })}/></div>
                  </div>

                  <div className="row">
                  <div className="col-25"><label>Mobile No</label></div>
                  <div className="col-75"><input type="text" className="in"
                  value={patient.mobileNum} required
                  disabled/></div>
                  </div>

                  <input type="submit" className="sb" value="Register"/>
                </div>
                :<p></p>
              }
                        
            </div>
          </form>
          </div>
          <Footer/>
        </div>
    );
}

export default PatientRegistration;