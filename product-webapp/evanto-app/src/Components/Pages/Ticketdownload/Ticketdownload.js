import React, {useState, useEffect} from 'react';
import jsPDF from 'jspdf'
import './Ticketdownload.css';
import { useParams } from 'react-router-dom';
import moment from 'moment';
import { getShowByid } from "../../../Services/BookingTicketService";
import { getTheaterById } from "../../../Services/RegisterService";
import { getEventByEventId, cancelTicket } from "../../../Services/EventDetailsService";
import { getBookingdetailsbyBookingid } from "../../../Services/BookingTicketService";
import swal from 'sweetalert';
import { useNavigate} from 'react-router-dom';



const Ticketdownload = () => {

    const  {bookingID}  = useParams();
    const navigate = useNavigate();

    const [values,setValues] = useState({
        bookingdetail: {},
        theaterDetail:{},
        eventDetail:{},
        showdetail : {}
    })

    const [errors,seErrors] = useState('');

    const getShowdetailsByid = (bookingdet) => {

        getShowByid(
            bookingdet.showId,
            (res) => {  
                setValues(prevState =>({
                    ...prevState,
                    showdetail: res
                }))
               
            },
            (err) => {
          
            seErrors('Nothing to show')
            }
        );

        getTheaterById(
            bookingdet.theatreId,
            (res) => {
                setValues(prevState =>({
                    ...prevState,
                    theaterDetail: res
                }))
               
            },
            (err) => {
                seErrors('Nothing to show')
              
            }
          );

          getEventByEventId(
            bookingdet.eventId,
            (res) => {
                setValues(prevState =>({
                    ...prevState,
                    eventDetail: res
                }))
            },
            (err) => {
                seErrors('Nothing to show')
            }
          );

    }

    const getBookingDetails = (bookingID) =>{

        getBookingdetailsbyBookingid(bookingID,(res)=>{
            setValues(prevState =>({
                ...prevState,
                bookingdetail: res
             }))
             getShowdetailsByid(res);
             
        },(err)=>{
            seErrors('Nothing to show')
        })
        
    }
    const cancelShow = () =>{
        swal({
            title: "Are you sure?",
            text: "You can't revert this action",
            icon: "warning",
            buttons: {
                true: "Proceed",
                cancel: "Cancel",
            },
            reverseButtons: false,
            dangerMode: true,
          }).then((isconfirm)=>{
                if(isconfirm){
                    const headers = {
                        'Content-Type': 'application/json',
                       
                      }
    
                      cancelTicket(bookingID, (res)=>{
                        swal({
                            title: "Cancellation successful !",
                            text: "Amount will be returned to your account in 3 days ",
                            icon: "success",
                          }).then(()=>{
                            navigate(`/userhistory`);
                          })
                      },(err)=>{
                        console.log(err)
                      })
    
    
                }
                
              

          })
    }

    const displayCancelbutton =() =>{
        let date = moment(values.bookingdetail.eventDate, 'DD-MM-YYYY').format('MM-DD-YYYY');
        let time = moment(values.bookingdetail.eventTime,'HH:mm:ss').format('HH:mm:ss')
        let datestr = (`${date} ${time}`).toString();
        const eventdatetime = moment(new Date(datestr),'MM-DD-YYYY HH:mm:ss')
        let today = moment().format('MM-DD-YYYY HH:mm:ss')
     
        const duration = moment.duration(moment(datestr).diff(moment(today)));
      
    
        if(Math.floor(duration.asHours())>=7){
            return (
                <button onClick={cancelShow} className='btn cancel_btn w-auto mt-2'>Cancel Ticket</button>
            )
        }
        else{
            return;
        }
    
    }

    const displayTicket = () => {
        return(
            <div className='card bg-light col-11 col-md-5 col-lg-4 px-3 '>
               
                    <div className='banner_img col-3 align-self-center'>
                        <img src={"data:image;base64," + values.eventDetail.image} className="w-100 rounded-3" />
                    </div>
                    <h4 className='text-dark event_title pb-2 text-center'>{values.eventDetail.eventName}</h4>
                    <div className='text-start'>
                        <div className='d-flex flex-row flex-wrap justify-content-between mt-2'>
                            <div className='col-7 col-sm-8 pe-3'>
                                <h6 className='ticket_gray'>Venue</h6>
                                <h6 className='ticket_black'>{values.theaterDetail.theatreName}</h6>
                            </div>
                            <div className='col-5 col-sm-4'>
                                <h6 className='ticket_gray'>Language</h6>
                                <h6 className='ticket_black'>{values.showdetail.language}</h6>
                            </div>
                           
                        </div>

                        <div className='d-flex flex-row flex-wrap justify-content-between mt-3'>
                             <div className='col-7 col-sm-8 pe-3'>
                                <h6 className='ticket_gray'>Date</h6>
                                <h6 className='ticket_black'>{values.bookingdetail.eventDate}</h6>
                            </div>
                            <div className='col-5 col-sm-4'>
                                <h6 className='ticket_gray'>Time</h6>
                                <h6 className='ticket_black'>{moment(values.bookingdetail.eventTime,["h:mm"]).format("hh:mm A")} </h6>
                            </div>
                           
                        </div>

                        <div className='d-flex flex-row flex-wrap justify-content-between mt-3'>
                             <div className='col-7 col-sm-8 pe-3'>
                                <h6 className='ticket_gray'>Booking Date</h6>
                                <h6 className='ticket_black'>{values.bookingdetail.bookingDate}</h6>
                            </div>
                            <div className='col-5 col-sm-4'>
                                <h6 className='ticket_gray'>Booking Time</h6>
                                <h6 className='ticket_black'>{values.bookingdetail.bookingTime}</h6>
                                {/* <h6 className='ticket_black'>{moment(values.bookingdetail.bookingTime,["h:mm"]).format("hh:mm A")}</h6> */}
                            </div>
                           
                        </div>

                        <div className='d-flex flex-row flex-wrap justify-content-between mt-3'>
                             <div className='col-7 col-sm-8 pe-3'>
                                <h6 className='ticket_gray'>Seat</h6>
                                <h6 className='ticket_black'>{values.bookingdetail.seatNumbers.map((el,index)=>{
                           return index == values.bookingdetail.seatNumbers.length-1 ? el : `${el}, `
                           })}</h6>
                            </div>
                            <div className='col-5 col-sm-4'>
                                <h6 className='ticket_gray'>Amount paid</h6>
                                <h6 className='ticket_black'>{values.bookingdetail.ticketFair}</h6>
                            </div>
                           
                        </div>



                        <div className='d-flex flex-row flex-wrap justify-content-between mt-3'>
                            <div className='col-7 col-sm-8 pe-3'>
                                <h6 className='ticket_gray'>Booking ID</h6>
                                <h6 className='ticket_black'>{values.bookingdetail._id}</h6>
                            </div>
                        </div>

                        <div className='d-flex flex-row flex-wrap justify-content-center pb-4 mt-2'>
                        <button onClick={generatePDF} className='btn dwld_btn w-auto mt-2 me-2'>Download</button>
                        {
                               displayCancelbutton()
                 

                        }

                        </div>
                        
                    </div>
                      
                
            </div>
        )
    }


    useEffect(()=>{
        bookingID && getBookingDetails(bookingID);    
    },[])


    function getDataUrl(url)
    {
        return new Promise(resolve => {
            var image = new Image();
            image.setAttribute('crossOrigin', 'anonymous'); 
    
            image.onload = function () {
                var canvas = document.createElement('canvas');
                canvas.width = this.naturalWidth;
                canvas.height = this.naturalHeight; 
                var ctx = canvas.getContext('2d');
                ctx.fillStyle = '#fff'; 
                ctx.fillRect(0, 0, canvas.width, canvas.height);
    
                canvas.getContext('2d').drawImage(this, 0, 0);
    
                resolve(canvas.toDataURL('image/jpeg'));
            };
    
            image.src = url;
        })
    }

 
    const generatePDF = async () => {

        let eventname = (values.eventDetail.eventName).toString();
        let theatername = (values.theaterDetail.theatreName).toString();
        let eventdate = (values.bookingdetail.eventDate).toString();
        let eventtime = (moment(values.bookingdetail.eventTime,["h:mm"]).format("hh:mm A")).toString();
        let language = (values.showdetail.language).toString();
        let cost = (values.bookingdetail.ticketFair).toString();
        let bookindate = (values.bookingdetail.bookingDate).toString();
        // let bookingtime = (moment(values.bookingdetail.bookingTime,["h:mm"]).format("hh:mm A")).toString();
        let bookingtime = (values.bookingdetail.bookingTime).toString();
        let seats = (values.bookingdetail.seatNumbers).toString();

        var logo = await getDataUrl("data:image;base64," + values.eventDetail.image);



      
        var doc = new jsPDF('portrait', 'px','a4','false');
        doc.setFontSize(14);
        doc.addImage(logo,'jpg',155,25,100,150);

        doc.text('EVENTO',185, 15)

        doc.text('Order ID',125, 200)
        doc.text(':',196, 200)
        doc.text(bookingID,210, 200)
        doc.text('Name',125, 220)
        doc.text(':',196, 220)
        doc.text(eventname,210, 220)
        doc.text('Venue',125, 240)
        doc.text(':',196, 240)
        doc.text(theatername,210, 240)
        doc.text('Date',125, 260)
        doc.text(':',196, 260)
        doc.text(eventdate,210, 260)
        doc.text('Time',125, 280)
        doc.text(':',196, 280)
        doc.text(eventtime,210, 280)
        doc.text('Language',125, 300)
        doc.text(':',196, 300)
        doc.text(language,210, 300)
        doc.text('Seat',125, 320)
        doc.text(':',196, 320)
        doc.text(seats,210, 320)
        doc.text('Ticket Price',125, 340)
        doc.text(':',196, 340)
        doc.text(cost,210, 340)
        doc.text('Booking Date',125, 360)
        doc.text(':',196, 360)
        doc.text(bookindate,210, 360)
        doc.text('Booking Time',125, 380)
        doc.text(':',196, 380)
        doc.text(bookingtime,210, 380)
        // window.open(doc.output('bloburl'), '_blank');
        doc.save(`${bookingID}.pdf`)
  }

    return (
        <div className='Eticket d-flex flex-row justify-content-center mt-4 muidrawer-padding'>
      
     
              {(((Object.keys(values.eventDetail).length) !==0) && ((Object.keys(values.theaterDetail).length) !==0) && ((Object.keys(values.showdetail).length) !==0) && ((Object.keys(values.bookingdetail).length) !==0)    ) ? displayTicket() : ''} 
            
           
        </div>
    );
}

export default Ticketdownload;
