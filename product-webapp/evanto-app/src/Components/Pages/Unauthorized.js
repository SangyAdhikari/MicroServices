import React from 'react';
import notfound from '../../media/images/404.png';
import { Link } from "react-router-dom";
import './Unauthorized.css';

const Unauthorized = ({state}) => {

    return (
        <div className='muidrawer-padding'>
            <div className='container d-flex flex-column align-items-center jutify-content-center'>
                
                 <p className="text-light text-center display-5 pt-5">We couldn't find this page</p>
                 <Link to={'/'}> <button className='btn btn-light border-0 rounded-0 mt-4'>Return to Home</button></Link>
                 <div className='notfound_img mt-4'>
                 <img src={notfound}  className="w-100"/>
                 </div>
                 

            </div>
        </div>
    );
}

export default Unauthorized;
