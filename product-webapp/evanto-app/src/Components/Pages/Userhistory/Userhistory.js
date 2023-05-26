import React, {useState, useEffect}from 'react';
import Historylist from '../../Historylist';
import Activeshowlist from '../../Activeshowlist';
import Userhistoryfilter from '../../Userhistoryfilter';
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';
import './Userhistory.css';
import { getBookingHistorybyEmailid } from "../../../Services/BookingTicketService";
import { mockComponent } from 'react-dom/test-utils';
import { ElderlyTwoTone } from '@mui/icons-material';


const Userhistory = () => {

    const [errors,setErrors] = useState('')
    const [email, setEmail] = useState('')
    const [values,setValues] = useState({
        bookindetail: [],
        searchfilter: "",
        activelist: [],
        historylist: []
    })
    const [startDate, setStartDate] = useState('');

    let bookingEvent = [];

 

    const getHistorylist = () =>{
       let val = values.historylist.map(element =>{
            if(bookingEvent.includes(element.eventId)){
                return  <Historylist bookingdetail={element} key={element._id} searchfilter={values.searchfilter} datefilter={startDate} rating={false}/> 
            }
            else{
                bookingEvent.push(element.eventId)
                return  <Historylist bookingdetail={element} key={element._id} searchfilter={values.searchfilter} datefilter={startDate} rating={true}/> 

            }

        }) 

        return val;
    }


    const getDetails =(emial) =>{
        let today = moment().format("DD-MM-YYYY");
        let timenow = moment().format('HH:mm:ss')
      
        getBookingHistorybyEmailid(
            emial,
            (res) => {
                setValues(prevState =>({
                    ...prevState,
                    bookindetail: res
                }))
                let activestate = [];
                let historystate = [];
                res.filter(el=>{
                   
                    let eventDate = new Date((el.eventDate).replace(/(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
                    let todaydate = new Date(new Date().setHours(0, 0, 0, 0))
                  
                    if(eventDate>todaydate){
                                                
                        activestate.push(el)
                    }
                    else if((el.eventDate == today) &&(timenow < moment(el.eventTime ,'HH:mm:ss').add(4,'hours').format('HH:mm:ss'))){

                       
                        activestate.push(el)
                    }
                    else{
                        historystate.push(el)
                    }

                })
                setValues(prevState =>({
                    ...prevState,
                    activelist: activestate,
                    historylist: historystate

                }))

            },
            (err) => {
                setErrors(err)
            }
          );
    }

    const handleSearchchange = (key) =>{
        setValues(prevState=>({
            ...prevState,
            searchfilter: key

        }))
    }

    const handleDatechange = (date)=>{
        setStartDate(date)
    }
    const handleClear = () =>{
        setStartDate('')
    }
  

    useEffect(()=>{

        const logindetails = JSON.parse(sessionStorage.getItem('logindetails'))
        if(logindetails){
           setEmail(logindetails.userEmail)

        }
        email && getDetails(email);    
    },[email])

   

    return (
        <div className='muidrawer-padding'>
            <h2 className='ev_primary_clr pt-4 text-center'>Booking history</h2>
                 <Userhistoryfilter handleSearchchange = {key=>handleSearchchange(key)} searchfilter={values.searchfilter} handleDatechange={(date)=>handleDatechange(date)} startDate={startDate} handleClear={handleClear} />

            <div className='row mx-0 gx-0 mt-2'>
               
                <div className='mt-2 activehistory_list'>
                    <h4 className='text-light my-4 text-center'>Active</h4>
                    <div className='d-flex flex-row flex-wrap justify-content-center'>
                        {
                   
                            values.activelist.map(element =>{
                                return  <Activeshowlist bookingdetail={element} key={element._id} searchfilter={values.searchfilter} datefilter={startDate} /> 
                            }) 
                        
                        }
                    </div>
                    

                </div>
                <div>
                    <h4 className='text-light text-center my-4'>History</h4>

                    {getHistorylist()}
                    
        
                   
                </div>

                

            </div>
         
            
           
        </div>
    );
}

export default Userhistory;
