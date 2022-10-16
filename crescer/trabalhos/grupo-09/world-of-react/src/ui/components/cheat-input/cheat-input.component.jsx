import { useEffect, useState } from "react";
import { CHEATS_ARRAY } from "../../../constants";
import { useGlobalCharacter } from "../../../context";
import { useCharactersApi, useCheatsApi } from "../../../hooks/api";
import { Button } from "../button/button.component";
import { CustomInput } from "../custom-input/custom-input.component";
import { ToastError } from "../toast-error/toast-error.component";
import "./cheat-input.style.css";

const ATIVA_INPUT = "crescer";

export const CheatInput = () => {
  const [keypress, setKeypress] = useState("");
  const [character, setCharacter] = useGlobalCharacter();
  const [cheatInput, setCheatInput] = useState("");
  const [isVisible, setIsVisible] = useState(false);
  const [error, setError] = useState(null);
  const { cheat } = useCheatsApi();
  const { getCharacterById } = useCharactersApi();

  const getCheatApiFunction = async (cheatInput) => {
    try {
      await cheat(cheatInput);
      const characterUpdated = await getCharacterById(character.id);
      setCharacter({ ...character, ...characterUpdated });
    } catch (error) {
      const { message } = error.response.data;
      setError(message);
    }
  };

  const handleSubmitCheat = async (event) => {
    event.preventDefault();
    setError(null);
    const cheatFind = CHEATS_ARRAY.find((cheatString) =>
      cheatInput.toLowerCase().includes(cheatString.toLowerCase())
    );
    if (cheatFind) {
      await getCheatApiFunction(cheatFind);
    }
  };

  const handleChange = (event) => {
    const { value } = event.target;

    setCheatInput(value);
  };

  useEffect(() => {
    const handleKeyPress = (event) => {
      setKeypress((oldKeypress) => oldKeypress + event.key);
    };
    window.addEventListener("keypress", handleKeyPress);
    return () => {
      window.removeEventListener("keypress", handleKeyPress);
    };
  }, []);

  useEffect(() => {
    if (keypress.includes(ATIVA_INPUT)) {
      setIsVisible(true);
    }
  }, [keypress, setIsVisible]);

  const handleCloseToast = () => {
    setError(null);
  };

  const handleCloseCheat = () => {
    setIsVisible(false);
    setKeypress("");
  };

  return (
    <section
      className={`cheat-input__content ${!isVisible && "cheat-input__hide"}`}
    >
      <form onSubmit={handleSubmitCheat} className="cheat-input__form">
        <div className="cheat-input__btn-close-div">
          <button className="cheat-input__btn-close" onClick={handleCloseCheat}>
            X
          </button>
        </div>
        <CustomInput label="Cheat" name="cheat" onChange={handleChange} />
        <Button>Enviar</Button>
      </form>
      {error && (
        <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
      )}
    </section>
  );
};
