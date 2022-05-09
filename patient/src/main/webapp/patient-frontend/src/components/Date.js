import React, { useState } from "react";
import { Link } from "react-router-dom";

export default function Date() {
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");

  const onChangeFromDate = (e) => {
    const f = e.target.value;
    setFromDate(f);
  };

  const onChangeToDate = (e) => {
    const t = e.target.value;
    setToDate(t);
  };

  let d = {
    fromDate: fromDate,
    toDate: toDate,
  };

  return (
    <>
      <form className="row user">
        <div className="col-auto" style={{ marginTop: "30px" }}>
          <label className="form-label" style={{ marginRight: "8px" }}>
            From Date:
          </label>
          <input
            placeholder="yyyy-MM-dd"
            value={fromDate}
            onChange={onChangeFromDate}
          ></input>
        </div>
        <div className="col-auto" style={{ marginTop: "30px" }}>
          <label className="form-label" style={{ marginRight: "8px" }}>
            To Date:
          </label>
          <input
            placeholder="yyyy-MM-dd"
            value={toDate}
            onChange={onChangeToDate}
          ></input>
        </div>
        <Link to="/health-records/ehr" state={d}>
          {" "}
          <button
            className="btn btn-outline-success"
            style={{ marginTop: "30px" }}
          >
            View
          </button>{" "}
        </Link>
      </form>

      <Link to="/dashboard">
        {" "}
        <button className="btn btn-secondary">Dashboard</button>{" "}
      </Link>
    </>
  );
}
