import React from 'react';
import './Moviebanner.css';
import moment from 'moment';


const Moviebanner = ({event_det}) => {
    const displaygenre =( ) =>{
      
        if(event_det.genreOfEvent){
            if(event_det.genreOfEvent.includes(',')){
                event_det.genreOfEvent.split(',').map(element=>{
                    return <div className='genre_label me-3 mt-2' key={element}>{element}</div>
                })
            }
            else{
                return(
                    <div className='genre_label me-3 mt-2' key={event_det.genreOfEvent}>{event_det.genreOfEvent}</div>
                )
               }
        }       
     
         
    }


const imgUrl = "data:image;base64," + event_det.image;

    return (
        <div className='text-light row container-fluid me-0 gx-0'>
            <div className='col-12 px-0 banner'  style={{'backgroundImage':`url(${imgUrl})`}} > 
            {/* style={{'backgroundImage':`url(${imgUrl})`}} */}
                <div className='bannerscreen py-5'>
                    
                    {/* <div className='share_btn'>
                        <i className="fa-solid fa-share-nodes"></i> Share
                    </div> */}
                        <div className='d-flex justify-content-center justify-content-md-between container flex-wrap pt-3 pt-sm-5 pb-1 px-3 align-items-center'>
                                <div className='d-flex flex-column justify-content-center align-items-baseline pb-3 pb-sm-0 pe-3'>
                                    <h1 className='fs-1 ev_primary_clr mb-2 w-100 text-center text-md-start'>{event_det.eventName}</h1>
                                    <div className='d-flex flex-row align-items-baseline mb-3 justify-content-md-start justify-content-center w-100'>
                                        <p className='fs-6 ev_sc_gray_clr'>{moment(event_det.releaseDate).format('DD MMMM, YYYY')}</p>
                                        <p className='fs-6 ev_sc_gray_clr ms-4'>{`${event_det.eventDuration.slice(0,2)}h ${event_det.eventDuration.slice(-2)}m`}</p>
                                    </div>
                
                                    <div className='d-flex flex-row align-items-baseline mb-3 w-100 justify-content-center justify-content-md-start flex-wrap'>  
                                    
                                        {

                                            displaygenre()
                                           
                                        }
                                 
                                    </div>
                                    <div className='rating d-flex flex-row user align-items-baseline w-100 justify-content-center justify-content-md-start'>
                                        <i className="fa-solid fa-star ev_primary_clr fs-6"></i>
                                        <p className='ms-2 fs-6'><span className='ev_primary_clr'>{(event_det.eventRating).toPrecision(2)}</span>/5</p>
                                    </div>
                                
                                </div>
                                {/* <div>
                                    <img src={imgUrl}/>
                                </div> */}
                                <div className='d-flex flex-column justify-content-center align-items-baseline'>
                                    <h5 className='mb-2 text-center w-100'>Cast and crew</h5>
                                        <div className='d-flex flex-row flex-nowrap justify-content-center align-items-center ev_sc_gray_clr w-100 '>
                                            {
                                                event_det.castAndCrew.map(element => {
                                                    return  <p className='fs-6 pe-3 ' key={element}>{element}</p>
                                                })
                                            }
                            
                                    </div>
                                </div>
                        </div>
                     </div>
            </div>
            
             <div className='row container text-start ps-md-5'><h3 className='fs-3 pt-4'>About the {event_det.eventType.toLowerCase()}</h3>
               <p className='fs-6'>{event_det.eventDescription}</p></div> 
            
        
        </div>
       
    );
}

export default Moviebanner;
