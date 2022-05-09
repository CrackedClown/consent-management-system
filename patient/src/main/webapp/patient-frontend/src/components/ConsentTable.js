/* eslint-disable */

import React from "react";
import { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../global.css";

function ConsentTable() {
  const user = JSON.parse(localStorage.getItem("user"));
  const [date, setDate] = useState("");
  const [id, setId] = useState();
  let navigate = useNavigate();

  const onChangeDate = (e) => {
    const dt = e.target.value;
    setDate(dt);
  };

  const onChangeId = (e) => {
    const id = e.target.value;
    setId(id);
  };

  const accept = () => {
    let data = {
      id: id,
      validUpto: date,
      consentStatus: "APPROVED",
    };

    fetch(
      "http://localhost:8082/consent",
      {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + user.jwtToken,
        },
      }
    );

    navigate("/dashboard");
  };

  const reject = () => {
    let data = {
      id: id,
      validUpto: date,
      consentStatus: "REJECTED",
    };

    fetch(
      "http://localhost:8082/consent",
      {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + user.jwtToken,
        },
      }
    );

    navigate("/dashboard");
  };

  const [consent, setConsent] = useState({
    items: [],
    isDataLoaded: false,
  });

  useEffect(() => {
    console.log("loaded");
    viewConsents();
  }, [consent.isDataLoaded]);

  const viewConsents = (e) => {
    const requestOptions = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        patientId: parseInt(user.user.id),
        Authorization: "Bearer " + user.jwtToken,
      },
    };

    fetch(
      "http://localhost:8082/consent",
      requestOptions
    )
      .then((response) => response.json())
      .then((res) =>
        setConsent({
          items: res,
          isDataLoaded: true,
        })
      );
    console.log(consent);
  };

  if (!consent.isDataLoaded)
    return (
      <div>
        <h2 className="text-muted" style={{ marginTop: "30px" }}>
          Please wait for some time....
        </h2>
      </div>
    );

  return (
    <div>
      <table className="table table-striped">
        <thead style={{ backgroundColor: "#3F72AF" }}>
          <tr>
            <th scope="col">Doctor ID</th>
            <th scope="col">Consent ID</th>
            <th scope="col">From Date</th>
            <th scope="col">To Date</th>
            <th scope="col">Valid upto</th>
            <th scope="col">Remarks</th>
            <th scope="col">Status</th>
            <th scope="col">Edit Valid upto Date</th>
            <th scope="col">Enter Consent ID</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {consent.items.map((item) => (
            <tr key={item.id}>
              <td>{item.healthProfessionalId}</td>
              <td>{item.id}</td>
              <td>{item.fromDate}</td>
              <td>{item.toDate}</td>
              <td className="valid-upto">{item.validUpto}</td>
              <td>{item.remarks}</td>
              <td id="txt">{item.status}</td>
              <td style={{ textAlign: "center" }}>
                {item.status === "PENDING" ? (
                  <input
                    type="text"
                    placeholder="Valid date (yyyy-dd-mm)"
                    onChange={onChangeDate}
                    style={{ margin: "auto" }}
                  ></input>
                ) : (
                  <></>
                )}
              </td>
              <td>
                {item.status === "PENDING" ? (
                  <input
                    type="text"
                    placeholder="Consent Id"
                    onChange={onChangeId}
                    style={{ margin: "auto" }}
                  ></input>
                ) : (
                  <></>
                )}
              </td>
              <td id="action">
                {item.status === "PENDING" ? (
                  <>
                    <button
                      className="btn-outline-success"
                      onClick={accept}
                      id="edit-btn"
                    >
                      APPROVE
                    </button>
                    <button className="btn-outline-danger" onClick={reject}>
                      REJECT
                    </button>
                  </>
                ) : (
                  <></>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <Link to="/dashboard">
        {" "}
        <button className="btn btn-secondary">Dashboard</button>{" "}
      </Link>
    </div>
  );
}

export default ConsentTable;
