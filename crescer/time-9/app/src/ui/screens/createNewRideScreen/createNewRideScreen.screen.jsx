import React, { useState } from "react";
import { PERSON_STATUS_AVAILABLE } from "../../../core/constants";
import {
  CreateRide,
  ShowListOfPassangers,
  SideSelector,
  ToastSuccess,
} from "../../component";
import { ToastError } from "../../component/toastError/toastError.component";

export const CreateNewRideScreen = () => {
  const [passengerId, setPassengerId] = useState(null);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const handleClick = (passenger) => {
    setPassengerId(passenger.passengerId);
  };

  return (
    <>
      <div className="mainScreen">
        <div className="centralScreen">
          {!passengerId && (
            <ShowListOfPassangers
              setSuccess={setSuccess}
              setError={setError}
              personStatus={[PERSON_STATUS_AVAILABLE]}
              handleClick={handleClick}
            />
          )}
          {!!passengerId && (
            <CreateRide
              setSuccess={setSuccess}
              setPassengerId={setPassengerId}
              setError={setError}
              passengerId={passengerId}
            />
          )}
        </div>
      </div>
      {error && <ToastError children={error} />}
      {success && <ToastSuccess children={success} />}
      <SideSelector />
    </>
  );
};
