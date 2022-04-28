import PatientRegistration from './PatientRegistration';
import AddPatientEHR from './AddPatientEHR';
import Home from './Home';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import CreateConsent from './CreateConsent';
import ViewConsents from './ViewConsents';
import ViewEHR from './ViewEHR';
import DelegateConsent from './DelegateConsent';
import Login from './Login';
import AdminLogin from './AdminLogin';
import AdminHome from './AdminHome';
import AddDoctor from './AddDoctor';
import RemoveDoctor from './RemoveDoctor';
import PrivateRoute from './PrivateRoute';
function App() {
    return (
      <>
        <Router>
          <Routes>
            <Route exact path="/" element={<Login/>} />
            <Route exact path="/adminLogin" element={<AdminLogin/>} />
            <Route exact path="/home" element={<PrivateRoute/>}>
              <Route exact path="/home" element={<Home/>}/>
            </Route>
            <Route exact path="/admin/home" element={<PrivateRoute/>} >
              <Route exact path="/admin/home" element={<AdminHome/>} />
            </Route>
            <Route exact path="/admin/addDoctor" element={<PrivateRoute/>} >
              <Route exact path="/admin/addDoctor" element={<AddDoctor/>} />
            </Route>
            <Route exact path="/admin/removeDoctor" element={<PrivateRoute/>} >
              <Route exact path="/admin/removeDoctor" element={<RemoveDoctor/>} />
            </Route>
            <Route exact path="/addPatientEHR" element={<PrivateRoute/>} >
              <Route exact path="/addPatientEHR" element={<AddPatientEHR/>} />
            </Route>
            <Route exact path="/patientRegistration" element={<PrivateRoute/>} >
              <Route exact path="/patientRegistration" element={<PatientRegistration/>} />
            </Route>
            <Route exact path="/createConsent" element={<PrivateRoute/>} >
              <Route exact path="/createConsent" element={<CreateConsent/>} />
            </Route>
            <Route exact path="/viewConsents" element={<PrivateRoute/>} >
              <Route exact path="/viewConsents" element={<ViewConsents/>} />
            </Route>
            <Route exact path="/viewConsents/viewEHR" element={<PrivateRoute/>} >
              <Route exact path="/viewConsents/viewEHR" element={<ViewEHR/>} />
            </Route>
            <Route exact path="/viewConsents/delegateConsent" element={<PrivateRoute/>} >
              <Route exact path="/viewConsents/delegateConsent" element={<DelegateConsent/>} />
            </Route>
          </Routes>
        </Router>
      </>
    );
  }
    
  export default App;