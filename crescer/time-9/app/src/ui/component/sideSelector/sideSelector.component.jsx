import React from "react";
import { Link } from "react-router-dom";
import { DRIVER_MENU, PASSENGER_MENU } from "../../../core/constants";
import { Button } from "../button/button.component";
import "./sideSelector.style.css";

export const SideSelector = () => {
  return (
    <div className="sideSelector">
      <Link to={DRIVER_MENU}>
        <Button children="Menu Dos Motoristas" />
      </Link>

      <Link to={PASSENGER_MENU}>
        <Button children="Menu Dos Passageiros" />
      </Link>
    </div>
  );
};
