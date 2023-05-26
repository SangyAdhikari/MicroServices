
import React, {useState, useEffect}from 'react';
import DatePicker from "react-datepicker";
import './Userhistoryfilter.css';
import "react-datepicker/dist/react-datepicker.css";



const Userhistoryfilter = ({handleSearchchange,searchfilter,handleDatechange,startDate,handleClear}) => {

    const handleClose = ()=> {
        handleSearchchange('')
    }

    return (
        <div className='container-fluid row text-light mt-5 ps-md-5' id='userhistory_filter'>
            <div className='d-flex flex-row flex-wrap text-start py-3  align-items-center justify-content-center justify-content-md-between border border-start-0 border-end-0'>
                <div className="pt-1 pe-2">
                        <form className='d-flex flex-row flex-nowrap align-items-baseline formsearch' onSubmit={(event)=>{event.preventDefault()}}>
                            <i className="fa-solid fa-magnifying-glass"></i>    
                            <input type="text" className="ps-3 w-100" autoComplete="off" id="venue_searchbox" placeholder='Search events...' data-statevalue="filterCinemas" value={searchfilter} onChange={(event)=>handleSearchchange(event.target.value)} />
                            {
                                 searchfilter ?  <i className="fa-solid fa-xmark text-light pe-2 fs-5" onClick={handleClose}></i> : ''
                            }
                        </form>
                 </div>
                <div className='d-flex flex-row flex-nowrap justify-content-start mt-4 mt-md-0'>
                    <DatePicker selected={startDate} dateFormat="dd-MM-yyyy" onChange={(date) => handleDatechange(date)}  className='w-100' placeholderText={'Filter by Date'} /> 
                    {
                        startDate ?  <div className='clear_date' onClick={handleClear}>Clear</div> : ''
                    }
                
                </div>
            </div>
        </div>
    );
}

export default Userhistoryfilter;
