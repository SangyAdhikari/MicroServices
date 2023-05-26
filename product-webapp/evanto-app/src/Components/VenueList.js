import React from "react";

export default function VenueList({
  showDetails,
  theatreName,
  city,
  street,
  numberOfSeats,
  isActive
}) {
  return (
    <div
      className={isActive ? `p-3 text-center my-2 bg-myOrange text-white` :`p-3 text-center my-2 bg-myGray text-white`}
      role={"button"}
      onClick={() => showDetails()}
    >
      <h4>{theatreName}</h4>
      <h6>{street}, {city}</h6>
      <h6>Seat count: {numberOfSeats}</h6>
    </div>
  );
}
