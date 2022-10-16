import { useState, useEffect } from "react";
import { useRidesApi } from "../../../hooks/api";
import { SideSelector } from "../../component";
import { useNavigate } from "react-router-dom";
import { ShowListOfDrivers } from "../../component";
import { DriverAssesForm, DriverRidesToAsses } from "./sections";
import { ToastError } from "../../component/toastError/toastError.component";
import { TOAST_ERROR_TIME } from "../../../core/constants";

export const DriverAsses = () => {
  const [driversAvaliable, setDriversAvaliable] = useState([]);
  const ridesApi = useRidesApi();
  const [selectedDriver, setSelectedDriver] = useState({});
  const [selectedRide, setSelectedRide] = useState({});
  const navigate = useNavigate();
  const [message, setMessage] = useState("");

  useEffect(() => {
    async function getAllDrivers() {
      const getAllDriversResponse = await ridesApi.getAllDrivers();
      const filteredLivreDrivers = getAllDriversResponse.filter(
        (driver) => driver.personStatus === "LIVRE"
      );
      const filteredDriversWithPendingAsses = filteredLivreDrivers
        .filter((driver) => driver.rides.length)
        .filter((driver) =>
          driver.rides.find(
            (ride) => ride.status === "FINALIZADA" && !ride.passengerScore
          )
        );

      setDriversAvaliable(filteredDriversWithPendingAsses);
    }

    getAllDrivers();
  }, [ridesApi]);

  const handleSelectDriver = (driver) => {
    setSelectedDriver(driver);
  };

  const handleSelectRide = (ride) => {
    setSelectedRide(ride);
  };

  const handleAsses = async (id, score) => {
    try {
      const requestData = { rideId: id, score: score };
      const response = await ridesApi.driverAssessPassenger(requestData);
      setMessage(response.response);
    } catch (error) {
      setMessage(error?.response?.data?.message);
    } finally {
      setTimeout(() => {
        navigate(0);
      }, TOAST_ERROR_TIME);
    }
  };

  return (
    <>
      <div className="mainScreen">
        <div className="centralScreen">
          {driversAvaliable?.length && !selectedDriver?.driverId ? (
            <ShowListOfDrivers
              drivers={driversAvaliable}
              handleClick={handleSelectDriver}
            />
          ) : (
            ""
          )}
          {selectedDriver?.driverId ? (
            <DriverRidesToAsses
              rides={selectedDriver.rides}
              handleClick={handleSelectRide}
            />
          ) : (
            ""
          )}
          {selectedRide?.rideId ? (
            <DriverAssesForm
              setSelectedRide={setSelectedRide}
              ride={selectedRide}
              onAsses={handleAsses}
            />
          ) : (
            ""
          )}
        </div>
      </div>
      <SideSelector />
      {message && <ToastError children={message} />}
    </>
  );
};
