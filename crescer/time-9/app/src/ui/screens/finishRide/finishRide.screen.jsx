import React, { useState } from "react";
import { useRidesApi } from "../../../hooks/api/ridesApi/use-rides-api-hook";
import { DriverRide, SideSelector, ToastSuccess } from "../../component";
import { ToastError } from "../../component/toastError/toastError.component";

export const FinishRide = () => {
  const [error, setError] = useState(null);
  const { getAllDriversAvailableToFinish, finishRide } = useRidesApi();
  const [success, setSuccess] = useState(null);

  return (
    <>
      <DriverRide
        setSucess={setSuccess}
        setError={setError}
        callbackGetDrivers={getAllDriversAvailableToFinish}
        callbackWhatToDo={finishRide}
        buttonFrase="Finalizar Corrida"
      />
      {error && <ToastError children={error} />}
      {success && (
        <ToastSuccess
          children={`Preço: ${success?.totalPrice} | Tempo em segundos: ${success?.totalTimeInSeconds}`}
        />
      )}
      <SideSelector />
    </>
  );
};
