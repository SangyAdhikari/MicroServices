import React,{useEffect,useState} from 'react';
import './Historylist.css';
import $ from 'jquery';
import moment from 'moment';
import { useNavigate} from 'react-router-dom';
import { getShowByid } from "./../Services/BookingTicketService";
import { getTheaterById } from "./../Services/RegisterService";
import { getEventByEventId, addRating } from "../Services/EventDetailsService";


const Historylist = ({bookingdetail,searchfilter,datefilter,rating}) => {
    const navigate = useNavigate();
    let datefilter_val  = datefilter ? moment(datefilter).format('DD-MM-YYYY') : '';
    const [errors,seErrors] = useState('');
    const [showsubmit,setShowsubmit] = useState(false);
    const [userdetail ,setUserdetail] = useState({})


const [values,setValues] = useState({
    theaterDetail: {},
    showdetail : {},
    eventDetail:{},
    rating: ''
})

const handleBookingclick = (bookingdetail)=>{
    if(bookingdetail._id){
     navigate(`/ticket/${bookingdetail._id}`);
    }
 }
 const handleSubmit = async() =>{
    setShowsubmit(false)

    let ratingdata = {
        "emailId": bookingdetail.userEmailId,
        "userRating": values.rating
    }

    addRating(bookingdetail.eventId, ratingdata,(res)=>{
       
        getDetails(bookingdetail);

    })

   
 }

const handleRatingclick = (event) =>{
    setShowsubmit(true)
    let id = event.target.getAttribute('id');
   
    let rating = Number(event.target.getAttribute('data-rating'));
   
    let id_new = id.slice(0,-2)
  
    for(let i=1; i<=5;i++){
      
       i<=rating ? $(`#${id_new}_${i}`).addClass('rating_fill') : $(`#${id_new}_${i}`).removeClass('rating_fill')
         
    }
    setValues(prevState=>({
        ...prevState,
        rating: rating
    }))
}
const displayRatings = () =>{
  let getrating =  values.eventDetail.userRating && values.eventDetail.userRating.filter(el=>
    {
        if(el.emailId ===bookingdetail.userEmailId){
            return el
        }
       
    });

    let userRating = getrating.length ? getrating[0].userRating : 0;
    let ratingStar =[];

  if(userRating){
    for(let i=1; i<=5;i++){
        let star = i<=userRating ? <i className="fa-solid fa-star ratingstar ratingstar-1 pe-1 rating_fill" data-rating='i' id={`${bookingdetail._id}_${i}`} data-star={i} key={i}></i> : <i className="fa-solid fa-star ratingstar ratingstar-1 pe-1" data-rating={i} id={`${bookingdetail._id}_${i}`} data-star={i}  key={i}></i>
        ratingStar.push(star)
    }
  }
  else{
    if(rating){
        for(let i=1; i<=5;i++){
            let star =   <i className="fa-solid fa-star ratingstar ratingstar-1 pe-1" data-rating={i} id={`${bookingdetail._id}_${i}`} data-star={i} onClick={(event)=>handleRatingclick(event)} key={i}></i>;
            ratingStar.push(star)
        }
    }
    else{
        ratingStar = [];
    }
    
  }
  return ratingStar; 
  
 
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
useEffect(()=>{
    

    
    bookingdetail && getDetails(bookingdetail);
      

},[bookingdetail,searchfilter,datefilter, ])

const displayList = () =>{
    return (
        <div className='userhistory container-fluid mx-0 gx-0' key={values.eventDetail.eventName}>
        <div className='history_list justify-content-around mt-4 text-start ps-md-5 text-light d-flex flex-row flex-wrap align-items-center mx-0 gx-0 mb-3'>
               <div className='text-start booked_event  d-flex flex-row flex-wrap pe-md-3 justify-content-center' onClick={()=>handleBookingclick(bookingdetail)}>
                   <div className='row gx-0 mx-0'>
                       <img src={`data:image;base64,${values.eventDetail.image}`} className="history_bannerimg"></img>
                   </div>
                   <div className='mb-2 ms-4 d-flex flex-row align-items-center justify-content-center flex-wrap'>
                       <div className='pe-4'>
                           <h3 className='fs-4 text-center text-sm-start'>{values.eventDetail.eventName}</h3>
                           <p className=' text-center text-sm-start'><i className="fa-solid fa-location-dot"></i> {values.theaterDetail.theatreName}, {values.theaterDetail.district}</p>
                           <p className=' text-center text-sm-start'><i className="fa-solid fa-calendar-days"></i> {bookingdetail.eventDate} {bookingdetail.eventTime}</p>
                       </div>
                       <div>
                           <p className=' text-center text-sm-start'>Seats booked: {bookingdetail.seatNumbers.map((el,index)=>{
                           return index == bookingdetail.seatNumbers.length-1 ? <span key={el}> {el}</span> : <span key={el}>{el},</span> 
                           })}</p>
                          
                           <p>BOOKING DATE & TIME: {bookingdetail.bookingDate} {bookingdetail.bookingTime}</p>
                           <p className=' text-center text-sm-start'>Amount paid: {bookingdetail.ticketFair}</p>
                       </div>
                       
                   </div>
               </div>

              

             <div className='text-light d-flex flex-row w-auto rating_container align-items-baseline'> 
             {displayRatings()} <button className={'btn submit_btn ms-2 '+(showsubmit ? 'showsubmit' : 'hidesubmit')} onClick={handleSubmit}>Submit</button>
         </div>

          
               
       </div>
   </div>
    )
}


    return (

        values.eventDetail.eventName &&  ((values.eventDetail.eventName.toUpperCase().replaceAll(" ","").includes(searchfilter.toUpperCase().replaceAll(" ","")) || searchfilter=='')&&(datefilter_val== bookingdetail.eventDate || datefilter_val == '')) ? displayList() : false
        
       
    );
}

export default Historylist;
