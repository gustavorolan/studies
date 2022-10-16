import { useState, useEffect } from "react";
import { useRidesApi } from "../../../hooks/api";
import { SideSelector } from "../../component";
import { WithdrawForm } from "./sections";
import { useNavigate } from "react-router-dom";
import { ToastError } from "../../component/toastError/toastError.component";
import { TOAST_ERROR_TIME } from "../../../core/constants";
import { ShowListOfDrivers } from "../../component";

export const DriverWithdraw = () => {
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

  const handleWithdraw = async (id, amount) => {
    try {
      const requestData = { moneyToDraw: amount, driverID: id };
      const response = await ridesApi.driverDraw(requestData);
      setMessage(response.withDrawResponse);
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
            <WithdrawForm
              setSelectedDriver={setSelectedDriver}
              driver={selectedDriver}
              onWithdraw={handleWithdraw}
            />
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
