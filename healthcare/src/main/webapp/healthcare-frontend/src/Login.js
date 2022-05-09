import React, { useState, useRef } from "react";
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "./services/auth";
import { Link } from "react-router-dom";
import "./CSS/login.css";
import Footer from "./Footer";
import Header from "./Header";
const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};
const Login = () => {
  let navigate = useNavigate();
  const form = useRef();
  const checkBtn = useRef();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };
  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };
  const handleLogin = (e) => {
    e.preventDefault();
    setMessage("");
    setLoading(true);
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(username, password).then(
        () => {
          navigate("/home");
          window.location.reload();
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          setLoading(false);
          setMessage(resMessage);
        }
      );
    } else {
      setLoading(false);
    }
  };
  return (
    <div>
      <Header/>
      <div>
        <ul className="loginbar">
          <li className="active"><Link to="/" className="l">Health Professional Login</Link></li>
          <li><Link to="/adminLogin" className="l">Admin Login</Link></li>
        </ul>
      </div>
      <div className="main">
        
        <Form onSubmit={handleLogin} ref={form} className="form_class">
          <div><h2>Health Professional Login</h2></div>
          <div className="form_div">
            <div>
              <label htmlFor="username">Username</label>
              <Input
                type="text"
                className="field_class"
                name="username"
                value={username}
                onChange={onChangeUsername}
                validations={[required]}
              />
            </div>
            <div>
              <label htmlFor="password">Password</label>
              <Input
                type="password"
                className="field_class"
                name="password"
                value={password}
                onChange={onChangePassword}
                validations={[required]}
              />
            </div>
          </div>
          
          <div>
            <button disabled={loading} className="submit_class">
              {loading && (
                <span className="spinner-border spinner-border-sm"></span>
              )}
              <span>Login</span>
            </button>
          </div>
          {message && (
            <div className="form-group">
              <div className="alert alert-danger" role="alert">
                {message}
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
      <Footer/>
    </div>
  );
};
export default Login;