import React,{useEffect,useState} from 'react';
import './Activeshowlist.css';
import moment from 'moment';
import { useNavigate} from 'react-router-dom';
import { getShowByid } from "./../Services/BookingTicketService";
import { getTheaterById } from "./../Services/RegisterService";
import { getEventByEventId } from "../Services/EventDetailsService";


const Activeshowlist = ({bookingdetail,searchfilter,datefilter}) => {
    const navigate = useNavigate();

    let datefilter_val  = datefilter ? moment(datefilter).format('DD-MM-YYYY') : '';
    const [errors,seErrors] = useState('');
    const [values,setValues] = useState({
    theaterDetail: {},
    showdetail : {},
    eventDetail:{}
})

const handleBookingclick = (bookingdetail)=>{
   if(bookingdetail._id){
    navigate(`/ticket/${bookingdetail._id}`);
   }
}



const getDetails = (bookingdetail) =>{

    getTheaterById(
        bookingdetail.theatreId,
        (res) => {
            setValues(prevState =>({
                ...prevState,
                theaterDetail: res,
            }))
           
        },
        (err) => {
            seErrors('Nothing to show')
          
        }
      );

      getEventByEventId(
        bookingdetail.eventId,
        (res) => {
            setValues(prevState =>({
                ...prevState,
                eventDetail: res,
            }))
        },
        (err) => {
            seErrors('Nothing to show')
        }
      );


      getShowByid(
        bookingdetail.showId,
        (res) => {  
            setValues(prevState =>({
                ...prevState,
                showdetail: res,
            }))
          
        },
        (err) => {
      
        seErrors('Nothing to show')
        }
    );


}

const displayRefund = () =>{
    let date = moment(bookingdetail.eventDate, 'DD-MM-YYYY').format('MM-DD-YYYY');
    let time = moment(bookingdetail.eventTime,'HH:mm:ss').format('HH:mm:ss')
    let datestr = (`${date} ${time}`).toString();
    const eventdatetime = moment(new Date(datestr),'MM-DD-YYYY HH:mm:ss')
    let today = moment().format('MM-DD-YYYY HH:mm:ss')
    const duration = moment.duration(moment(datestr).diff(moment(today)));


    if(Math.floor(duration.asHours())>=7){
        return (
            <p className='cancellation'>Cancellation available</p>
        )
    }
    else{
        return;
    }



}



useEffect(()=>{
   
    
    bookingdetail && getDetails(bookingdetail);
      

},[bookingdetail,searchfilter,datefilter])

const displayList = () =>{
    return (
    <div className='activeshow mx-0 gx-0 bg-light text-dark mb-3 mx-2 me-sm-3 rounded-3 shadow-lg' onClick={()=>handleBookingclick(bookingdetail)} >
        <div className='active_list justify-content-around mt-0 text-start text-dark  d-flex flex-row flex-wrap align-items-center mx-0 gx-0'>
               <div className='text-start d-flex flex-row flex-wrap pe-md-3 justify-content-center'>
                   <div className='row gx-0 mx-0'>
                       <img src={`data:image;base64,${values.eventDetail.image}`} className="active_bannerimg rounded-sm-start rounded-0"></img>
                   </div>
                   <div className='mb-2 ms-4 d-flex flex-row align-items-center justify-content-center flex-wrap'>
                       <div className='pe-4'>
                           <h3 className='fs-4 text-center mb-0 text-sm-start'>{values.eventDetail.eventName}</h3>
                           <p className='text-center text-sm-start eventlocation'><i className="fa-solid fa-location-dot bookingicon"></i>      {values.theaterDetail.theatreName}</p>
                           <p className='text-center text-sm-start eventime'><i className="fa-solid fa-calendar-days bookingicon"></i> {bookingdetail.eventDate} {moment(bookingdetail.eventTime,["h:mm"]).format("hh:mm A")}</p>
                           <p className='eventseats text-center text-sm-start'>Seats booked: {bookingdetail.seatNumbers.map((el,index)=>{
                           return index == bookingdetail.seatNumbers.length-1 ? el : `${el}, `
                           })}</p>
                          
                           <p className='text-center text-sm-start '>BOOKING DATE: {bookingdetail.bookingDate}</p>
                           <p className='text-center text-sm-start'>Amount paid: {bookingdetail.ticketFair}</p>
                           {
                                displayRefund()
                           }
                       </div>
                       
                   </div>
                  
               </div>
               
       </div>
   </div>
    )
}


    return (
        (Object.keys(values.eventDetail).length) &&  ((values.eventDetail.eventName.toUpperCase().replaceAll(" ","").includes(searchfilter.toUpperCase().replaceAll(" ","")) || searchfilter=='')&&(datefilter_val== bookingdetail.eventDate || datefilter_val == '')) ? displayList() : false
        
       
    );
}

export default Activeshowlist;
