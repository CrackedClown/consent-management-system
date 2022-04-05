import { useState } from "react";
import firebase from './firebase';
import './patientRegistration.css';
import { getAuth,signInWithPhoneNumber,RecaptchaVerifier } from "firebase/auth";
function PatientRegistration(){
  const [patient,setPatient]=useState({
    hid:"",
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
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patient)
      };
      fetch("http://601c-103-156-19-229.ngrok.io/patient/register", requestOptions)
        .then(response => response.json())
        .then(res => console.log(res));
    }
    return(
        <div class="container">
          <h1>Patient Registration</h1>
          
          <div id="sign-in-button"></div>
          <div class="row">
          <div class="col-25"><label>Hospital ID</label></div>
          <div class="col-75"><input type="text" 
          value={patient.hid}
          onChange={(e) => setPatient({ ...patient, hid: e.target.value })}/> </div>
          </div>

          <div class="row">
          <div class="col-25"><label>Patient Name</label></div>
          <div class="col-75"><input type="text" 
          value={patient.patientName}
          onChange={(e) => setPatient({ ...patient, patientName: e.target.value })}/></div>
          </div>

          <div class="row">
          <div class="col-25"><label>Government ID</label></div>
          <div class="col-75"><input type="text" 
          value={patient.governmentId}
          onChange={(e) => setPatient({ ...patient, governmentId: e.target.value })}/></div>
          </div>
        
          <form onSubmit={handleOTP}>
          <div class="row">
          <div class="col-25"><label>Mobile No.</label></div>
          <div class="col-75"><input type="number" 
            value={patient.mobileNum}
            onChange={(e) => setPatient({ ...patient, mobileNum: e.target.value })}/>
              <input type="submit" value="Send OTP"/></div> 
          </div>
          </form>

          <form onSubmit={onSubmitOTP}>
          <div class="row">
            <div class="col-25"><label>OTP</label></div>
            <div class="col-75"><input type="number" name="otp" placeholder="OTP Number" required 
            onChange={(e) => setOTP(e.target.value)}/><input type="submit" value="Verify OTP"/>
            {verified?<h3 style={{color:"green"}}>verified</h3>:<h3 style={{color:"red"}}>Not verified</h3>}</div>
          </div>
          </form>
          <hr></hr>
          <div>
            {
              verified ?
              <div>

                <div class="row">
                <div class="col-25"><label>Patient Name</label></div>
                <div class="col-75"><input type="text" 
                value={patient.patientName}
                disabled/></div>
                </div>

                <div class="row">
                <div class="col-25"><label>Gender</label></div>
                <input type="radio" id="male" name="gender" value="Male"
                onChange={(e) => setPatient({ ...patient, gender: e.target.value })}/>
                <label for="male">Male</label>
                <input type="radio" id="female" name="gender" value="Female"
                onChange={(e) => setPatient({ ...patient, gender: e.target.value })}/>
                <label for="female">Female</label>
                </div>

                <div class="row">
                <div class="col-25"><label>Age</label></div>
                <div class="col-75"><input type="number" 
                value={patient.age}
                onChange={(e) => setPatient({ ...patient, age: e.target.value })}/></div>
                </div>

                <div class="row">
                <div class="col-25"><label>Father's Name</label></div>
                <div class="col-75"><input type="text" 
                value={patient.fathersName}
                onChange={(e) => setPatient({ ...patient, fathersName: e.target.value })}/></div>
                </div>

                <div class="row">
                <div class="col-25"><label>Mother's Name</label></div>
                <div class="col-75"><input type="text" 
                value={patient.id}
                onChange={(e) => setPatient({ ...patient, mothersName: e.target.value })}/></div>
                </div>

                <div class="row">
                <div class="col-25"><label>Government ID</label></div>
                <div class="col-75"><input type="text" 
                value={patient.governmentId}
                disabled/></div>
                </div>
                
                <div class="row">
                <div class="col-25"><label>Marital Status</label></div>
                <input type="radio" id="Yes" name="maritalStatus" value="Yes"
                onChange={(e) => setPatient({ ...patient, maritalStatus: e.target.value })}/>
                <label for="Yes">Yes</label>
                <input type="radio" id="No" name="maritalStatus" value="No"
                onChange={(e) => setPatient({ ...patient, maritalStatus: e.target.value })}/>
                <label for="No">No</label>
                </div>

                <h3>Contact Details</h3>
                <div class="row">
                <div class="col-25"><label>Email</label></div>
                <div class="col-75"><input type="text" 
                value={patient.email}
                onChange={(e) => setPatient({ ...patient, email: e.target.value })}/></div>
                </div>

                <div class="row">
                <div class="col-25"><label>Mobile No</label></div>
                <div class="col-75"><input type="text" 
                value={patient.mobileNum}
                disabled/></div>
                </div>

                <input type="submit" onClick={register} value="Register"/>
              </div>
              :<p></p>
            }
            
          </div>
          

        </div>
    );
}

export default PatientRegistration;