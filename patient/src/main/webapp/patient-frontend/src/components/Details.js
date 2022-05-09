/* eslint-disable */
import React from "react";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../global.css";

function Details() {
  const user = JSON.parse(localStorage.getItem("user"));

  const [details, setDetails] = useState({});
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    console.log("loaded");
    const requestOptions = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        patientId: parseInt(user.user.id),
        Authorization: "Bearer " + user.jwtToken,
      },
    };

    fetch(
      "http://localhost:8082/patient",
      requestOptions
    )
      .then((response) => response.json())
      .then((res) => {
        setDetails(res);
        setLoading(true);
      });
    console.log(details);
  }, [loading]);

  if (!loading) return <h1>Please wait some time....</h1>;

  return (
    <div>
      <h1>User Details</h1>
      <table className="table table-striped table-hover user">
        <tbody>
          <tr>
            <td>Patient ID</td>
            <td> {details.id} </td>
          </tr>
          <tr>
            <td>Name</td>
            <td> {details.patientName} </td>
          </tr>
          <tr>
            <td>Age</td>
            <td> {details.age} </td>
          </tr>
          <tr>
            <td>Gender</td>
            <td> {details.gender} </td>
          </tr>
          <tr>
            <td>Father's Name</td>
            <td> {details.fathersName} </td>
          </tr>
          <tr>
            <td>Mother's Name</td>
            <td> {details.mothersName} </td>
          </tr>
          <tr>
            <td>Email Address</td>
            <td> {details.email} </td>
          </tr>
          <tr>
            <td>Mobile Number</td>
            <td> {details.mobileNum} </td>
          </tr>
        </tbody>
      </table>
      <Link to="/dashboard">
        {" "}
        <button className="btn btn-secondary">Dashboard</button>{" "}
      </Link>
    </div>
  );
}

export default Details;
