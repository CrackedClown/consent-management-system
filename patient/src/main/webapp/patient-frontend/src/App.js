import "./global.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Consent from "./components/Consent";
import Details from "./components/Details";
import Cards from "./components/Cards";
import EHRecord from "./components/Record";
import EHRTable from "./components/RecordTable";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Cards />} />
        <Route path="/consents" element={<Consent />} />
        <Route path="/user-details" element={<Details />} />
        <Route path="/health-records" element={<EHRecord />} />
        <Route path="/health-records/ehr" element={<EHRTable />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
