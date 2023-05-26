import React,{useState,useEffect} from 'react';
import Venuedetail from './Venuedetail';
import './Venueselection.css';
import $ from "jquery";
import moment from 'moment';
import { getShowByLocationEventid } from "./../Services/BookingTicketService";



const Venueselection = ({event_det,userlocation}) => {

    const [errors,setErrors] = useState('')
    const [value,setValue] = useState({
        filterdatelist:["Morning","Afternoon","Evening","Night","All Time"],
        filterLang: "All Languages",
        filterTiming: "All Time",
        event_list:[],
        filterdate_count: 6,
        filterDate : moment().format('DD-MM-YYYY'),
        filterCinemas: ''
        
    })

    function getDatelist(count){
        let datelist = [];
        for(let i=0;i<count;i++){
               
            datelist.push({
               "month": moment().add(i,'days').format('MMM'),
               "date": moment().add(i,'days').format('DD'),
               "key": moment().add(i,'days').format('DD-MM-YYYY')
            })
        }
      return datelist;
    }

    const getshows = (id,location) =>{

        getShowByLocationEventid(
            id, location,
            (res) => {
                setValue(prevState =>({ 
                    ...prevState,
                    event_list: res
                }))
            },
            (err) => {
              setErrors(err)
            }
          );
            
    }
 

    useEffect(()=>{

        userlocation && getshows(event_det._id,userlocation);
        
    },[userlocation])

    const getLanguagelist = (event_list) =>{
        let alllang = event_list.map(el=>{
            return el.language
        })
        let langlist = [...new Set(alllang)];
        langlist.push('All Languages');
        return langlist
    }

    const handleDateselector = (event) =>{
        
        $('.date_selector').removeClass('active');
        event.target.classList.add('active');
        setValue(prevState=>({
            ...prevState,
            filterDate: event.target.getAttribute('data-date')
        }))

    }

    const handleFilterval = (event) =>{
      let stateValue = event.target.getAttribute('data-statevalue');
        setValue(prevState=>({
            ...prevState,
            [stateValue]: event.target.value
        }))
    }

    const handleSearch = (searchkey) =>{
        setValue(prevState=>({
            ...prevState,
            filterCinemas: searchkey
        }))
    }

    const handleClose = () =>{
        setValue(prevState=>({
            ...prevState,
            filterCinemas: ''
        }))
    }

    return (
        <div className='venueselection'>
             <div className='container-fluid row text-light mt-5 ps-md-5 mx-0 gx-0'>
                <div className='row col-12 text-start py-3 venue_search align-items-center border border-start-0 border-end-0'>
                    <div className='d-flex flex-row col-12 col-md-6 flex-wrap'>

                            {getDatelist(value.filterdate_count).map(element=>{
                               
                                return(
                                    
                                    <div className={'date_selector d-flex flex-column align-items-center justify-content-around me-3 mt-2 '+(element.key==moment().format('DD-MM-YYYY') ? 'active': '')}  onClick={(event)=>handleDateselector(event)} key={element.key} data-date={element.key}>
                                        <div className='month_indic'>{element.month}</div>
                                        <div className='date_indic'>{element.date}</div>
                                    </div>
                                )
                            })}
                       
                    </div>
                    <div className='d-flex flex-row col-12 col-md-6 pt-4  pt-md-0 justify-content-md-end'>
                        <div className='pe-4'>
                        <label className="dropdown-toggle dropdown-toggle-language fs-6" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">{value.filterLang ? value.filterLang : "All Languages"}</label>
                                

                            <ul className="dropdown-menu" aria-labelledby="dropdownMenu2">
                                {  getLanguagelist(value.event_list) ? getLanguagelist(value.event_list).map((el)=>{
                                            return (
                                                <li key={`lang-${el}`}><button className="dropdown-item" type="button" data-statevalue="filterLang" value={el}  onClick={(event)=>handleFilterval(event)}>{el}</button></li>
                                            )
                                        }) 
                                        :  
                                    <li key="lang-All Languages"><button className="dropdown-item" type="button" value="All Languages" data-statevalue="filterLang"  onClick={(event)=>handleFilterval(event)}>All Languages</button></li>
                                }
                            </ul>
                        </div>
                        <div className='ps-4 pe-4 border-start'>
                        <label className="dropdown-toggle dropdown-toggle-language fs-6" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">{value.filterTiming ? value.filterTiming : "Timing"}</label>
                            <ul className="dropdown-menu" aria-labelledby="dropdownMenu2">

                                {
                                    value.filterdatelist.map(el=>{
                                        return (
                                            <li key={el}><button className="dropdown-item" type="button" value={el} data-statevalue="filterTiming" onClick={(event)=>handleFilterval(event)} >{el}</button></li>
                                        )
                                    })
                                }
                                
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div className='container-fluid row text-light mt-2 mt-md-5 ps-md-5'>
                <div className='d-flex flex-row flex-wrap align-items-center justify-content-between text-start py-3 venue_search align-items-center'>
                    <div className="pt-3 pe-2">
                        <form className='d-flex flex-row flex-nowrap align-items-baseline formsearch' autoComplete='off' onSubmit={(event)=>{event.preventDefault()}}>
                            <i className="fa-solid fa-magnifying-glass"></i>    
                            <input type="text" className="ps-3 w-100" id="venue_searchbox" placeholder='Search cinemas...' data-statevalue="filterCinemas" onChange={(event)=>handleSearch(event.target.value)} value={value.filterCinemas} />
                            {
                                 value.filterCinemas ?  <i className="fa-solid fa-xmark text-light pe-2 fs-6" onClick={handleClose}></i> : ''
                             }
                        </form>
                    </div>
                    
                    <div className='me-5 d-flex flex-row flex-wrap pt-3'>
                        <p className='venue_avail_indic avail_indicator fs-6 pe-4'>Available</p>
                        <p className='venue_avail_indic moderate_indicator fs-6 pe-4'>Fast filling</p>
                        <p className='venue_avail_indic soldout_indicator fs-6'>Sold out</p>
                    </div>
                </div>
            </div>

            <Venuedetail filterDate={value.filterDate} filterLang={value.filterLang} filterTiming={value.filterTiming}  event_list={value.event_list} filterCinemas={value.filterCinemas} filterLocation={userlocation}/>
        </div>
    );
}

export default Venueselection;
