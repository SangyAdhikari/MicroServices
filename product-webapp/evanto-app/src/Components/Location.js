import React, {useState,useEffect} from 'react';
import $ from "jquery";
import './Location.css';
import state_list from '../data/db'

const Location = ({setuserlocation}) => {

    const [location, setLocation] = useState('');
    const [values, setValues] = useState({
        showstate: false,
        showcity: false
    });
    const [cities,setCities] = useState([]);
    const [states,setStates] = useState('');

    const [district,setDistrict] = useState({});

    const toggleState = () =>{
        setValues(prevState=>({
            ...prevState,
            showstate: !values.showstate,
            showcity: false
        }))
    }

    const handleDistrict = (event,districtname) =>{
        $('.districts').removeClass('filldistricts')
        $(`#${districtname}`).addClass("filldistricts");
        sessionStorage.setItem("Locationcomponent",districtname)
        setLocation(districtname);
        setuserlocation(districtname)
    }

    const showcity = (event,statename) =>{
        $('.states').removeClass('fillstate')
        $(event.target).addClass("fillstate"); 
      
        let districtlist = cities.filter(el=> el.state === statename)
        setValues(prevState=>({
            ...prevState,
            showcity: true
        }))

        setStates(statename)
        setDistrict(districtlist[0])

    }

    const getCitylist = async() =>{
        setCities(state_list.states);
    
    }
    const handleCloseclick = ()=>{
        setValues(prevState=>({
            ...prevState,
            showstate: false,
            showcity: false
        }))
    }


    const handlelocation = (distrct) =>{
            
               let final = state_list.states.forEach(el=>
                    el.districts.forEach(element =>{
                        if(distrct.includes(element)){
                            setStates(el.state);
                            setLocation(element);
                            setuserlocation(element);
                        }
                    })
                )
               return final;
        
    }

    useEffect(() => {

      getCitylist(); 
        let locationdet = sessionStorage.getItem("Locationcomponent")
        if(locationdet){
            handlelocation(locationdet);

        }
   

      },[]);

    return (
        <div>
           <div className='location col-12 pt-3'>
           
           <div className='text-light d-flex flex-row flex-nowrap align-items-baseline justify-content-center my-2'>
            <div onClick={toggleState}>
            <label className='location_label pe-3'>{location ? location : "Location"}</label>
             <i className="fa-solid fa-location-dot location_icon"></i>
            </div>
             
           </div>
         <div className="alllocation_card">
           <div   className={'statelist card mx-5 p-3 text-light '+(values.showstate ? 'show' : 'hide')} >
                <div className='close_icon' onClick={handleCloseclick}><i className="fa-solid fa-circle-xmark"></i></div>
                 {
                     <div className='row px-3'>
                         {cities && cities.map(el=> {
                             return (
                                
                                 <p onClick={(event)=>showcity(event,el.state)} id={el.state}  className={'px-2 py-1 states col-sm-6 col-md-4 col-lg-2 '+(el.state===states ? 'fillstate': '')} key={el.state}>{el.state}</p>
                             )
                         })}
                     </div>
                 }
              </div>

             <div   className={'citylist card mx-5 p-3 text-dark '+(values.showcity ? 'show' : 'hide')} >
                     <div className='row px-3'>
                         {((Object.keys(district).length) !==0) && district.districts.map(el=> {
                             return (
                                 <p onClick={(event)=>handleDistrict(event,el)} id={el} key={el} className={'px-2 py-1 districts col-sm-6 col-md-4 col-lg-2 '+(el===location ? 'filldistricts': '')}>{el}</p>
                             )
                         })}
                     </div>
                 
             </div>
           </div>
           
         </div>
        </div>
    );
}

export default Location;
