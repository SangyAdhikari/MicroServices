import React, { useState, useEffect } from "react";
import { Row, Col, Button, Label } from "reactstrap";
import TextField from "@mui/material/TextField";
import Select from "react-select";

import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";
import VenueList from "../VenueList";
import {
  addNewVenueAPI,
  getOrganizerDetailsAPI,
  updateVenueAPI,
} from "../../Services/RegisterService";

import StateAndDistrict from "../../data/db.json";
import "../../CSS/TheaterList.css";

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default function TheaterList() {
  const [venueList, setVenueList] = useState([]);

  const [showForm, setForm] = useState(false);
  const [pinCode, setPincode] = useState("");
  const [numberOfSeats, setAvailableSeats] = useState("");
  const [street, setStreet] = useState("");
  const [landMark, setLandmark] = useState("");
  const [theatreName, setTheaterName] = useState("");
  const [message, setMessage] = useState("");
  const [showPopup, setPopup] = useState(false);
  const [isUpdate, setUpdate] = useState(false);
  const [allStates, setAllStates] = useState([]);
  const [selectedAllDistrict, setselectedAllDistrict] = useState([]);
  const [state, setState] = useState("Select");
  const [city, setCity] = useState("Select");
  const [userEmail, setEmail] = useState("");
  const [theatreId, setTheatreId] = useState(null);

  const [showError, setShowError] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  useEffect(() => {
    let UserAccount = sessionStorage.getItem("logindetails");
    UserAccount = JSON.parse(UserAccount);
    if (UserAccount === null) {
      return;
    }
    let { userEmail } = UserAccount;

    setEmail(userEmail);
    getTheaters(userEmail);
  }, []);

  const fontColor = {
    style: { color: "white" },
  };

  useEffect(() => {
    setAllStates(StateAndDistrict.states);
  }, []);

  useEffect(() => {}, []);

  const getTheaters = (userEmail) => {

    getOrganizerDetailsAPI(
      userEmail,
      (res) => {
        let venueList = res.list;
        if (venueList === null) {
          venueList = [];
        }
        setVenueList(venueList);
      },
      (err) => {
        console.log(err);
        setShowError(true);
        setErrorMsg(err.response.data);
      }
    );
  };

  const onSubmit = (e) => {
    e.preventDefault();

    let addNew = {
      city,
      district: city,
      landMark,
      numberOfSeats: parseInt(numberOfSeats),
      pinCode,
      state,
      street,
      theatreName,
    };
    if (addNew.city === "") {
      return;
    }
    if (addNew.state === "") {
      return;
    }
    if (isUpdate) {
      updateVenue();
    } else {
      addNewVenue(addNew);
    }
  };

  const updateVenue = () => {
    let updated_info = {
      city,
      district: city,
      landMark,
      numberOfSeats: parseInt(numberOfSeats),
      pinCode,
      state,
      street,
      theatreName,
      theatreId,
    };

    updateVenueAPI(
      theatreId,
      userEmail,
      updated_info,
      (res) => {
        setMessage("updated");
        setPopup(true);
        getTheaters(userEmail);
        newVenue()
      },
      (err) => {
        console.log(err);
        setShowError(true);
        setErrorMsg(err.response.data);
      }
    );
  };

  const addNewVenue = (data) => {
    
    addNewVenueAPI(
      userEmail,
      data,
      (res) => {
        
        setMessage("created");
        setPopup(true);
        getTheaters(userEmail);
        newVenue()
      },
      (err) => {
        console.log(err);
        setShowError(true);
        setErrorMsg(err.response.data);
      }
    );
  };

  const showDetails = (venue) => {
    let {
      pinCode,
      numberOfSeats,
      theatreName,
      street,
      landMark,
      city,
      theatreId,
      state,
    } = venue;
    setTheatreId(theatreId);
    const CityOptions = allStates.filter((d) => d.state === state)[0];
    onChangeState(CityOptions);

    setForm(true);
    setUpdate(true);
    setPincode(pinCode);
    setAvailableSeats(numberOfSeats);
    setTheaterName(theatreName);
    setStreet(street);
    setLandmark(landMark);
    setState(state);
    setCity(city);
  };

  const newVenue = () => {
    setForm(true);
    setUpdate(false);
    setPincode("");
    setAvailableSeats("");
    setTheaterName("");
    setStreet("");
    setLandmark("");
    setState("");
    setCity("");
  };

  const onChangeState = (value) => {
    setState(value.state);
    setselectedAllDistrict(value.districts);
    setCity(null);
  };

  const onChangeCity = (value) => {
    setCity(value.label);
  };

  return (
    <div className="container-fluid mt-4" id="theater_list">
      <Row className="muidrawer-padding">
        <Col md={4} className="theaterListScroll">
          <div className="text-center text-orange">Your Venue Lists</div>
          <div
            className="text-end text-orange"
            role={"button"}
            onClick={() => newVenue()}
            style={{ fontSize: 15 }}
          >
            Add New Theater
          </div>

          {venueList.map((venue, index) => (
            <VenueList
              key={index}
              isActive={venue.theatreId === theatreId}
              showDetails={() => showDetails(venue)}
              theatreName={venue.theatreName}
              city={venue.city}
              street={venue.street}
              numberOfSeats={venue.numberOfSeats}
            />
          ))}
          {venueList.length === 0 && (
            <div className="text-center my-1" style={{ color:'red'}}>
              Please Create Theater
            </div>
          )}
        </Col>
        <Col md={8} style={{ borderLeft: "1px solid white" }}>
          {showForm ? (
            <>
              <div className="text-center text-orange">
                {isUpdate ? "Update Venue" : "Add Venue"}
              </div>
              <form className="mt-2 container" onSubmit={onSubmit}>
                <Row className="mt-3">
                  <Col md={12}>
                    <TextField
                      name="TheaterName"
                      color="warning"
                      label="Theater Name"
                      // disabled
                      value={theatreName}
                      onChange={(e) => setTheaterName(e.target.value)}
                      variant="standard"
                      style={{ width: "100%" }}
                      required
                      inputProps={fontColor}
                    />
                  </Col>
                </Row>
                <Row className="mt-5">
                  <Col md={6} className="py-2">
                    <TextField
                      name="SeatCount"
                      label="Seat Count"
                      variant="standard"
                      color="warning"
                      type={"number"}
                      value={numberOfSeats}
                      onChange={(e) => setAvailableSeats(e.target.value)}
                      required
                      style={{ width: "100%", color: "white" }}
                      inputProps={fontColor}
                    />
                  </Col>
                  <Col md={6} className="py-2">
                    <TextField
                      name="Street"
                      color="warning"
                      label="Street"
                      value={street}
                      onChange={(e) => setStreet(e.target.value)}
                      variant="standard"
                      style={{ width: "100%" }}
                      required
                      inputProps={fontColor}
                    />
                  </Col>
                </Row>

                <Row className="my-3">
                  <Col md={4}>
                    <Label className="text-white" style={{ fontSize: 13 }}>
                      Select Country
                    </Label>

                    <Select
                      value={{ label: "India" }}
                      options={[{ label: "India" }]}
                      styles={{
                        option: (styles, { isFocused, isSelected }) => ({
                          ...styles,
                          fontSize: 14,
                          color: "white",
                          background: isFocused
                            ? "orange"
                            : isSelected
                            ? "black"
                            : "black",
                          zIndex: 1,
                        }),
                        control: (base) => ({
                          ...base,
                          height: 30,
                          minHeight: 30,
                          fontSize: 14,
                        }),
                      }}
                      classNamePrefix="select2-selection"
                    />
                  </Col>

                  <Col md={4}>
                    <Label className="text-white" style={{ fontSize: 13 }}>
                      Select State
                    </Label>

                    <Select
                      value={{ state }}
                      options={allStates}
                      onChange={(e) => onChangeState(e)}
                      classNamePrefix="select2-selection"
                      getOptionLabel={(option) => option.state}
                      styles={{
                        option: (styles, { isFocused, isSelected }) => ({
                          ...styles,
                          fontSize: 14,
                          background: isFocused
                            ? "orange"
                            : isSelected
                            ? "black"
                            : undefined,
                          zIndex: 1,
                        }),

                        control: (base) => ({
                          ...base,
                          height: 30,
                          minHeight: 30,
                          fontSize: 14,
                          padding: 0,
                          // background: "gray",
                        }),
                      }}
                    />
                  </Col>
                  <Col md={4}>
                    <Label className="text-white" style={{ fontSize: 13 }}>
                      Select City
                    </Label>

                    <Select
                      value={{ label: city }}
                      options={selectedAllDistrict.map((t) => ({
                        value: t,
                        label: t,
                      }))}
                      onChange={(e) => onChangeCity(e)}
                      classNamePrefix="select2-selection"
                      // getOptionLabel={(option) => option.state}
                      styles={{
                        option: (styles, { isFocused, isSelected }) => ({
                          ...styles,
                          fontSize: 14,
                          background: isFocused
                            ? "orange"
                            : isSelected
                            ? "black"
                            : undefined,
                          zIndex: 1,
                        }),

                        control: (base) => ({
                          ...base,
                          height: 30,
                          minHeight: 30,
                          fontSize: 14,
                          padding: 0,
                          // background: "gray",
                        }),
                      }}
                    />
                  </Col>
                </Row>
                <Row className="mt-5">
                  <Col md={6} className="py-2">
                    <TextField
                      name="pinCode"
                      label="Pin Code"
                      variant="standard"
                      color="warning"
                      value={pinCode}
                      type={"number"}
                      onChange={(e) => setPincode(e.target.value)}
                      required
                      style={{ width: "100%", color: "white" }}
                      inputProps={fontColor}
                    />
                  </Col>
                  <Col md={6} className="py-2">
                    <TextField
                      name="Landmark"
                      color="warning"
                      label="Landmark"
                      value={landMark}
                      onChange={(e) => setLandmark(e.target.value)}
                      variant="standard"
                      style={{ width: "100%" }}
                      required
                      inputProps={fontColor}
                    />
                  </Col>
                </Row>

                <Row className="mt-4">
                  <Col md={12}>
                    <Button
                      color="warning"
                      outline
                      type="submit"
                      className="w-100 text-white"
                    >
                      {isUpdate ? "Update Venue" : "Add Venue"}
                    </Button>
                  </Col>
                </Row>
              </form>
            </>
          ) : null}

          <Snackbar
            open={showPopup}
            autoHideDuration={3000}
            onClose={() => setPopup(false)}
            anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
          >
            <Alert
              onClose={() => setPopup(false)}
              severity="success"
              sx={{ width: "100%" }}
            >
              Venue {message} Successfully
            </Alert>
          </Snackbar>

          <Snackbar
            open={showError}
            autoHideDuration={3000}
            onClose={() => {
              setShowError(false);
              setErrorMsg("");
            }}
            anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
          >
            <Alert
              onClose={() => {
                setShowError(false);
                setErrorMsg("");
              }}
              severity="error"
              sx={{ width: "100%" }}
            >
              {errorMsg}
            </Alert>
          </Snackbar>
        </Col>
      </Row>
    </div>
  );
}
