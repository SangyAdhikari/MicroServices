import React from "react";
import { BsStarHalf, BsStarFill, BsStar } from "react-icons/bs";
import _ from "lodash";

export default function Ratings({ value, total }) {
  let isFloat = Number(value) === value && value % 1 !== 0;
  let fullStar = Math.floor(value);
  let halfStar = isFloat ? 1 : 0;
  let remaining = total - Math.floor(value) - halfStar;

  return (
    <>
      <div>
        {_.times(fullStar, (i) => (
          <BsStarFill key={i} style={{ color: "#ffa500" }} className="mx-1" />
        ))}
        {halfStar === 1 ? (
          <BsStarHalf style={{ color: "#ffa500" }} className="mx-1" />
        ) : null}
        {_.times(remaining, (i) => (
          <BsStar key={i} style={{ color: "#ffa500" }} className="mx-1" />
        ))}
      </div>
    </>
  );
}
