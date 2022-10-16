import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  SELECTED_DRIVER_URL,
  DRIVER_ASSES_URL,
} from "../../../../../core/constants";

export const DriverStatusFilter = ({
  driver,
  onSaqueSubmit,
  onStartRide,
  onFinishRide,
}) => {
  const [saque, setSaque] = useState(driver.balance);

  const [rideAtual, setRideAtual] = useState({});

  useEffect(() => {
    if (driver.personStatus === "OCUPADO") {
      const ultimaRide = driver.rides.find(
        (ride) => ride.status !== "FINALIZADA"
      );
      setRideAtual(ultimaRide);
    }
  }, [driver]);

  const handleSubmit = (event) => {
    event.preventDefault();
    onSaqueSubmit(saque);
  };

  const handleChange = (event) => {
    const { value } = event.target;
    setSaque(value);
  };

  const handleClickStart = () => {
    onStartRide(rideAtual.rideId);
  };

  const handleClickFinish = () => {
    onFinishRide(rideAtual.rideId);
  };

  switch (driver.personStatus) {
    case "LIVRE":
      return (
        <div>
          <h3>free</h3>

          <Link
            to={`${SELECTED_DRIVER_URL}/${driver.driverId}${DRIVER_ASSES_URL}`}
          >
            <button>avaliar corridas</button>
          </Link>

          <form onSubmit={handleSubmit}>
            <label htmlFor="valor">Valor pra Sacar</label>
            <input
              type="number"
              name="valor"
              value={saque}
              onChange={handleChange}
            />

            <button>sacar</button>
          </form>
        </div>
      );

    case "OCUPADO":
      return rideAtual?.status?.length ? (
        <div>
          <h3>notfree</h3>

          <p>Viagem com {rideAtual.passenger.namePassenger}</p>

          {rideAtual.status === "SOLICITADA" ? (
            <button onClick={handleClickStart}>começar</button>
          ) : (
            <button onClick={handleClickFinish}>finalizar</button>
          )}
        </div>
      ) : (
        <div></div>
      );

    case "INATIVO":
      return (
        <div>
          <h3>rip</h3>
        </div>
      );

    default:
      return <div></div>;
  }
};
