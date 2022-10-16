import React, { useState } from "react";
import {
  PERSON_STATUS_AVAILABLE,
  PERSON_STATUS_BUSY,
} from "../../../core/constants";
import {
  MoneyPrinter,
  ShowListOfPassangers,
  SideSelector,
  ToastSuccess,
} from "../../component";
import { ToastError } from "../../component/toastError/toastError.component";

export const PrintMoney = () => {
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [passenger, setPassenger] = useState(null);
  const handleClick = (passenger) => {
    setPassenger(passenger);
  };

  return (
    <>
      <div className="mainScreen">
        <div className="centralScreen">
          {!passenger && (
            <ShowListOfPassangers
              setSucess={setSuccess}
              setPassenger={setPassenger}
              setError={setError}
              personStatus={[PERSON_STATUS_BUSY, PERSON_STATUS_AVAILABLE]}
              handleClick={handleClick}
            />
          )}
          {!!passenger && (
            <MoneyPrinter
              setSuccess={setSuccess}
              error={error}
              setError={setError}
              setPassenger={setPassenger}
              passenger={passenger}
            />
          )}
        </div>
      </div>
      {!!error && <ToastError children={error} />}
      {!!success && <ToastSuccess children={success} />}
      <SideSelector />
    </>
  );
};
