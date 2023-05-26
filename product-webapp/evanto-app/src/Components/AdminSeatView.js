
import React, { useState } from 'react';
import { Button } from 'reactstrap';
import './Seatinglayout.css';
import { useNavigate } from 'react-router-dom'

const AdminSeatView = ({ image, theatreName, bookedSeats, seatcount, eventName, language, eventDate, eventTime, location }) => {

    let navigate = useNavigate()
    const [values] = useState({
        selectedseat: [],
        totalprice: "",
        seatsperrow: 10,
        ticketprice: 120
    });




    function numToSSColumnLetter(num) {
        let columnLetter = "", t;
        while (num > 0) {
            t = (num - 1) % 26;
            columnLetter = String.fromCharCode(65 + t) + columnLetter;
            num = (num - t) / 26 | 0;
        }
        return columnLetter || undefined;
    }

    const seats = () => {
        let col = values.seatsperrow;
        let row = seatcount / col;
        if (seatcount % col !== 0) {
            row = Math.ceil(row);
        }
        let seatui = [];
        for (let i = 1; i <= row; i++) {
            let seatcol = [];
            let seatrow = numToSSColumnLetter(i);

            for (let j = 1; j <= col; j++) {
                let seatnumber = ((i - 1) * col) + j;
                let isReserved = bookedSeats.includes(`${seatrow}${j}`);
                // let isReserved = 10

                let seat = <div key={`${seatrow}${j}`} id={`${seatrow}${j}`} className='seatui w-auto col-1' ><div className={'d-flex flex-nowrap align-items-center justify-content-center seatnum ' + (isReserved ? 'reserved' : 'available')}>{seatrow}{j}</div></div>


                seatcol.push(seat);

                if (seatnumber === seatcount) {
                    break;
                }
            }
            seatui.push(<div id={`row-${seatrow}`} className='row d-flex flex-nowrap align-items-center justify-content-center seatrow mx-0' key={i}>{seatcol}</div>)
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
                        <div className='indication_icon indication_icon_reserve me-3'></div>
                        <p className='indication'>Reserved</p>
                    </div>
                </div>
                <div className='seats row d-flex flex-column flex-nowrap justify-content-center text-light mt-1 col-12  mx-0 gx-0'>

                    <div className='text-light text-center'>Screen this way</div>
                    <div className='d-flex justify-content-center'> <div className='screen mb-5 mt-2 justify-content-center'></div></div>

                    {
                        seats()
                    }
                </div>


            </div>
            <div className='row align-center justify-content-center mx-0 gx-0 col-12 col-lg-5 '>
                <div className='row d-flex flex-row text-dark justify-content-center mt-5 mx-0 gx-0 px-4'>
                    <div className='card bg-light d-flex flex-column align-items-center ticket_card shadow-lg mx-0 gx-0' style={{ 'maxWidth': '560px', 'minWidth': "250px", 'height': 'fit-content' }}>

                        <div className='text-center my-2'>
                            <img
                                style={{ width: "auto", height: 200 }}
                                className="card-img-top"
                                alt="Poster"
                                src={"data:image;base64," + image}
                            />
                        </div>
                        <div className='py-2'>
                            <h4 className='my-3 text-center'>{eventName} </h4>

                            <div className='d-flex flex-row flex-nowrap pt-1 ev_primary_clr justify-content-center'>
                                <div>
                                    <p className='text-dark pe-4'>Date :{eventDate}</p>
                                </div>

                                <div>
                                    <p className='text-dark pe-4'>Time : {eventTime}</p>
                                </div>

                            </div>

                            <div className='mt-2'>
                                Booked Seats: {bookedSeats.length} / {seatcount}
                            </div>
                            <div className='mt-2'>
                                Theater Name: {theatreName}
                            </div>

                            <div className='mt-2'>
                                Location: {location}
                            </div>

                        </div>

                        <div className='my-2'>
                            <Button className='text-white' color='warning' outline onClick={() => navigate('/ShowsHistory')}>Go Back</Button>
                        </div>

                    </div>
                </div>


            </div>

        </div>
    );
}

export default AdminSeatView;
