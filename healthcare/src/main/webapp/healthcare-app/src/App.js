import PatientRegistration from './PatientRegistration';
import AddPatientEHR from './AddPatientEHR';
import Home from './Home';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";

function App() {
    return (
      <>
        <Router>
          <Routes>
            <Route exact path="/" element={<Home/>} />
            <Route path="/addPatientEHR" element={<AddPatientEHR/>} />
            <Route path="/patientRegistration" element={<PatientRegistration/>} />
            <Route path="/" element={<Navigate to="/"/>}/>
          </Routes>
        </Router>
      </>
    );
  }
    
  export default App;