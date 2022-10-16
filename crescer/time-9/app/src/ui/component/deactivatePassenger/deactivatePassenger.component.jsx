import React from "react";
import { timeOutNull } from "../../../core/functions";
import { useRidesApi } from "../../../hooks/api";
import { Button } from "../button/button.component";
import "./deactivatePassenger.style.css";

export const DeactivatePassengerComponent = ({
  setPassenger,
  passenger,
  setError,
  setSuccess,
}) => {
  const { makePassengerDeactivate, getOnePassenger } = useRidesApi();
  const handleClick = async () => {
    try {
      const response = await makePassengerDeactivate(passenger.passengerId);
      const passangerUpdate = await getOnePassenger(passenger.passengerId);
      setPassenger(passangerUpdate);
      setPassenger({});
      setSuccess(response.message);
      timeOutNull(setSuccess);
    } catch (e) {
      setError(e?.response?.data?.message);
      timeOutNull(setError);
    }
  };

  return (
    <div className="deactivatePessenger">
      <h1>{passenger.passengerId + ". " + passenger.namePassenger}</h1>

      <Button onClick={handleClick} children="Desativar Passageiro" />
    </div>
  );
};
