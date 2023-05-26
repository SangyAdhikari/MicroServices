import React from 'react';
import moment from 'moment';
import './Theaterdetail.css';

const Theaterdetail = ({theater_det,show_det}) => {


    return (
        <div className='theaterdetail py-4 text-start row me-0 gx-0 ps-3 ps-md-5 mt-0'>
            <h2 className='ev_primary_clr'>{theater_det.theatreName}</h2>
            <p className='theater_street'>{theater_det.street}, {theater_det.district}, {theater_det.state} - {theater_det.pinCode}</p>
            <div className='d-flex flex-row flex-nowrap pt-1 ev_primary_clr'>
                <p className='text-light pe-4'>{moment(show_det.eventDate, 'DD-MM-YY').format('DD MMMM, YYYY')}</p>
                <p className='text-light pe-4'>{moment(show_det.eventTime,["h:mm"]).format("hh:mm A")}</p>
                <p className='text-light'>{show_det.language}</p>
            </div>
          
            
        </div>
    );
}

export default Theaterdetail;
