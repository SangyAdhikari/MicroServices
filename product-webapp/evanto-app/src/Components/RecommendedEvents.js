import React, { useState, useEffect } from "react";
import MovieCard from "./MovieCard";
import { Row, Col } from "reactstrap";
import Carousel from "react-elastic-carousel";
import "../CSS/Carousel.css";


export default function RecommendedMovies({ title, events_list,searchkey}) {
  const [toggle, setToggle] = useState(true);


  let filterval = searchkey.toUpperCase().replaceAll(" ","");


  const matchedresult = events_list.filter(el=>
    {

      let eventname = el.eventName.toUpperCase().replaceAll(" ","");
      return  eventname.includes(filterval)
  })



  const breakPoints = [
    { width: 1, itemsToShow: 1 },
    { width: 550, itemsToShow: 3, itemsToScroll: 2 },
    { width: 768, itemsToShow: 5 },
    { width: 1200, itemsToShow: 6 },
  ];


  

  return (
    matchedresult.length ?

    <div className="my-4">
      <Row>
        <Col md={6}>
          <h4 className="text-white text-start">{title}</h4>
        </Col>
        <Col className="text-end" md={6}>
          <h6
            className="text-orange"
            onClick={() => setToggle(!toggle)}
            role={"button"}
          >
            {toggle ? "See All" : "Minimize All"}
          </h6>
        </Col>
      </Row>
      {toggle ? (
        <div className="carousel-wrapper">
          <Carousel breakPoints={breakPoints}>
            {matchedresult.map((data, index) => (
              
              <MovieCard
                eventRating={data.eventRating}
                releasedLanguages={data.releasedLanguages}
                key={index}
                name={data.eventName}
                eventPoster={data.image}
                eventId={data._id}
              /> 
            ))}
          </Carousel>
        </div>
      ) : (
        <Row className="justify-content-evenly">
          {matchedresult.map((data, index) => (
            <Col md={2} xs={10} sm={6} key={index}>
              <MovieCard
                eventRating={data.eventRating}
                releasedLanguages={data.releasedLanguages}
                name={data.eventName}
                eventPoster={data.image}
                eventId={data._id}
              />
            </Col>
          ))}
        </Row>
      )}
    </div> : ""
  );
}
