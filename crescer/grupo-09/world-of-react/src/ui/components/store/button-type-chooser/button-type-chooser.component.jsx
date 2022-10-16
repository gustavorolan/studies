import "./button-type-chooser.style.css";
import { Button } from "../../button/button.component";
import { useNavigate } from "react-router-dom";

export const ButtonTypeChooser = ({ handleClick }) => {
  const navigate = useNavigate();
  const handleGoToMenu = () => {
    navigate("/");
  };

  return (
    <div
      className="button-type-chooser__list"
      onClick={(Event) => handleClick(Event.target)}
    >
      <Button onClick={handleGoToMenu} value="LISTA">
        Menu
      </Button>
      <Button value="MY_ITEMS">Meus Itens</Button>
      <Button value="LISTA">Lista de Items</Button>
      <Button value="EXPANSAO">Expansões</Button>
      <Button value="DANO">Dano</Button>
      <Button value="VIGOR">Vigor</Button>
      <Button value="VIDA">Vida</Button>
    </div>
  );
};
