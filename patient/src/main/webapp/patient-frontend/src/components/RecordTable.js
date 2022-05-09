/* eslint-disable */

import React, { useEffect, useState } from "react";
import EHRecord from "./Record";
import { Link, useLocation } from "react-router-dom";

function EHRTable() {
  const user = JSON.parse(localStorage.getItem("user"));
  const location = useLocation();
  const item = location.state;

  const [ehr, setEhr] = useState({
    data: [],
    isDataLoaded: false,
  });

  useEffect(() => {
    console.log("loaded");
    console.log(item.fromDate, item.toDate);
    viewEHR();
  }, [ehr.isDataLoaded]);

  const viewEHR = () => {
    const requestOptions = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        patientId: parseInt(user.user.id),
        fromDate: item.fromDate,
        toDate: item.toDate,
        Authorization: "Bearer " + user.jwtToken,
      },
    };

    fetch("http://localhost:8082/ehr", requestOptions)
      .then((response) => response.json())
      .then((res) =>
        setEhr({
          data: res,
          isDataLoaded: true,
        })
      );
    console.log(ehr);
  };

  if (!ehr.isDataLoaded) return <h1>Please wait some time....</h1>;

  return (
    <div>
      <h1>EHR Records</h1>
      <table className="table table-striped">
        <thead style={{ "backgroundColor": "#3F72AF" }}>
          <tr>
            <th scope="col">Hospital ID</th>
            <th scope="col">Healthcare Professional ID</th>
            <th scope="col">Consultation Time</th>
            <th scope="col">Symptoms</th>
            <th scope="col">Prescription</th>
            <th scope="col">Remarks</th>
          </tr>
        </thead>
        <tbody>
          {ehr.data.map((d) => (
            <tr key={d.id}>
              <td>{d.hospitalId}</td>
              <td>{d.healthcareProfessionalId}</td>
              <td>{d.consultationTime}</td>
              <td>{d.symptoms}</td>
              <td>{d.prescription}</td>
              <td>{d.remarks}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/health-records">
      <button className="btn btn-outline-primary">
        Refresh
      </button>
      </Link>
    </div>
  );
}

export default EHRTable;
