import { Routes, Route } from "react-router-dom";
import "./App.css";

import {
  CreateNewRideScreen,
  DriverStartRide,
  FinishRide,
  UserSelection,
  PassengerMenu,
  PrintMoney,
  DeactivatePassenger,
  DriverMenu,
  DriverAsses,
  DriverWithdraw,
  DeactivateDriver,
} from "./ui";
import { PassengerAsses } from "./ui/screens/passengerAsses/passengerAsses.screen";
import {
  DRIVER_ASSES_URL,
  PASSENGER_PRINTER,
  PASSENGER_MENU,
  CREATE_RIDE,
  INIT_RIDE,
  FINISH_RIDE,
  PASSENGER_ASSES,
  DEACTIVATE_PASSENGER,
  DRIVER_MENU_URL,
  DRIVER_WITHDRAW_URL,
  DEACTIVATE_DRIVER_URL,
} from "./core/constants";

function App() {
  return (
    <Routes>
      <Route path={DEACTIVATE_PASSENGER} element={<DeactivatePassenger />} />
      <Route path={PASSENGER_PRINTER} element={<PrintMoney />} />
      <Route path={PASSENGER_MENU} element={<PassengerMenu />} />
      <Route path={CREATE_RIDE} element={<CreateNewRideScreen />} />
      <Route path="/" element={<UserSelection />} />
      <Route path={INIT_RIDE} element={<DriverStartRide />} />
      <Route path={FINISH_RIDE} element={<FinishRide />} />
      <Route path={PASSENGER_ASSES} element={<PassengerAsses />} />
      <Route path={DRIVER_MENU_URL} element={<DriverMenu />} />
      <Route path={DRIVER_WITHDRAW_URL} element={<DriverWithdraw />} />
      <Route path={DRIVER_ASSES_URL} element={<DriverAsses />} />
      <Route path={DEACTIVATE_DRIVER_URL} element={<DeactivateDriver />} />
    </Routes>
  );
}

export default App;
