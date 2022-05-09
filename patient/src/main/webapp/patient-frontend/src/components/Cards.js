import React from "react";
import { Link } from "react-router-dom";
import AuthService from "../services/auth";
import "../global.css";

function Cards() {
  return (
    <>
      <h1>Welcome to Patient Portal</h1>
      <div className="row">
        <div className="col-sm-4">
          <div className="card">
            <div className="card-body">
              <img src="medical-record.png" alt="EHR Record"></img>
              <br />
              <Link to="/health-records">
                <button type="button" className="btn btn-primary">
                  View EHR
                </button>
              </Link>
            </div>
          </div>
        </div>
        <div className="col-sm-4">
          <div className="card">
            <div className="card-body">
              <img src="donor-consent-form.png" alt="consent form"></img>
              <br />
              <Link to="/consents">
                <button type="button" className="btn btn-primary">
                  View Consent
                </button>
              </Link>
            </div>
          </div>
        </div>
        <div className="col-sm-4">
          <div className="card">
            <div className="card-body">
              <img src="patient.png" alt="user details"></img>
              <br />
              <Link to="/user-details">
                <button type="button" className="btn btn-primary">
                  View Details
                </button>
              </Link>
            </div>
          </div>
        </div>
      </div>
      <Link to="/">
        <button style={{"marginTop": "30px"}} className="btn btn-outline-danger" onClick={AuthService.logout}>
          Logout
        </button>
      </Link>
    </>
  );
}

export default Cards;
