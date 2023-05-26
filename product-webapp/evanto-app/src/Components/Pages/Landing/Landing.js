import React from 'react';
import './Landing.css';
import { Link } from "react-router-dom";
import Carousel from 'react-material-ui-carousel'
import "../../../CSS/App.css";




const Landing = () => {
   
    return (
        <div>
            <div className='topnav row col-12 d-flex align-items-center justify-content-center justify-content-md-start'>
                <div className='col-md-6'>
                    <div className='landing_brandlogo p-2 ms-2 mt-3'>
    
                         <img src="https://drive.google.com/uc?export=view&id=1k0fXDCm15MTgwyHQS3r1Y8EQG-XJjC93" className="w-100" alt="logo"/>
                    </div>
                     {/* <h2 className='text-left ps-md-3 pt-3 brandname display-2 text-center text-md-start'>EVENTO</h2> */}
                </div>
                <div className='col-md-6 d-flex flex-wrap flex-columnn justify-content-center justify-content-md-end'>
                <Link to="/login"><button className='btn btn-light border-0 mt-3 rounded-0 mx-1'>Login</button></Link>
                <Link to="/signup"><button className='btn btn-light border-0 mt-3 rounded-0 mx-1'>Signup</button></Link>

                </div>

            </div>
    
     
            <div className='carouselarea mt-4'>
                <Carousel className='carousel' autoPlay={true} interval={3000} animation={"slide"} stopAutoPlayOnHover={false} duration={1000} cycleNavigation={true} fullHeightHover={false} indicators={false} height={350}>
                    <div className='carouselitem' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1nmVzFbLQeqagnXfOTTcWvz2HnwpxtYjO)`}}>
                        
                    </div>
                    <div className='carouselitem' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1X_bGU_r9gyCUjIZwKEJDk_hpdWAR7W8u)`}}>
                            
                    </div>
                    <div className='carouselitem' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1udN5pwtXYEzlTSYX32XJJTjMFpp80pAV)`}}>
                            
                    </div>
                </Carousel>
    
            </div>
            <div className='mt-5'><p className='fs-2 text-center text-light p-1'>Got a show, event, activity or a great experience? Partner with us & get listed on <span className='brandname'>Evento</span></p></div>
           
           
            
           

            <div className='parallax_container mt-5' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1cAKKVkRWGm_vCTOmH45_SehZ3IuZygkg)`}}>

                <div className='col-12 text-light text-center col1_whatson p-1 pt-5 mt-5 message_card'>
                            <h1 className='display-4 col_header'>WHAT'S ON <span>TODAY</span></h1>
                            <p className='fs-5'>Explore Movies, Concerts and all events near you</p>
                            <p className='fs-6'>Choose your preferable seats and enjoy the show!</p>
                    </div>

                <div className='col-12 row px-3 py-5  d-flex justify-content-between card_layer mx-0 gx-0'>
                    <div className='card col-md-3 p-5 d-flex align-items-center justify-content-center eventcard me-3' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1Ux5YnvlS6_wEdZdfn-ndnSgXbFOGH4Dc)`}}>
                        <div className='overlay'></div>
                        <p className='display-5 text-light'>Movies</p>
                    </div>
                    <div className='card col-md-3 p-5 d-flex align-items-center justify-content-center eventcard me-3' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1ELjElNehXKwqmsGdgK5tI4XYp6qTFJYo)`}}>
                    <div className='overlay'></div>
                        <p className='display-5 text-light'>Music</p>
                    </div> 
                    <div className='card col-md-3 p-5 d-flex align-items-center justify-content-center eventcard' style={{'backgroundImage':`url(https://drive.google.com/uc?export=view&id=1Icjt95H2mYWZMDOlwNvOkDiiKLTzjkXr)`}}>
                    <div className='overlay'></div>
                        <p className='display-5 text-light'>Comedy</p>
                    </div>
                </div>
                
            </div>
            <div className='landing_footer'>
                {/* <p className='text-light text-center fs-5 pt-4'>Connect with us on</p> */}
                <div className='d-flex flex-row flex-wrap text-dark justify-content-center contact_list pt-5 text-light'>
                    <p className='text-light  pe-4'>evento.bookconfirmation@gmail.com</p><p className=' pe-4 divider'>|</p><p className='text-light  pe-4'>+91 8489397667</p><p className=' pe-4 divider'>|</p><p className='text-light '>Chennai, India</p>
                </div>
                <div className='d-flex flex-row text-dark justify-content-center fs-5 pt-3'>
                    <div className='socialmedia_container'>
                    <a href='#' target="_blank" className='text-decoration-none'><p className='me-3 socialmedia_icon d-flex align-items-center justify-content-center'>
                    <i className="fa-brands fa-instagram"></i>
                    </p></a>
                    </div>

                    <div className='socialmedia_container'>
                    <a href='#' target="_blank" className='text-decoration-none'><p className='me-3 socialmedia_icon d-flex align-items-center justify-content-center'>
                    <i className="fa-brands fa-facebook-f"></i>
                    </p></a>
                    </div>

                    <div className='socialmedia_container'>
                    <a href='#' target="_blank" className='text-decoration-none'><p className='me-3 socialmedia_icon d-flex align-items-center justify-content-center'>
                    <i className="fa-brands fa-twitter"></i>
                    </p></a>
                    </div>
                    
              
                </div>
            </div>
         

        </div>
    );
}



export default Landing;
