import { useParams, useNavigate, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDriverApi, useRidesApi } from "../../../hooks/api";
import { DriverStatusFilter } from "./sections";
import { DRIVER_SELECTION_URL } from "../../../core/constants";

export const SelectedDriver = () => {
  const [selectedDriver, setSelectedDriver] = useState({});
  const { driverId } = useParams();
  const driverApi = useDriverApi();
  const { initRide, finishRide } = useRidesApi();
  const navigate = useNavigate();

  useEffect(() => {
    const getSpecificDriver = async () => {
      const getSpecificDriverResponse = await driverApi.getSpecificDriver(
        driverId
      );
      setSelectedDriver(getSpecificDriverResponse);
    };

    getSpecificDriver();
  }, [driverApi,driverId]);

  const handleSaque = async (valor) => {
    await driverApi.putDriverWithdraw(driverId, valor);
    navigate(0);
  };

  const handleStartRide = async (rideId) => {
    await initRide(rideId);
    navigate(0);
  };

  const handleFinishRide = async (rideId) => {
    await finishRide(rideId);
    navigate(0);
  };

  return selectedDriver?.nameDriver?.length ? (
    <div>
      <h2>{selectedDriver.nameDriver}</h2>
      <DriverStatusFilter
        driver={selectedDriver}
        onSaqueSubmit={handleSaque}
        onStartRide={handleStartRide}
        onFinishRide={handleFinishRide}
      />
      <Link to={DRIVER_SELECTION_URL}>
        <button>Voltar</button>
      </Link>
    </div>
  ) : (
    <p>carregando</p>
  );
};
