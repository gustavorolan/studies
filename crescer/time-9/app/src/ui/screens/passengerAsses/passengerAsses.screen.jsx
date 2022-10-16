import React from "react";
import { useState } from "react";
import { PERSON_STATUS_AVAILABLE } from "../../../core/constants";
import { useRidesApi } from "../../../hooks/api/ridesApi/use-rides-api-hook";
import {
  ShowListOfPassangers,
  SideSelector,
  ToastSuccess,
} from "../../component";
import { RidesShowList } from "../../component/ridesShowList/ridesShowList.component";
import { ToastError } from "../../component/toastError/toastError.component";

export const PassengerAsses = () => {
  const [passenger, setPassenger] = useState();
  const { getOnePassenger } = useRidesApi();
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const handleClick = async ({ passengerId }) => {
    const response = await getOnePassenger(passengerId);
    setPassenger(response);
  };

  return (
    <>
      <div className="mainScreen">
        <div className="centralScreen">
          {!passenger && (
            <ShowListOfPassangers
              setSuccess={setSuccess}
              personStatus={[PERSON_STATUS_AVAILABLE]}
              handleClick={handleClick}
            />
          )}
          {!!passenger && (
            <RidesShowList
              setSuccess={setSuccess}
              passenger={passenger}
              getOnePassenger={getOnePassenger}
              setPassenger={setPassenger}
              setError={setError}
            />
          )}
        </div>
      </div>
      {error && <ToastError children={error} />}
      {success && <ToastSuccess children={success?.response} />}
      <SideSelector />
    </>
  );
};
