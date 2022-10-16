import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { TOAST_ERROR_TIME } from "../../../core/constants";

import { Button, ShowListOfDrivers } from "..";

import { timeOutNull } from "../../../core/functions";

export const DriverRide = ({
  callbackGetDrivers,
  callbackWhatToDo,
  buttonFrase,
  setSucess,
  setError,
}) => {
  const [lastRideId, setlastRideId] = useState(null);
  const [buttonController, setButtonController] = useState(false);
  const [drivers, setDrivers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const getRideApi = async () => {
      try {
        const data = await callbackGetDrivers();
        setDrivers(data);
      } catch (e) {
        setError(e?.response?.data?.message);
        timeOutNull(setError);
      }
    };
    getRideApi();
  }, [callbackGetDrivers, setError]);

  const handleClick = async (driver) => {
    setlastRideId(driver.lastRideId);
    setButtonController(true);
  };

  const handleInitRide = async () => {
    try {
      const responseMessage = await callbackWhatToDo(lastRideId);
      const response = await callbackGetDrivers();
      setSucess(responseMessage);
      setDrivers(response);
    } catch (error) {
      setError(error);
    } finally {
      setTimeout(() => {
        setButtonController(false);
        navigate(0);
      }, TOAST_ERROR_TIME);
    }
  };
  return (
    <div className="mainScreen">
      <div className="centralScreen">
        <ShowListOfDrivers handleClick={handleClick} drivers={drivers} />
        <div>
          {!!buttonController && (
            <Button children={buttonFrase} onClick={handleInitRide} />
          )}
        </div>
      </div>
    </div>
  );
};
