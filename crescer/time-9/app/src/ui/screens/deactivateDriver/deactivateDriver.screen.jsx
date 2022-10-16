import { useState, useEffect } from "react";
import { useRidesApi } from "../../../hooks/api";
import { useNavigate } from "react-router-dom";
import { ToastError } from "../../component/toastError/toastError.component";
import { TOAST_ERROR_TIME } from "../../../core/constants";
import { Button, ShowListOfDrivers } from "../../component";
import { SideSelector } from "../../component";
import "./deactivateDriver.style.css";

export const DeactivateDriver = () => {
  const [driversAvaliable, setDriversAvaliable] = useState([]);
  const ridesApi = useRidesApi();
  const [selectedDriver, setSelectedDriver] = useState({});
  const navigate = useNavigate();
  const [message, setMessage] = useState("");

  useEffect(() => {
    async function getAllDrivers() {
      const getAllDriversResponse = await ridesApi.getAllDrivers();
      const filteredDrivers = getAllDriversResponse.filter(
        (driver) => driver.personStatus === "LIVRE"
      );

      setDriversAvaliable(filteredDrivers);
    }

    getAllDrivers();
  }, [ridesApi]);

  const handleSelectDriver = (driver) => {
    setSelectedDriver(driver);
  };

  const handleDesativarDriver = async () => {
    try {
      const response = await ridesApi.makeDriverBusy(selectedDriver.driverId);
      setMessage(response.message);
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
          {driversAvaliable?.length ? (
            <ShowListOfDrivers
              drivers={driversAvaliable}
              handleClick={handleSelectDriver}
            />
          ) : (
            ""
          )}
          {selectedDriver?.nameDriver ? (
            <div className="userSelectionButtonToDeactivate">
              <Button onClick={() => handleDesativarDriver()}>Desativar</Button>
            </div>
          ) : (
            ""
          )}
          
        </div>
      </div>
      {message && <ToastError children={message} />}
      <SideSelector />
    </>
  );
};
