import React, { useEffect, useState } from "react";
import NowShowing from "../NowShowing";
import SearchAndFilters from "../SearchAndFilters";
import { getOrganizerHistorybyEmailid } from '../../Services/BookingTicketService'
import { Row, Col } from "reactstrap";
import { getAllEvents } from '../../Services/EventDetailsService';
import moment from "moment";
import { getOrganizerDetailsAPI } from "../../Services/RegisterService";
import OldShows from "../OldShows";

import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';




const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default function ShowsHistory() {
  // const [Events, setEvents] = useState([]);

  const [currentShows, setCurrentShows] = useState([])
  const [allCurrentShows, setAllCurrentShows] = useState([])
  const [allOldShows, setAllOldShows] = useState([])

  const [oldShowsList, setOldShowsList] = useState([])
  const [date, setDate] = useState(null);
  const [searchTxt, setSearchTxt] = useState('');

  
  const [showError, setShowError] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");



  useEffect(() => {
    let UserAccount = sessionStorage.getItem("logindetails");
    UserAccount = JSON.parse(UserAccount);
    let { userEmail } = UserAccount;
    getHistoryAPI(userEmail)
  }, [])


  const getHistoryAPI = (userEmail) => {
    getOrganizerHistorybyEmailid(userEmail, (res) => {
      getAllTheaters(res, userEmail)
    },
      (err) => {
        console.log(err);
        // setShowError(true); 
          // setErrorMsg(err.response.data) 
      }
    );
  }

  const getAllTheaters = (allHistory, userEmail) => {

    getOrganizerDetailsAPI(userEmail, (res) => {
      getAllEventsAPI(allHistory, res.list)
    },
      (err) => {
        console.log(err);
        setShowError(true); 
          setErrorMsg(err.response.data) 
      }
    );
  }

  const getAllEventsAPI = (allHistory, theaters) => {
    getAllEvents((res) => {

      aggrigateEvents(allHistory, theaters, res)
    },
      (err) => {
        console.log(err);
        setShowError(true); 
          setErrorMsg(err.response.data) 
      }
    );
  }


  const aggrigateEvents = (allHistory, theaters, events) => {
    let comibinedData = []

    allHistory.forEach(history => {
      events.forEach(event => {
        if (history.eventId === event._id) {
          let theater_name = theaters.filter(theater => { return (theater.theatreId === history.theatreId) })
            theater_name = theater_name[0].theatreName
            
          let { bookedSeats, eventDate, eventId, eventTime, language, location, organizerEmailId, theatreId } = history
          let { castAndCrew, eventDescription, eventDuration, eventName, eventRating, eventType, genreOfEvent, image } = event
          comibinedData.push({ showId: history._id, bookedSeats, eventDate, eventId, eventTime, language, location, organizerEmailId, theatreId, castAndCrew, eventDescription, eventDuration, eventName, eventRating, eventType, genreOfEvent, image, theater_name })
        }
      });
    });
    // setEvents(comibinedData)


    const curntShws = comibinedData.filter((data) => {
      let plainDate = data.eventDate
      let eventDate = new Date(plainDate.replace(/(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
      let today = new Date(new Date().setHours(0, 0, 0, 0))
      return eventDate >= today
    }
    );

    const oldShowsList = comibinedData.filter((data) => {
      let plainDate = data.eventDate
      let eventDate = new Date(plainDate.replace(/(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
      let today = new Date(new Date().setHours(0, 0, 0, 0))
      return eventDate < today
    }
    );



    setAllCurrentShows(curntShws)
    setCurrentShows(curntShws)

    setAllOldShows(oldShowsList)
    setOldShowsList(oldShowsList)

  }

  const onChangeSearch = (searchField) => {
    setSearchTxt(searchField)
    onFilterCurrentShows(searchField)
    onFilterOldShows(searchField)
  }

  const onFilterCurrentShows = (searchField) => {
    const filteredShows = allCurrentShows.filter(data => {
      return (data.eventName.toLowerCase().replace(/\s/g, '').includes(searchField.toLowerCase().replace(/\s/g, '')) || data.theater_name.toLowerCase().replace(/\s/g, '').includes(searchField.toLowerCase().replace(/\s/g, '')));
    });
    setCurrentShows(filteredShows)
  }

  const onFilterOldShows = (searchField) => {
    const filteredOldShows = allOldShows.filter(data => {
      return (data.eventName.toLowerCase().replace(/\s/g, '').includes(searchField.toLowerCase().replace(/\s/g, '')) || data.theater_name.toLowerCase().replace(/\s/g, '').includes(searchField.toLowerCase().replace(/\s/g, '')));
    });
    setOldShowsList(filteredOldShows)
  }

  const onChangeDate = (value) => {
    value = moment(value).format("DD-MM-YYYY")
    setDate(value)
    if (value === "Invalid date") {
      setCurrentShows(allCurrentShows)
      setOldShowsList(allOldShows)
      return
    }

    if (value !== '') {
      const filteredCurrentShows = allCurrentShows.filter(data => { return ((data.eventDate === value)) })
      const filteredOldShows = allOldShows.filter(data => { return ((data.eventDate === value)) })
      setCurrentShows(filteredCurrentShows)
      setOldShowsList(filteredOldShows)
    }

  }



  return (
   <div className="container-fluid mt-4">
     <Row className="justify-content-center muidrawer-padding" >
      <Col md={12}>
        <div className="text-center">
          <h4 className="text-orange">SHOWS HISTORY</h4>
        </div>
        <SearchAndFilters onChangeSearch={e => onChangeSearch(e)} onChangeDate={(e) => onChangeDate(e)} />
        <div className="mt-4">
          <h4 className="text-white">Current Shows</h4>
        </div>
        <NowShowing Events={currentShows} filteredDate={date} />
        <div className="mt-4">
          <h4 className="text-white">Old Shows</h4>
        </div>
        <OldShows Events={oldShowsList} filteredDate={date} />
      </Col>

      
      <Snackbar open={showError} autoHideDuration={3000} onClose={() => {setShowError(false); setErrorMsg("") }}         anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}>
        <Alert  onClose={() => {setShowError(false); setErrorMsg("") }} severity="error" sx={{ width: '100%' }}>
        {errorMsg}
        </Alert>
      </Snackbar>

    </Row>
   </div>
  );
}
