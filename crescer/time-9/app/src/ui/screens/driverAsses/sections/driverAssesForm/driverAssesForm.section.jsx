import { useState } from "react";
import { Input, Button } from "../../../../component";
import "./driverAssesForm.style.css";

export const DriverAssesForm = ({ ride, onAsses, setSelectedRide }) => {
  const [scoreValue, setScoreValue] = useState(5);

  const handleSubmit = (event) => {
    event.preventDefault();
    onAsses(ride.rideId, scoreValue);
  };

  const handleChange = (event) => {
    const { value } = event.target;
    setScoreValue(value);
  };

  const handleClose = () => {
    setSelectedRide({});
  };

  return (
    <div className="driverAssesContainer">
      <div>
        <p>Identificador: {ride.rideId}</p>
        <p>
          De: {ride.startX}, {ride.startY}
        </p>
        <p>
          Ate: {ride.endX}, {ride.endX}
        </p>
      </div>

      <form onSubmit={handleSubmit}>
        <label htmlFor="score">Avaliação</label>
        <Input
          type="number"
          name="score"
          value={scoreValue}
          onChange={handleChange}
        />
        <div>
          {" "}
          <Button>Avaliar</Button>
        </div>
      </form>
      <div  className="buttonClose">
        <Button onClick={handleClose}>x</Button>
      </div>
    </div>
  );
};
