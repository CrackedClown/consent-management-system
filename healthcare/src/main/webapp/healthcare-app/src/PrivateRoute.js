import React from 'react';
import { Route, Navigate, Outlet } from 'react-router-dom';
import AuthService from "./services/auth";

const PrivateRoute = () => {
    const auth = (localStorage.getItem("user") !== null);
    return auth ? <Outlet /> : <Navigate to="/" />;
}

export default PrivateRoute;