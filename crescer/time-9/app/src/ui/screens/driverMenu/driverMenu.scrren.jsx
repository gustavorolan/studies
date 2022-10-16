import { Button } from "../../component";
import { Link } from "react-router-dom";
import {
  DRIVER_ASSES_URL,
  DRIVER_WITHDRAW_URL,
  DEACTIVATE_DRIVER_URL,
} from "../../../core/constants";

export const DriverMenu = () => {
  return (
    <div className="userSelection">
      <div className="userSelectionButton">
        <Link to={"/"}>
          <Button>Menu</Button>
        </Link>
      </div>
      <div className="userSelectionButton">
        <Link to={"/iniciarCorrida"}>
          <Button>Iniciar Corridas</Button>
        </Link>
      </div>
      <div className="userSelectionButton">
        <Link to={"/finalizarCorrida"}>
          <Button>Finalizar Corridas</Button>
        </Link>
      </div>
      <div className="userSelectionButton">
        <Link to={DRIVER_WITHDRAW_URL}>
          <Button>Sacar</Button>
        </Link>
      </div>
      <div className="userSelectionButton">
        <Link to={DRIVER_ASSES_URL}>
          <Button>Avaliar Corridas</Button>
        </Link>
      </div>
      <div className="userSelectionButton">
        <Link to={DEACTIVATE_DRIVER_URL}>
          <Button>Inativar Motoristas</Button>
        </Link>
      </div>
    </div>
  );
};
