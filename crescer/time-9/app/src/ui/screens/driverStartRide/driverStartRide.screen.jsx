import React, { useState } from "react";
import { useRidesApi } from "../../../hooks/api/ridesApi/use-rides-api-hook";
import { DriverRide, SideSelector, ToastSuccess } from "../../component";
import { ToastError } from "../../component/toastError/toastError.component";

export const DriverStartRide = () => {
  const { getAllDriversAvailableToinit, initRide } = useRidesApi();
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  return (
    <>
      <DriverRide
        setSucess={setSuccess}
        setError={setError}
        callbackGetDrivers={getAllDriversAvailableToinit}
        callbackWhatToDo={initRide}
        buttonFrase="Iniciar Corrida"
      />
      {error && <ToastError children={error} />}
      {success && (
        <ToastSuccess
          children={`Tempo Estimado em Segundos: ${success?.estimatedArrivalTime} | Preço Estimado: ${success?.estimatedPrice}`}
        />
      )}
      <SideSelector />
    </>
  );
};
