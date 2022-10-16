import "./home.style.css";

import { Button, Logout } from "../../components";
import { useNavigate } from "react-router-dom";
import { useGlobalCharacter } from "../../../context";
import { useEffect, useState } from "react";

export const Home = () => {
  const navigate = useNavigate();
  const [globalCharacter, setGlobalCharacter] = useGlobalCharacter();
  const [characterIsSelected, setCharacterIsSelected] = useState(false);

  useEffect(() => {
    const verifyGlobalCharacter = Object.keys(globalCharacter).length !== 0;
    if (verifyGlobalCharacter) {
      setCharacterIsSelected(true);
    }
  }, [globalCharacter]);

  const handleChoose = (event) => {
    const { value } = event.target;
    navigate(value);
  };

  const handleUnselectedCharacter = () => {
    setGlobalCharacter({});
    setCharacterIsSelected(false);
  };

  return (
    <div className="home__content">
      <div className="home__title">
        <h1>Menu</h1>
      </div>
      {!characterIsSelected && (
        <div className="home__menu">
          <Button value={"select-character"} onClick={handleChoose}>
            Escolher Personagem
          </Button>
          <Button value={"create-character"} onClick={handleChoose}>
            Criar Personagem
          </Button>
        </div>
      )}
      {characterIsSelected && (
        <div className="home__menu-character">
          <Button value={"battle"} onClick={handleChoose}>
            Batalhar
          </Button>
          <Button value={"quests"} onClick={handleChoose}>
            Missões
          </Button>
          <Button value={"store"} onClick={handleChoose}>
            Loja
          </Button>
          <Button onClick={handleUnselectedCharacter}>Voltar para menu</Button>
        </div>
      )}
    </div>
  );
};
