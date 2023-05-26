import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Ratings from "./Ratings";


export default function MovieCard({
  eventPoster,
  name,
  eventRating,
  releasedLanguages,
  eventId,
}) {
  const navigate = useNavigate();

  const [userType, setuserType] = useState("")

  const imgUrl = "data:image;base64," + eventPoster;

  useEffect(() => {
    let logindetails = sessionStorage.getItem("logindetails")
    logindetails = JSON.parse(logindetails);
    let { userType } = logindetails

    setuserType(userType)
  }, [])

  const nxtPage = () => {

    console.log('userType', userType)

    if (userType === "Organizer") {
      navigate(`/CreateEvent/${eventId}`);

    } else {
      navigate(`/venue/${eventId}`);
    }

  };

  return (
    <div
      className="card m-2 cardHover text-decoration-none"
      role={"button"}
      onClick={nxtPage}
      // style={{ width: 220 }}
      style={{ width: 200, minHeight: 350 }}

    >
      <img
        style={{ height: '250px', objectFit: "cover" }}

        src={imgUrl}
        className="card-img-top"
        alt="Poster"
      />
      <div className="p-2">
        <div className="card-text text-dark" style={{ fontSize: 17 }}>
          {name}
        </div>
        <div className="card-text text-dark" style={{ fontSize: 13 }}>
          {releasedLanguages.join(", ")}
        </div>
        <div className="card-text text-dark" style={{ fontSize: 12 }}>
          <Ratings value={Number(eventRating)} total={5} />
        </div>
      </div>
    </div>
  );
}
