import React, {useState, useEffect}from 'react';
import Theaterdetail from './../../Theaterdetail';
import Seatinglayout from './../../Seatinglayout';
import { useParams } from 'react-router-dom';
import { getShowByid } from "../../../Services/BookingTicketService";
import { getTheaterById } from "../../../Services/RegisterService";
import { getEventByEventId } from "../../../Services/EventDetailsService";


const Seatselection = () => {

    const [values,setValues] = useState({
        theaterDetail: {},
        showdetail : {},
        eventDetail:{}
    })

    const [errors,seErrors] = useState('');

    const  {showId}  = useParams();
    

    const getEventandTheater = (showdet) =>{
    
        getTheaterById(
            showdet.theatreId,
            (res) => {
                setValues(prevState =>({
                    ...prevState,
                    theaterDetail: res
                }))
               
            },
            (err) => {
                seErrors('Nothing to show')
              
            }
          );

          getEventByEventId(
            showdet.eventId,
            (res) => {
                setValues(prevState =>({
                    ...prevState,
                    eventDetail: res
                }))
            },
            (err) => {
                seErrors('Nothing to show')
            }
          );

    }

    const getDetails =async (showId) =>{

        getShowByid(
            showId,
            (res) => {  
                setValues(prevState =>({
                    ...prevState,
                    showdetail: res
                }))
                getEventandTheater(res)
            },
            (err) => {
          
            seErrors('Nothing to show')
            }
        );
    }


useEffect(()=>{
    
    showId && getDetails(showId);
      
},[])


    return (
        <div className='muidrawer-padding'>
            {
                errors ?  <div className='d-flex flex-row align-items-center justify-content-center mt-5'> <p className='mt-2 text-light seaterror fs-3'>{errors}</p> </div>: ''
            }
            
            
            {
                  (Object.keys(values.theaterDetail).length)&&(Object.keys(values.showdetail).length !== 0) ?  <Theaterdetail theater_det={values.theaterDetail} show_det = {values.showdetail} /> : ""
            }
            {
                (Object.keys(values.theaterDetail).length !== 0) && (Object.keys(values.eventDetail).length !== 0) && (Object.keys(values.showdetail).length !== 0) ?  <Seatinglayout theater_det={values.theaterDetail} event_det={values.eventDetail} show_det = {values.showdetail}/> : ""
               
            }
          
        </div>
    );
}

export default Seatselection;
