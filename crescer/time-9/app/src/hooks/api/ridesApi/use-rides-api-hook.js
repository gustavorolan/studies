import { useMemo } from "react";
import {
  BASE_URL_ME_LEVA_AI,
  DRIVER_STATUS_END_RIDE,
  DRIVER_STATUS_START_RIDE,
} from "../../../core/constants";
import { useHttp } from "../_base/use-http.hook";

export function useRidesApi() {
  const httpInstance = useHttp(BASE_URL_ME_LEVA_AI);

  const getAllPassengers = async () => {
    return await httpInstance.get("/passageiros");
  };

  const getOnePassenger = async (id) => {
    return await httpInstance.get(`/passageiros/${id}`);
  };

  const getAllDrivers = async () => {
    return await httpInstance.get("/motoristas");
  };
  const getAllDriversAvailableToinit = async () => {
    const response = await httpInstance.get("/motoristas");
    return response.filter((driver) => {
      return driver.driverStatusRide === DRIVER_STATUS_START_RIDE;
    });
  };
  const getAllDriversAvailableToFinish = async () => {
    const response = await httpInstance.get("/motoristas");
    return response.filter((driver) => {
      return driver.driverStatusRide === DRIVER_STATUS_END_RIDE;
    });
  };

  const createNewRide = async (data) => {
    return await httpInstance.post("/criarCorrida", data);
  };

  const moneyPrinterPassenger = async (data) => {
    return await httpInstance.put("/imprimirDinheiro", data);
  };

  const driverDraw = async (data) => {
    return await httpInstance.put("/retirarDinheiro", data);
  };

  const driverAssessPassenger = async (data) => {
    return await httpInstance.put("/driverAvalia", data);
  };

  const passengerAssessDrive = async (data) => {
    return await httpInstance.put("/passengerAvalia", data);
  };

  const initRide = async (id) => {
    return await httpInstance.put(`/${id}/startRide`);
  };

  const finishRide = async (id) => {
    return await httpInstance.put(`/${id}/finishRide`);
  };

  const makeDriverBusy = async (id) => {
    return await httpInstance.put(`/driver/${id}/deactivate`);
  };
  const makePassengerDeactivate = async (id) => {
    return await httpInstance.put(`/passenger/${id}/deactivate`);
  };

  return useMemo(
    () => ({
      getAllPassengers,
      createNewRide,
      driverDraw,
      moneyPrinterPassenger,
      driverAssessPassenger,
      passengerAssessDrive,
      initRide,
      finishRide,
      makeDriverBusy,
      makePassengerDeactivate,
      getAllDrivers,
      getAllDriversAvailableToinit,
      getAllDriversAvailableToFinish,
      getOnePassenger,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
}
