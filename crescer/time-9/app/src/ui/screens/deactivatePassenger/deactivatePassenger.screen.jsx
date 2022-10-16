import React, { useState } from "react";
import { PERSON_STATUS_AVAILABLE } from "../../../core/constants";
import {
  DeactivatePassengerComponent,
  ShowListOfPassangers,
  SideSelector,
  ToastSuccess,
} from "../../component";
import { ToastError } from "../../component/toastError/toastError.component";

export const DeactivatePassenger = () => {
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [passenger, setPassenger] = useState({});
  const handleClick = (passenger) => {
    setPassenger(passenger);
  };

  return (
    <>
      <div className="mainScreen">
        <div className="centralScreen">
          {!passenger?.passengerId && (
            <ShowListOfPassangers
              setError={setError}
              personStatus={[PERSON_STATUS_AVAILABLE]}
              handleClick={handleClick}
              setSuccess={setSuccess}
            />
          )}
          {!!passenger?.passengerId && (
            <DeactivatePassengerComponent
              setPassenger={setPassenger}
              passenger={passenger}
              setError={setError}
              setSuccess={setSuccess}
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
