import React from "react";
import { Link } from "react-router-dom";
import { ARRAY_PASSENGER_URL } from "../../../core/constants";
import { Button } from "../../component";

export const PassengerMenu = () => {
  return (
    <div className="userSelection">
      {ARRAY_PASSENGER_URL.map(({ url, name }, index) => {
        return (
          <div key={index} className="userSelectionButton">
            <Link to={url}>
              <Button>{name}</Button>
            </Link>
          </div>
        );
      })}
    </div>
  );
};
