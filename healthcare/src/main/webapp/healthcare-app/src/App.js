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
function App() {
    return (
      <>
        <Router>
          <Routes>
            <Route exact path="/" element={<Home/>} />
            <Route path="/addPatientEHR" element={<AddPatientEHR/>} />
            <Route path="/patientRegistration" element={<PatientRegistration/>} />
            <Route path="/createConsent" element={<CreateConsent/>} />
            <Route path="/viewConsents" element={<ViewConsents/>} />
            <Route path="/viewConsents/viewEHR" element={<ViewEHR/>} />
            <Route path="/viewConsents/delegateConsent" element={<DelegateConsent/>} />
            <Route path="/" element={<Navigate to="/"/>}/>
          </Routes>
        </Router>
      </>
    );
  }
    
  export default App;