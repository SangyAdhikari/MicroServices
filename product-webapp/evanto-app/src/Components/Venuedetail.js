import React, {useState, useEffect} from 'react';
import './Venuedetail.css';
import moment from 'moment';
import { useNavigate} from 'react-router-dom';
import { getTheaterById } from "./../Services/RegisterService";



const Venuedetail = ({filterDate, filterLang, filterTiming,event_list,filterCinemas,filterLocation}) => {
    const navigate = useNavigate();
  
    const [values, setValues] = useState(
        {
            theaterevent:[], 
            selected_timing: {}
        }
    );

  
    const handleEventclick = (showid) =>{
       
        navigate(`/selectseat/${showid}`);
    }


    const checkSeatAvailability = (eve,theat) =>{
       
                      
        if(eve.bookedSeats.length<Number(theat.numberOfSeats)/2){
            return 'timing_available';
        }
        else if(eve.bookedSeats.length >= theat.numberOfSeats){
            return 'timing_sold';
        }
        else{
            return 'timing_moderate';
        }
    }

    const checkEventtime = (eventtime) =>{
        var currentHour =Number(String(eventtime).slice(0,2))
       
        if (currentHour > 3 && currentHour < 12){
            return "Morning";
        } else if (currentHour >= 12 && currentHour < 15){
            return "Afternoon";
        }   else if (currentHour >= 15 && currentHour < 20){
            return "Evening";
        } else if (currentHour >= 20 || currentHour <=3){
            return "Night";
        } else {
            return "All"
        }
    }

    const displayDetails = (element,eventtime,eventlang) =>{
        
        return (
           
            <div className='d-flex flex-row flex-wrap align-items-center text-start px-2 py-3 venues' key={element.theatreId}>
                <div className='venue_det me-4 mb-2'>
                    <p className='fs-5'>{element.theatreName}</p>
                    <p className='venue_addr'>{element.street}</p>
                    <p className='venue_addr ev_primary_clr'>{eventlang}</p>
                </div>
                <div className='venue_timings d-flex flex-wrap flex-row'>
           
                 {eventtime}
            
                </div>
            </div>
        )
    }

    const getTheaterList = (theatercoll,eventdet) =>{
       
       let list = theatercoll.map(element => {

            let searchKey = filterCinemas.toUpperCase().replaceAll(" ","");
            let theaterName = element.theatreName.toUpperCase().replaceAll(" ","");
         
            if((theaterName.includes(searchKey))||(!filterCinemas)){
               
                    let eventtime=[];
                    let eventlang;
                    eventdet.sort((a, b) => {    
                        return Number(String(a.eventTime).slice(0,2)) - Number(String(b.eventTime).slice(0,2));
                    }).filter(el=>{
                      
                        if(el.eventDate == moment().format('DD-MM-YYYY')){
                           
                            if(el.eventTime > moment().add(10, 'minutes').format('HH:mm')){
                              
                                return el;
                            }
                        }
                        else{
                            return el;
                        }
                        

                    }).filter(el=>{
                        if((el.theatreId === element.theatreId) &&(el.eventDate===filterDate) &&((el.language === filterLang)||(filterLang === "All Languages"))
                            &&((checkEventtime(el.eventTime)===filterTiming)||(filterTiming==="All Time"))&&((filterLocation=='')||(filterLocation==el.location))){
                                eventlang = el.language;

                               let temp = <div className={'timing_indic mt-2 mt-md-0 '+checkSeatAvailability(el,element)} key={`${element._id}-${el.eventTime }`} data-event={el._id} onClick={(event)=>handleEventclick(el._id)}>{moment(el.eventTime,["h:mm"]).format("hh:mm A")}</div>
                                eventtime.push(temp)
                            }
                    })
                    if(eventtime.length===0){
                        return null
                    }
                    else{
                        return displayDetails(element,eventtime,eventlang)
                    }
            }
            else{
                return null;
            }

        });
        return list;
    }

    
    const getShowdetails =async (eventdet) =>{    
    
        let allTheaterdet = eventdet.map((element)=>{
            return element.theatreId;
        });

        let theaterlistid = [...new Set(allTheaterdet)];
        let theatercollections =[]
        

        for(let i= 0;i<theaterlistid.length;i++){

           await getTheaterById(theaterlistid[i], (res)=>{theatercollections.push(res)})
        
        }

    
        let theatercollection = getTheaterList(theatercollections,eventdet);
      
        let filternull = theatercollection.filter(el=> el!=null)
  
        if(filternull){
            setValues(prevState=>({
                ...prevState,
                theaterevent : filternull
            }))
          
        }
        else{
            setValues(prevState=>({
                ...prevState,
                theaterevent : []
            }))
        }
    }


    useEffect( ()=>{
      
        getShowdetails(event_list);

    },[filterDate, filterLang, filterTiming,event_list,filterCinemas,filterLocation]);

 
    return (
       
        <div className='container-fluid row text-light mt-3 mb-5 mx-0 gx-0 ps-md-5'>
         
            {values.theaterevent.length===0 ? <div className='fs-6 text-center'>Nothing to show</div>: values.theaterevent}           
        </div>
       
    );
}

export default Venuedetail;
