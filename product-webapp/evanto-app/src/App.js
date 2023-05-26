import {  Routes, Route, useLocation } from "react-router-dom";
import { Row, Col } from "reactstrap";
import "./CSS/App.css";
import Home from "./Components/Pages/Home/Home";
import Venue from "./Components/Pages/Venue/Venue";
import Seatselection from "./Components/Pages/Seatselection/Seatselection";
import Login from "./Components/Pages/Loginpage/Login";
import Signup from "./Components/Pages/Signuppage/Signup";
import Userhistory from "./Components/Pages/Userhistory/Userhistory";
import Ticketdownload from "./Components/Pages/Ticketdownload/Ticketdownload";
import Landing from "./Components/Pages/Landing/Landing";
import React from "react";
import VenueSelect from "./Components/Pages/VenueSelect";
import CreateEvent from "./Components/Pages/CreateEvent";
import ShowsHistory from "./Components/Pages/ShowsHistory";
import UpdateProfile from "./Components/Pages/UpdateProfile";
import TheaterList from "./Components/Pages/TheaterList";
import "./CSS/App.css";
import { useState } from "react";
import AdminTopNavBar from "./Components/AdminTopNavBar";
import { useEffect } from "react";
import ShowBookingHistory from "./Components/Pages/ShowBookingHistory";
import { getUserLocation } from './Services/LocationService';
import RequireAuth from './Components/RequireAuth';
import Unauthorized from "./Components/Pages/Unauthorized";
import SideMenuBar from "./Components/SideMenuBar";
// import { useLocation } from 'react-router-dom';



function App() {
  const location = useLocation();

  let [isUser, setIsUser] = useState(false);
  let [userType, setUserType] = useState("");
  let [email, setEmail] = useState("");
  let [showContnet, toggleContent] = useState(false);

  useEffect(() => {
  
    if (location.pathname === "/login" || location.pathname === "/signup" || location.pathname === "/") {
      toggleContent(false)
    } else {
      toggleContent(true)
    }
    getSession()


    const successCallback = (position) => {

      getUserLocation(position, (res) => {
        if (res.results[0].components) {
          sessionStorage.setItem("Locationcomponent", res.results[0].components.state_district)
        }
      }, (err) => {
        console.log(err)
      })

    }

    window.navigator.geolocation
      .getCurrentPosition(successCallback);

  }, [location])


  const getSession = () => {
    let UserAccount = sessionStorage.getItem("logindetails");
    UserAccount = JSON.parse(UserAccount);
    if (UserAccount === null) {
      return;
    }
    let { userEmail, userType } = UserAccount;
    setEmail(userEmail)
    setUserType(userType)

  }

  return (


    <div className="App-body" id="app">


      <div className={isUser ? "container" : "container-fluid"} id="App">
        {
          showContnet ?
            <>
              {!isUser ? (
                <Row>
                  <Col md={12} style={{ height: 60 }}>
                    <AdminTopNavBar email={email} />
                  </Col>
                </Row>
              ) : null}
            </>
            :
            null
        }


        <Row className="justify-content-center">

          {
            showContnet ?
              <div style={{ width: 65 }}>
                <SideMenuBar userType={userType} />
              </div>
              :
              null
          }


          <div className="px-0">
            <Routes>
              <Route path="/" element={<Landing />} />
              <Route path="signup" element={<Signup />} />
              <Route path="login" element={<Login />} />


              <Route element={<RequireAuth allowedRoles={['User', 'Organizer']} />}>
                <Route path="/Home" element={<Home />} />
                <Route path="/UpdateProfile" element={<UpdateProfile />} />
              </Route>

              <Route element={<RequireAuth allowedRoles={['User']} />}>
                <Route path="/venue/:eventId" element={<Venue />} />
                <Route path="/selectseat/:showId" element={<Seatselection />} />
                <Route path="/userhistory" element={<Userhistory />} />
                <Route path="/ticket/:bookingID" element={<Ticketdownload />} />
                <Route path="/venueSelect/:movie_name" element={<VenueSelect />} />
              </Route>

              <Route element={<RequireAuth allowedRoles={['Organizer']} />}>
                <Route path="/CreateEvent/:eventId" element={<CreateEvent />} />
                <Route path="/ShowsHistory" element={<ShowsHistory />} />
                <Route path="/ShowBookingHistory/:theatreId/:showid" element={<ShowBookingHistory />} />
                <Route path="/TheaterList" element={<TheaterList />} />
              </Route>

              <Route path="*" element={<Unauthorized />} />
            </Routes>
          </div>
        </Row>
      </div>
    </div>

  );
}
export default App;