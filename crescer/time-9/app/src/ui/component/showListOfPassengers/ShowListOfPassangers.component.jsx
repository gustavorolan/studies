import React, { useEffect, useState } from "react";
import { timeOutNull } from "../../../core/functions";
import { useRidesApi } from "../../../hooks/api/ridesApi/use-rides-api-hook";
import "./ShowListOfPassangers.style.css";

export const ShowListOfPassangers = ({
  handleClick,
  personStatus,
  setError,
}) => {
  const { getAllPassengers } = useRidesApi();
  const [passengers, setPassengers] = useState([]);

  useEffect(() => {
    const getRideApi = async () => {
      try {
        const response = await getAllPassengers();
        const availablePassengers = response.filter((passenger) => {
          return personStatus.includes(passenger.personStatus);
        });
        setPassengers(availablePassengers);
      } catch (e) {
        setError(e?.response?.data?.message);
        timeOutNull(setError);
      }
    };
    getRideApi();
  }, [getAllPassengers, personStatus, setError]);

  return (
    <div className="ShowListOfPassangersContainer">
      <div className="passengersContainer">
        {passengers.map((passenger) => (
          <div className="nameButton" key={passenger.passengerId}>
            <button onClick={() => handleClick(passenger)}>
              <p>{passenger.namePassenger}</p>
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};
