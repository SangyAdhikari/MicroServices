import React from "react";
import CurrentShowCard from "./CurrentShowCard";
import { Row, Col } from "reactstrap";

export default function NowShowing({ Events }) {
  return (
    <Row className="mt-2">
      {Events.map((data, index) => (
        <Col lg={4} md={6} key={index}>
          {
            <CurrentShowCard
              theatreId={data.theatreId}
              showId={data.showId}
              img_src={data.image}
              title={data.eventName}
              genere={data.genreOfEvent}
              language={data.language}
              eventRating={data.eventRating}
              theater_name={data.theater_name}
              eventDate={data.eventDate}
              eventTime={data.eventTime}
            />
          }
        </Col>
      ))}
      {
        Events.length === 0 &&
        <div className="text-center text-orange">No Datas Found</div>
      }
    </Row>
  );
}
