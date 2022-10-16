import React, { useState } from "react";
import { timeOutNull } from "../../../core/functions";
import { useRidesApi } from "../../../hooks/api/ridesApi/use-rides-api-hook";
import { Button } from "../button/button.component";
import { Input } from "../Input/input.component";
import "./createRide.style.css";

export const CreateRide = ({
  passengerId,
  setError,
  setPassengerId,
  setSuccess,
}) => {
  const [createRideInput, setCreateRideInput] = useState({ passengerId });
  const { createNewRide } = useRidesApi();
  const handleChange = ({ name, value }) => {
    setCreateRideInput({ ...createRideInput, [name]: value });
  };

  const handleCreateRide = async () => {
    try {
      const response = await createNewRide(createRideInput);
      setSuccess(
        `Voce solicitou uma corrda com o motorista ${response.driverName} carro ${response.vehicleName}`
      );
      timeOutNull(setSuccess);
      setPassengerId(null);
    } catch (e) {
      setError(e?.response?.data?.message);
      timeOutNull(setError);
    }
  };

  return (
    <>
      <div
        onChange={(Event) => handleChange(Event.target)}
        className="coordinatesContainer"
      >
        <label htmlFor="Coordenadas Iniciais">Coordenadas Iniciais</label>
        <div className="coordinatesStartEnd">
          <Input type="text" name="startX" placeholder="Inicio X" />
          <Input type="text" name="startY" placeholder="Inicio Y" />
        </div>
        <label htmlFor="Coordenadas Iniciais">Coordenadas Finais</label>
        <div className="coordinatesStartEnd">
          <Input type="text" name="endX" placeholder="Final X" />
          <Input type="text" name="endY" placeholder="Final Y" />
        </div>
      </div>
      <div className="buttonCriarPersonagem">
        <Button onClick={handleCreateRide} children="Criar Corrida" />
      </div>
    </>
  );
};
