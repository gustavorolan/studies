import "./create-character.style.css";

import { useCharactersApi, useRacesApi } from "../../../hooks/api";
import { useEffect, useState } from "react";
import {
  Button,
  CustomInput,
  ListRaces,
  Loader,
  RadioButton,
  ToastError,
} from "../../components";
import { useNavigate } from "react-router-dom";

const INITIAL_CHARACTER = {
  name: "",
  raceId: "",
  faction: "",
};

export const CreateCharacter = () => {
  const [character, setCharacter] = useState(INITIAL_CHARACTER);
  const [error, setError] = useState(null);
  const [races, setRaces] = useState([]);
  const charactersApi = useCharactersApi();
  const useRaces = useRacesApi();
  const navigate = useNavigate();

  useEffect(() => {
    const getRaces = async () => {
      const reqRaces = await useRaces.getRaces();
      setRaces(reqRaces);
    };

    getRaces();
  }, [useRaces]);

  const handleCharacter = (event) => {
    const { name, value } = event.target;
    setCharacter({ ...character, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const convertCharacter = {
      ...character,
      raceId: parseInt(character.raceId),
    };
    try {
      await charactersApi.postCreateCharacter(convertCharacter);
      navigate("/select-character");
    } catch (error) {
      setError(error.response?.data?.message);
    }
  };

  const findSelectedRace = (raceId) => {
    const findRace = races.find((race) => race.id === parseInt(raceId));
    return findRace;
  };

  const handleSelectAnotherRace = () => {
    setCharacter((oldCharacter) => {
      return {
        ...oldCharacter,
        raceId: "",
      };
    });
  };

  const selectedRace = findSelectedRace(character.raceId);

  const checkingRaces = () => {
    if (races.length > 0) {
      return (
        <ListRaces races={races} onChange={handleCharacter} nameProp="raceId" />
      );
    }
    return (
      <div className="create-character__loader">
        <Loader />
      </div>
    );
  };

  const handleCloseToast = () => {
    setError(null);
  };

  const handleBackToMenu = () => {
    navigate("/");
  };

  return (
    <div className="create-character__content">
      <form onSubmit={handleSubmit}>
        <div className="create-character__form">
          {!character.raceId ? (
            checkingRaces()
          ) : (
            <>
              <CustomInput
                label="Nome"
                name="name"
                onChange={handleCharacter}
                value={character.name}
              />
              <RadioButton
                label="Horda"
                name="faction"
                onChange={handleCharacter}
                value="Horda"
                selectedValue={character.faction}
              />
              <RadioButton
                label="Aliança"
                name="faction"
                onChange={handleCharacter}
                value="Aliança"
                selectedValue={character.faction}
              />
              <h4>Raça selecionada: {selectedRace.name}</h4>
              <Button type="button" onClick={handleSelectAnotherRace}>
                Selecionar outra raça
              </Button>
              <Button>Criar Personagem</Button>
            </>
          )}
        </div>
      </form>
      <Button onClick={handleBackToMenu}>Voltar para menu</Button>
      {error && (
        <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
      )}
    </div>
  );
};
