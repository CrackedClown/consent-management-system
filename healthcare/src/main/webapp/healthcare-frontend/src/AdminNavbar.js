import React from "react";
import {
  AppBar,
  Toolbar,
  CssBaseline,
  Typography,
  makeStyles,
} from "@material-ui/core";
import { Link } from "react-router-dom";
import AuthService from "./services/auth";
import { useNavigate } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
  navlinks: {
    marginLeft: theme.spacing(10),
    display: "flex",
  },
 logo: {
    flexGrow: "1",
    cursor: "pointer",
  },
  link: {
    textDecoration: "none",
    color: "white",
    fontSize: "20px",
    marginLeft: theme.spacing(20),
    "&:hover": {
      color: "yellow",
      borderBottom: "1px solid white",
    },
  },
  navbar: {
    backgroundColor:"black",
  },
}));

function AdminNavbar() {
  let navigate = useNavigate();
  const classes = useStyles();
    const handleLogout =(e) => {
        e.preventDefault();
        AuthService.logout()
        navigate("/adminLogin");
        window.location.reload();
    }
  return (
    <AppBar position="static">
      <CssBaseline />
      <Toolbar className={classes.navbar}>
        <Typography variant="h4" className={classes.logo}>
          Healthcare Portal
        </Typography>
          <div className={classes.navlinks}>
            <Link to="/admin/home" className={classes.link}>
              Home
            </Link>
            <Link to="" className={classes.link} onClick={handleLogout}>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
              Logout
            </Link>
          </div>
      </Toolbar>
    </AppBar>
  );
}
export default AdminNavbar;