import React from "react";
import { useState } from "react";
import { SCORE } from "../../../core/constants";
import { timeOutNull } from "../../../core/functions";
import { useRidesApi } from "../../../hooks/api/ridesApi/use-rides-api-hook";
import { Button } from "../button/button.component";
import { Input } from "../Input/input.component";
import "./ridesShowList.style.css";

export const RidesShowList = ({
  passenger,
  getOnePassenger,
  setPassenger,
  setSuccess,
  setError,
}) => {
  const { passengerAssessDrive } = useRidesApi();
  const [score, setScore] = useState(null);

  const { rides } = passenger;
  const ridesFiltered = rides.filter((ride) => ride.driverScore === null);

  const handleChange = ({ value, name }) => {
    if (name === SCORE) {
      setScore(value);
    }
  };
  const handleClickAssess = async (id) => {
    try {
      const responseMessage = await passengerAssessDrive({
        rideId: id,
        score: score,
      });
      const response = await getOnePassenger(passenger.passengerId);
      setSuccess(responseMessage);
      setPassenger(response);
      timeOutNull(setSuccess);
    } catch (e) {
      setError(e?.response?.data?.message);
      timeOutNull(setError);
    }
  };

  return (
    <div className="ridesContainer">
      {ridesFiltered.map((ride) => {
        return (
          <div
            onChange={(Event) => handleChange(Event.target)}
            className="avaliationContainer"
            key={passenger.passengerId}
          >
            <p>
              Corrida {ride.rideId} com o motorista {ride.driver.nameDriver}
            </p>
            <div>
              <Input name={SCORE} />
              <Button onClick={() => handleClickAssess(ride.rideId)}>
                Avaliar
              </Button>
            </div>
          </div>
        );
      })}
    </div>
  );
};
