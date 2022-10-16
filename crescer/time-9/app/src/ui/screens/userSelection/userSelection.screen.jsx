import { Button } from "../../component";
import { Link } from "react-router-dom";
import { DRIVER_MENU, PASSENGER_MENU } from "../../../core/constants";
import "./userSelection.style.css";

export function UserSelection() {
  return (
    <div className="userSelection">
      <div className="userSelectionButton">
        <Link to={PASSENGER_MENU}>
          <Button>Tela dos Passageiros</Button>
        </Link>
      </div>
      <div className="userSelectionButton">
        <Link to={DRIVER_MENU}>
          <Button>Tela dos Motoristas</Button>
        </Link>
      </div>
    </div>
  );
}
