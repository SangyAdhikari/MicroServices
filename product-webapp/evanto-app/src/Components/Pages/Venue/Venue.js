
import React, {useEffect,useState}  from 'react';
import Moviebanner from './../../Moviebanner';
import Venueselection from './../../Venueselection';
import { useParams } from 'react-router-dom';
import Location from './../../Location';
import { getEventByEventId } from "../../../Services/EventDetailsService";


const Venue  = () =>{
    const  {eventId}  = useParams();
    const [errors,setErrors] = useState('')
    const [userlocation, setuserlocation] = useState('');
    const [values,setValues] =useState({
        eventdet: {} 
    })

    const getEventdetails = async (id) =>{
 
        getEventByEventId(
            id,
            (res) => {
                setValues(prevState =>({
                    ...prevState,
                    eventdet: res
                }))
            },
            (err) => {
                setErrors(err)
            }
          );
    }

    useEffect(()=>{
        eventId && getEventdetails(eventId)
    },[])
   
  
    return(
        <div className='venue muidrawer-padding'>
               <div className="row container-fluid col-12 justify-content-center mb-2 mt-1">
                    <Location setuserlocation={setuserlocation}/>
               </div>
        
          {(Object.keys(values.eventdet).length) !==0 ? <Moviebanner event_det={values.eventdet}/> : ''}
          {(Object.keys(values.eventdet).length) !==0 ? <Venueselection event_det={values.eventdet} userlocation={userlocation} /> : ''}
            
        </div>
    )
}   

export default Venue;