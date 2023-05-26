
import React,{useState,useEffect} from 'react';
import './Seatinglayout.css';
import $ from 'jquery';
import moment from 'moment';
import { useNavigate} from 'react-router-dom';
import { getShowByid } from "./../Services/BookingTicketService";
import { createOrder,addPaymentdetails } from "./../Services/PaymentService";
import { bookShow } from "./../Services/BookingTicketService";





const Seatinglayout = ({theater_det,event_det,show_det}) => {
    const navigate = useNavigate();

    const [email, setEmail] = useState('')
    const [errors,setErrors] = useState('')
    const [values, setValues] = useState({
        selectedseat : [],
        totalprice: "",
        seatsperrow: 10,
        ticketprice: 120
    });

    const [showdetail,setShowdet] =useState(show_det)


  
 

    useEffect(()=>{
   
        if(values.selectedseat.length){
            setValues(prevState =>({
                ...prevState,
                totalprice: values.selectedseat.length *values.ticketprice
            }))
        }

        const logindetails = JSON.parse(sessionStorage.getItem('logindetails'))
        if(logindetails){
           setEmail(logindetails.userEmail)

        }

    },[values.selectedseat,showdetail])


    const seatclicked = (seatnum) =>{
        if($(`#${seatnum} div`).hasClass('seat_selected')){
   
            const rmv_seat = values.selectedseat.indexOf(seatnum);
            if (rmv_seat > -1) {
                values.selectedseat.splice(rmv_seat, 1); 
            }
        
            setValues(prevState =>({
                ...prevState,
                selectedseat: [...values.selectedseat],
            }))

            $(`#${seatnum} .seatnum`).removeClass("seat_selected");
        }
        else{
           
                setValues(prevState =>({
                    ...prevState,
                    selectedseat: [...values.selectedseat, seatnum],
                }))
    
                $(`#${seatnum} .seatnum`).addClass("seat_selected");
           
           
        }

    }


    function numToSSColumnLetter(num) {
        let columnLetter = "",t;
        while (num > 0) {
          t = (num - 1) % 26;
          columnLetter = String.fromCharCode(65 + t) + columnLetter;
          num = (num - t) / 26 | 0;
        }
        return columnLetter || undefined;
      }

// --------------------Payment part

const loadScript = (src) => {
	return new Promise((resolve) => {
		const script = document.createElement('script')
		script.src = src
		script.onload = () => {
			resolve(true)
		}
		script.onerror = () => {
			resolve(false)
		}
		document.body.appendChild(script)
	})
}

const razorPayHandler = (razorresponse) =>{


    let bookingdetail = {
            "showId": showdetail._id,
            "userEmailId": email,
            "seatNumbers": values.selectedseat,
            "paymentId":razorresponse.razorpay_payment_id, 
    }
    const headers = {
        'Content-Type': 'application/json',
      }

      bookShow(bookingdetail,(response)=>{
      
        
               let dataobj = {
                payment_id:razorresponse.razorpay_payment_id,
                order_id:razorresponse.razorpay_order_id,
                bookingId: response._id
            }
            let datastr = JSON.stringify(dataobj);

            addPaymentdetails(datastr, (res)=>{
               
               
                getShowByid(
                    showdetail._id,
                    (res) => {
                        setShowdet(res)
                    },
                    (err) => {
                      setErrors(err)
                    }
                );
                setValues(prevState =>({
                    ...prevState,
                    totalprice: '',
                    selectedseat : [],

                }))
                navigate(`/ticket/${response._id}`);

            })

      })


}

const razorPayOpen =(response) =>{
   
    const options = {
        key:'rzp_test_B2hboJXaufQJZD',
        amount:response.data.amount,
        currency:response.data.currency,
        order_id: response.data.id,
        name: event_det.eventName,
        description: 'Pay and enjoy the show',
        // image: logo,
        handler: function(response) {
            razorPayHandler(response)
        },
        prefill: {
            email: email,
            contact: ''
        },
        theme: {
            color: "#1a1a1a"
        }
    }
    const paymentObject = new window.Razorpay(options)
    paymentObject.open()
}

    const proceed = async () =>{
     

        const res = await loadScript('https://checkout.razorpay.com/v1/checkout.js')
        if (!res) {
			alert('Razorpay SDK failed to load. Are you online?')
			return
		}

        let obj = {
            amount:values.totalprice,
            emailId: email,
            info:'Order_request'
        }

        let data = JSON.stringify(obj);
       
        const headers = {
            'Content-Type': 'application/json',
           
          }

          createOrder(data, (res)=>{
          
            razorPayOpen(res)
          }, (err)=>{
            console.log(err)
          })

}

    const seats = (seatcount) =>{
        let col = values.seatsperrow;
        let row = seatcount / col;
        if(seatcount%col!==0){
            row = Math.ceil(row);
        }
            let seatui = [];
            for(let i=1;i<=row;i++){
                let seatcol =[];
                let seatrow =numToSSColumnLetter(i);
               
             for(let j=1;j<=col;j++){
                let seatnumber = ((i-1)*col)+j;
                let isReserved = showdetail.bookedSeats.includes(`${seatrow}${j}`);

                let seat = <div key={`${seatrow}${j}`} id={`${seatrow}${j}`} className='seatui w-auto col-1'
               onClick={ isReserved==true ? () => false: ()=>{ seatclicked(`${seatrow}${j}`);}}><div className={'d-flex flex-nowrap align-items-center justify-content-center seatnum '+(isReserved ? 'reserved': 'available')}>{seatrow}{j}</div></div>

                
                seatcol.push(seat);
             
                if(seatnumber === seatcount){
                    break;
                }  
             }
             seatui.push(<div id={`row-${seatrow}`}className='row d-flex flex-nowrap align-items-center justify-content-center seatrow mx-0' key={i}>{seatcol}</div>)
            }
            return seatui;

    }


    return (
        <div className='mt-3 container-fluid row gx-0'>
            
            <div className='row align-center justify-content-left mx-0 gx-0 col-lg-7 seat_container'>
          
                <div className='row d-flex flex-row text-light justify-content-center mb-3  mt-3'>
                    <div className='d-flex flex-row w-auto py-2'>
                        <div className='indication_icon indication_icon_avail me-3'></div>
                        <p className='indication'>Available</p>
                    </div>
                    <div className='d-flex flex-row w-auto py-2'>
                        <div className='indication_icon indication_icon_select me-3'></div>
                        <p className='indication'>Selected</p>
                    </div>
                    <div className='d-flex flex-row w-auto py-2'>
                        <div className='indication_icon indication_icon_reserve me-3'></div>
                        <p className='indication'>Reserved</p>
                    </div>
                </div>
                <div className='seats row d-flex flex-column flex-nowrap justify-content-center text-light mt-1 col-12  mx-0 gx-0'>
               
                    <div className='text-light text-center'>Screen this way</div>
                    <div className='d-flex justify-content-center'> <div className='screen mb-5 mt-2 justify-content-center'></div></div>
                
                        { theater_det.numberOfSeats && seats(theater_det.numberOfSeats)}
                </div>
            
            
            </div>
            <div className='row align-center justify-content-center mx-0 gx-0 col-12 col-lg-5 '>
                <div className='row d-flex flex-row text-dark justify-content-center mt-5 mx-0 gx-0 px-4'>
                <div className='card bg-light d-flex flex-column align-items-center ticket_card shadow-lg mx-0 gx-0' style={{'maxWidth':'520px','minWidth':"250px",'height':'fit-content'}}>
                    <div className='py-2'>
                            <h4 className='mb-0 text-center'>{event_det.eventName} <span className='fs-6'>({showdetail.language})</span></h4>
                      
                            <div className='d-flex flex-row flex-nowrap pt-1 ev_primary_clr justify-content-center'>
                            <p className='text-dark pe-4'>Date &#38; time : {moment(showdetail.eventDate, 'DD-MM-YY').format('DD MMMM, YYYY')}</p>
                            <p className='text-dark pe-4'>{moment(showdetail.eventTime,["h:mm"]).format("hh:mm A")}</p>
                           
                         </div>
                        
                    </div>
                
                   <div className='pt-1'>
                    {
                 
                        values.selectedseat.length ?  <div className='d-flex flex-column align-items-center px-4'>
                        <p className='fs-6 text-center'>Seats: <span className='ev_primary_clr'>{values.selectedseat.map((el,index)=>{
                            return values.selectedseat.length-1==index ? `${el}`: `${el}, `
                            }
                              
                            )}</span></p> <p className='fs-6 text-center'>Total price: <span className='ev_primary_clr'>{values.totalprice}</span></p>
                            <button className='btn my-2 proceed_btn rounded-0 text-center' onClick={proceed}>Proceed</button>
                        </div> :<p className='py-2 ev_primary_clr'>Select your seat to enjoy the show</p>
                    }
                   
                   </div>
                       
                        
                </div>
                </div>
                  
                  
            </div>
           

         </div>
    );
}

export default Seatinglayout;
