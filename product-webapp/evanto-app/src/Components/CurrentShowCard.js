import React from "react";
import { Link } from "react-router-dom";
import { Col, Row } from "reactstrap";
import Ratings from "./Ratings";
import moment from 'moment'

export default function CurrentShowCard({ theatreId, showId, img_src, title, genreOfEvent, language, eventDate, eventTime, eventRating, theater_name }) {
  return (
    <Link to={`/ShowBookingHistory/${theatreId}/${showId}`} className="text-decoration-none">
      <Row className="bg-white text-black m-2">
        <Col md={4} className=" align-self-center m-0 p-0">
          <img
            style={{ width: "100%", height: 160, objectFit: "cover" }}
            className="card-img-top"
            alt="Poster"
            src={"data:image;base64," + img_src}
          />
        </Col>
        <Col md={8} className="align-self-center px-4 py-2">
          <h5 >{title}</h5>
          <h6 >{theater_name}</h6>
          <h6 >{genreOfEvent}</h6>
          <h6 >{language}</h6>
          <h6 >{eventDate} ({moment(eventTime, 'HH:mm:ss').format('hh:mm a')})</h6>

          <Ratings value={Number(eventRating)} total={5} />
        </Col>
      </Row>
    </Link>
  );
}
