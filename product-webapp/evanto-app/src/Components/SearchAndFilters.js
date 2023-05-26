import React from "react";
import { Row, Col } from "reactstrap";
import "../CSS/SearchAndFilter.css";

export default function SearchAndFilters({onChangeDate, onChangeSearch}) {
  return (
    <Row>
      <Col md={3} className="align-self-end">
        <form className="d-flex flex-row flex-nowrap align-items-baseline myformsearch">
          <i className="fa-solid fa-magnifying-glass"></i>
          <input
            type="text"
            className="w-100"
            id="venue_searchbox"
            placeholder="Search..."
            onChange={(e)=>onChangeSearch(e.target.value)}
          />
        </form>
      </Col>
      <Col md={9} className="my-3 align-self-center text-end">
          <div>
            <h6 htmlFor="showDate" className="text-white">
              Show By Date
            </h6>
          </div>
          <input
            type="date"
            name="show Date"
            className="my_datepicker"
            placeholder="Date"
            style={{width:230}}
            onChange={(e)=>onChangeDate(e.target.value)}
          />
      </Col>
    </Row>
  );
}
