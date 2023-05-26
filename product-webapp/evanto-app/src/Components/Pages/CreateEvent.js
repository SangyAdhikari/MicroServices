import React, { useState, useEffect } from "react";
import { Row, Col, Button, Label } from "reactstrap";
import TextField from "@mui/material/TextField";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";

import { useParams } from "react-router-dom";
import moment from "moment";
import { getEventByEventId } from "../../Services/EventDetailsService";
import { getOrganizerDetailsAPI } from "../../Services/RegisterService";
import { registerEventAPI } from "../../Services/BookingTicketService";
// import TimePicker from "rc-time-picker";

import "rc-time-picker/assets/index.css";

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default function CreateEvent() {
  const [availableSeats, setAvailableSeats] = useState(0);
  const [ticketFare, setTicketFare] = useState(120);
  const [language, setLanguage] = useState("");
  const [eventType, setEventType] = useState(1);
  const [location, setLocation] = useState("");
  const [description, setDescription] = useState(0);
  const [eventTime, setEventTime] = useState("");
  const [eventDate, setEventDate] = useState("");
  const [showPopup, setPopup] = useState(false);

  const [eventName, setEventName] = useState("");
  const [organizerEmailId, setOrganizerEmailId] = useState("");
  const [genre, setGenre] = useState("");

  const [releaseDate, setReleaseDate] = useState(null);
  const [theatreId, setTheaterId] = useState("");
  const [venueList, setVenueList] = useState([]);
  const [releasedLanguages, setReleasedLanguages] = useState([]);

  const [showError, setShowError] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  let { eventId } = useParams();

  useEffect(() => {
    let UserAccount = sessionStorage.getItem("logindetails");
    UserAccount = JSON.parse(UserAccount);
    if (UserAccount === null) {
      return;
    }
    let { userEmail } = UserAccount;
    setOrganizerEmailId(userEmail);
    getTheaterLists(userEmail);
  }, []);

  const getTheaterLists = (userEmail) => {
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

  useEffect(() => {
    getEventByEventId(
      eventId,
      (res) => {
        let {
          releasedLanguages,
          genreOfEvent,
          eventName,
          eventType,
          eventDescription,
          releaseDate,
        } = res;
        setReleaseDate(releaseDate);
        setEventName(eventName);
        setGenre(genreOfEvent);
        setReleasedLanguages(releasedLanguages);
        setEventType(eventType);
        setDescription(eventDescription);
      },
      (err) => {
        console.log(err);
        setShowError(true);
        setErrorMsg(err.response.data);
      }
    );
  }, []);

  const onSubmit = (e) => {
    e.preventDefault();

    let eventDate_ = moment(eventDate, "YYYY-MM-DD").format("DD-MM-YYYY");

    if (language === "") {
      setShowError(true);
      setErrorMsg("Please Select Language");
      return;
    }
    if (eventTime === "") {
      setShowError(true);
      setErrorMsg("Please Select event time");
      return;
    }

    let data = {
      eventId: parseInt(eventId),
      theatreId: parseInt(theatreId),
      organizerEmailId,
      location: location.city,
      language,
      eventDate: eventDate_,
      eventTime,
    };

    registerEventAPI(
      data,
      (res) => {
        setPopup(true);
      },
      (err) => {
        setShowError(true);
        setErrorMsg(err.response.data);
      }
    );
  };

  const onChangeTheater = (value) => {
    let { numberOfSeats, theatreId } = value;
    setAvailableSeats(numberOfSeats);
    setTheaterId(theatreId);
    setLocation(value);
  };

  const fontColor = {
    style: { color: "white" },
  };

  const getActiveDates = () => {
    let dates = null;
    let today = moment().format("YYYY-MM-DD");
    let comp = moment(releaseDate).diff(today, "days");

    if (comp > 0) {
      dates = moment(releaseDate).format("YYYY-MM-DD");
    } else {
      // dates = moment().add(1, "days").format("YYYY-MM-DD")
      dates = moment().format("YYYY-MM-DD");
    }
    return dates;
  };

  return (
    <div className="container-fluid muidrawer-padding" id="create_event">
      <Row className="justify-content-center">
        <Col md={10}>
          <form className="mt-2 container" onSubmit={onSubmit}>
            <Row className="mt-3">
              <Col md={12} className="text-center">
                <h2 className="text-orange">CREATE SHOW</h2>
              </Col>
            </Row>
            <Row className="mt-3">
              <Col md={12}>
                <FormControl variant="standard" style={{ width: "100%" }}>
                  <InputLabel id="demo-simple-select-standard-label">
                    Select Venue
                  </InputLabel>
                  <Select
                    name="venue"
                    value={location}
                    onChange={(e) => onChangeTheater(e.target.value)}
                    color="warning"
                    style={{ width: "100%" }}
                    label="Venue"
                    required
                  >
                    {venueList.map((venue, index) => (
                      <MenuItem value={venue} key={index}>
                        {venue.theatreName}
                      </MenuItem>
                    ))}
                  </Select>
                  {venueList.length === 0 && (
                    <div className=" my-1" style={{color:'red'}}>
                      Please Create Theater
                    </div>
                  )}
                </FormControl>
              </Col>
            </Row>
            <Row className="mt-3">
              <Col md={12} className="mt-3">
                <TextField
                  name="EventName"
                  color="warning"
                  label="Event Name"
                  // disabled
                  value={eventName}
                  variant="standard"
                  style={{ width: "100%" }}
                  required
                  inputProps={fontColor}
                />
              </Col>
            </Row>
            <Row className="mt-4">
              <Col md={6} className="py-2 align-self-end">
                <TextField
                  name={"EventDate"}
                  label="Event Date"
                  type="date"
                  value={eventDate}
                  required
                  InputProps={{
                    inputProps: {
                      min: getActiveDates(),
                    },
                  }}
                  sx={{
                    width: "100%",
                    svg: { color: "white" },
                    input: { color: "white" },
                  }}
                  InputLabelProps={{
                    shrink: true,
                  }}
                  onChange={(e) => setEventDate(e.target.value)}
                />
              </Col>
              <Col md={6} className="py-2">
                {/* <Label className="text-white">Event Time</Label>
                <div>
                  <TimePicker
                    showSecond={false}
                    // defaultValue={eventTime}
                    onChange={(value) => {
                      setEventTime(value.format("HH:MM"));
                    }}
                    use12Hours
                    format={"h:mm a"}
                    className="w-100"
                    required
                  />
                </div> */}
                <div>
                  <label className="text-white">Event Time</label>
                  <div>
                    <input
                      type="time"
                      className="myDatePicker"
                      name="eventTime"
                      value={eventTime}
                      required
                      onChange={(e) => {
                        setEventTime(e.target.value);
                      }}
                    />
                  </div>
                </div>
              </Col>
            </Row>

            <Row className="mt-3">
              <Col md={6} className="mt-3">
                <TextField
                  name={"TicketFare"}
                  label="Ticket Fare"
                  type={"number"}
                  value={ticketFare}
                  variant="standard"
                  color="warning"
                  style={{ width: "100%", color: "white" }}
                  inputProps={fontColor}
                  // required
                  // disabled
                  // onChange={(e) => setTicketFare(e.target.value)}
                />
              </Col>
              <Col md={6} className="mt-3">
                <TextField
                  name="SeatCount"
                  label="Seat Count"
                  variant="standard"
                  color="warning"
                  value={availableSeats}
                  // disabled
                  // onChange={(e) => setAvailableSeats(e.target.value)}
                  // required
                  style={{ width: "100%", color: "white" }}
                  inputProps={fontColor}
                />
              </Col>
            </Row>
            <Row className="mt-3">
              <Col md={6} className="mt-3">
                <TextField
                  label="Genre"
                  variant="standard"
                  color="warning"
                  inputProps={fontColor}
                  value={genre}
                  style={{ width: "100%" }}
                />
              </Col>
              <Col md={6} className="mt-3">
                <FormControl variant="standard" style={{ width: "100%" }}>
                  <InputLabel id="demo-simple-select-standard-label">
                    Select Language
                  </InputLabel>
                  <Select
                    name="Language"
                    value={language}
                    onChange={(e) => setLanguage(e.target.value)}
                    color="warning"
                    style={{ width: "100%" }}
                    label="Venue"
                    required
                  >
                    {releasedLanguages.map((lang, index) => (
                      <MenuItem key={index} value={lang}>
                        {lang}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Col>
            </Row>
            <Row className="mt-4">
              <Col md={6} className="mt-3">
                <FormControl variant="standard" style={{ width: "100%" }}>
                  <InputLabel id="demo-simple-select-standard-label">
                    Event Type
                  </InputLabel>
                  <Select
                    name="Event Type"
                    value={eventType}
                    onChange={(e) => setEventType(e.target.value)}
                    color="warning"
                    style={{ width: "100%" }}
                    label="Venue"
                    required
                  >
                    <MenuItem value={"Movie"}>Movie</MenuItem>
                    <MenuItem value={"Drama"}>Drama</MenuItem>
                    <MenuItem value={"Music Concerts"}>Music Concerts</MenuItem>
                    <MenuItem value={"Stand up comedy"}>
                      Stand up comedy
                    </MenuItem>
                  </Select>
                </FormControl>
              </Col>
            </Row>
            <Row className="mt-3">
              <Col md={12} className="mt-3">
                <TextField
                  label="Description"
                  variant="standard"
                  style={{ width: "100%" }}
                  color="warning"
                  multiline
                  rows={4}
                  required
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
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
                  Create Event
                </Button>
              </Col>
            </Row>
          </form>
        </Col>

        <Snackbar
          open={showPopup}
          autoHideDuration={3000}
          onClose={() => setPopup(false)}
          anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        >
          <Alert
            onClose={() => setPopup(false)}
            severity="success"
            sx={{ width: "50%" }}
          >
            Event Created Successfully
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
      </Row>
    </div>
  );
}
