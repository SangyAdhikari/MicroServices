import React, { useEffect, useState } from 'react'
import AdminSeatView from '../AdminSeatView'
import { useParams } from "react-router-dom";
import { getShowByid } from '../../Services/BookingTicketService';
import { getTheaterById } from '../../Services/RegisterService';
import { getEventByEventId } from '../../Services/EventDetailsService';


import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';




const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});


export default function ShowBookingHistory() {

    let [seatCount, setSeatCount] = useState(0)
    let [eventName, setEventName] = useState("")
    let [language, setLanguage] = useState("")
    let [eventDate, setEventDate] = useState("")
    let [eventTime, setEventTime] = useState("")
    let [location, setLocation] = useState("")
    let [theatreName, setTheatreName] = useState("")
    let [image, setImage] = useState("")


    let [bookedSeats, setBookedSeats] = useState([])

    const [showError, setShowError] = useState(false);
    const [errorMsg, setErrorMsg] = useState("");

    let { theatreId, showid } = useParams();

    useEffect(() => {
        getTheaterDetails()
        getBookedSeats()
    }, [])



    const getTheaterDetails = () => {
        getTheaterById(
            theatreId,
            (res) => {
                let { numberOfSeats, theatreName } = res

                setSeatCount(numberOfSeats)
                setTheatreName(theatreName)
            },
            (err) => {
                console.log(err);
                setShowError(true);
                setErrorMsg(err.response.data)
            }
        );
    }
    const getBookedSeats = () => {


        getShowByid(
            showid,
            (res) => {
                let { bookedSeats, language, eventDate, eventTime, location, eventId } = res
                getEventName(eventId)
                setBookedSeats(bookedSeats)
                setLanguage(language)
                setEventDate(eventDate)
                setEventTime(eventTime)
                setLocation(location)

            },
            (err) => {
                console.log(err);
                setShowError(true);
                setErrorMsg(err.response.data)
            }
        );
    }


    const getEventName = (eventId) => {
        getEventByEventId(
            eventId,
            (res) => {
                let { eventName, image } = res;
                setEventName(eventName);
                setImage(image);
            },
            (err) => {
                console.log(err);
                setShowError(true);
                setErrorMsg(err.response.data)
            }
        );
    }




    return (
        <div className='muidrawer-padding'>
            <AdminSeatView seatcount={seatCount}
                location={location}
                theatreName={theatreName}
                image={image}
                bookedSeats={bookedSeats} eventName={eventName} eventDate={eventDate} eventTime={eventTime}
                language={language} />


            <Snackbar open={showError} autoHideDuration={3000} onClose={() => { setShowError(false); setErrorMsg("") }} anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}>
                <Alert onClose={() => { setShowError(false); setErrorMsg("") }} severity="error" sx={{ width: '100%' }}>
                    {errorMsg}
                </Alert>
            </Snackbar>

        </div>
    )
}
