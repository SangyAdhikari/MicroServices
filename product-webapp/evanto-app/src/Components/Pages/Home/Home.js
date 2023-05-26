import React, { useState, useEffect } from "react";
import RecommendedEvents from '../../RecommendedEvents';
import './Home.css';    
import { getEventByEventtype } from "../../../Services/EventDetailsService"



export default function Home() {


  const [movies, setMovies] = useState([]);
  const [musicEvents, setMusicEvents] = useState([]);
  const [standupComedy, setStandupComedy] = useState([]);
  const [filterkey, setFilterkey] = useState('');

  const [showError, setShowError] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  const [userType, setUsertype] =useState('')

  const handleFilterval = (searchkey) =>{
    setFilterkey(searchkey)
  }



  useEffect(() => {
    getMoviesAPI();
    getEventsAPI();
    getStandupComedyAPI();


  let UserAccount = sessionStorage.getItem("logindetails");
  if(UserAccount){
    UserAccount = JSON.parse(UserAccount);
    setUsertype(UserAccount.userType)
  }
 

  }, [filterkey]);

  // getEventByEventtype('Movie',)



  const getMoviesAPI = () => {
    getEventByEventtype(
      'Movie',
      (res) => {
        setMovies(res);
      },
      (err) => {
        console.log(err);
        setShowError(true); 
        setErrorMsg(err.response.data) 
      }
    );
  
  };

  const getEventsAPI = () => {
 
      getEventByEventtype(
        'Music',
        (res) => {
          setMusicEvents(res);
        },
        (err) => {
          console.log(err);
          setShowError(true); 
          setErrorMsg(err.response.data) 
        }
      );
  };

  const getStandupComedyAPI = () => {
     getEventByEventtype(
        'Comedy',
        (res) => {
          setStandupComedy(res);
        },
        (err) => {
          console.log(err);
          setShowError(true); 
          setErrorMsg(err.response.data) 
        }
      );
  };
  const handleClose = () =>{
    handleFilterval('');
 }

  return (
    <div className="container muidrawer-padding home" id="home">
            <div className="row container-fluid col-12 justify-content-center mb-2 mt-4">
              <div className="pt-3 col-12 col-md-5">
                  <form className='d-flex flex-row flex-nowrap align-items-baseline formsearch w-100'>
                      <i className="fa-solid fa-magnifying-glass text-light"></i>    
                      <input type="text" className="ps-3 pe-2 w-100" id="venue_searchbox" placeholder='Search Events...' data-statevalue="filterCinemas" autoComplete='off' onChange={(event)=>handleFilterval(event.target.value)} value={filterkey} />
                      {
                          filterkey ?  <i className="fa-solid fa-xmark text-light pe-2 fs-5" onClick={handleClose}></i> : ''
                      }
                    
                  </form>
              </div>
            </div>
            {
              userType == "User" ?  <div className="mt-5 text-light text-center p-2 fs-3">Hi, Pick any events to book <span className="text-orange">show</span></div> :  <div className="mt-5 text-light text-center p-2 fs-3">Hi, Pick any events to create <span className="text-orange">shows</span></div> 
            }

           

            

      <RecommendedEvents events_list={movies} title="Movie" searchkey = {filterkey}  />
      <RecommendedEvents events_list={musicEvents} title="Music Concerts" searchkey = {filterkey}  />
      
      <RecommendedEvents events_list={standupComedy} title="Stand up comedy" searchkey = {filterkey}  />
    </div>
  );
}
