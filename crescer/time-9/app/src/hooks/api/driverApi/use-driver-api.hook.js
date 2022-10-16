import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import {
  BASE_URL_ME_LEVA_AI,
  GET_ALL_DRIVERS_API,
  GET_SPECIFIC_DRIVER_API,
  DRIVER_WITHDRAW_API,
  DRIVER_ASSES_API,
} from "../../../core/constants";

export function useDriverApi() {
  const httpInstance = useHttp(BASE_URL_ME_LEVA_AI);

  const getDrivers = async () => {
    const response = await httpInstance.get(GET_ALL_DRIVERS_API());
    return response;
  };

  const getSpecificDriver = async (id) => {
    const response = await httpInstance.get(GET_SPECIFIC_DRIVER_API(id));
    return response;
  };

  const putDriverWithdraw = async (id, valor) => {
    const response = await httpInstance.put(DRIVER_WITHDRAW_API(), {
      moneyToDraw: valor,
      driverID: id,
    });
    return response;
  };

  const putDriverAsses = async (rideId, score) => {
    const response = await httpInstance.put(DRIVER_ASSES_API(), {
      score: score,
      rideId: rideId,
    });
    return response;
  };

  return useMemo(
    () => ({
      getDrivers,
      getSpecificDriver,
      putDriverWithdraw,
      putDriverAsses,
      
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
}
