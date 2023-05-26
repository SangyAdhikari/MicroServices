import React from "react";
import { Row, Col } from "reactstrap";

export default function Trending({ trending, title }) {
  return (
    <>
      <Row>
        <Col md={6}>
          <h5 className="text-white">{title}</h5>
        </Col>
      </Row>
      <Row className=" text-center ">
        {trending.map((data, index) => (
          <Col className="flex-1 bg-myGray mx-3 p-2 " key={index}>
            <h5 className="text-orange">{data.name}</h5>
            <h6 className="text-white">{data.type}</h6>
          </Col>
        ))}
      </Row>
    </>
  );
}
