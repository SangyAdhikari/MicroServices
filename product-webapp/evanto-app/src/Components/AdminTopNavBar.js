import React from "react";
import { Col, Row } from "reactstrap";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import { Link } from "react-router-dom";
import "../CSS/AdminTopNavBar.css";


export default function AdminTopNavBar({ email, handleDrawerOpen }) {

  const handleLogout = () =>{
    sessionStorage.removeItem('logindetails')
  }
  return (
    <Row className="text-orange p-3 fixed-top topnavbar d-flex align-items-center" style={{zIndex: 1205}} >
       {/* <div className="text-start col-2" onClick={handleDrawerOpen} >

       </div> */}
       <div className="brandlogo">
       
       <Link to={'/home'} className="text-decoration-none"> <img src="https://drive.google.com/uc?export=view&id=1k0fXDCm15MTgwyHQS3r1Y8EQG-XJjC93" className="w-100" alt="logo"/></Link>
       </div>
      
      <Col className="text-end">
        <div className="mydropdown">
          <div className="mydropbtn text-orange d-flex align-items-center">
            <p className="pe-2"> <i className="fa-solid fa-user text-orange"></i></p>
            <p className="mx-1 d-none d-sm-block pe-1 text-light fs-6">{email}</p>
            {/* <AccountCircleIcon /> */}
            <KeyboardArrowDownIcon className="text-light" />
          </div>
          <div className="mydropdown-content" onClick={handleLogout}>
            <Link to={'/'}><div><i className="fa-solid fa-right-from-bracket pe-2"></i>Logout</div></Link>
          </div>
        </div>
      </Col>
    </Row>
  );
}
