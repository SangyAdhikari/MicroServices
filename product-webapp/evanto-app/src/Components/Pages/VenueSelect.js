import React from "react";
import { useParams } from "react-router-dom";

export default function VenueSelect() {
  let { movie_name } = useParams();
  console.log(useParams());
  return (
    <div>
      venueSelect
      <h3>Movie: {movie_name}</h3>
    </div>
  );
}
