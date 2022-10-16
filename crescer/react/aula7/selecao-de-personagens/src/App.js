import React from "react";
import "./App.css";
import { CharactersListToChoose } from "./ui/screens";
import characters from "./assets/json/characters.json";

function App() {
  const [characterOne, setCaracterOne] = React.useState({});
  const [characterTwo, setCaracterTwo] = React.useState({});
  const [lastChosen, setlastChosen] = React.useState(true);
  const [animationControl, setAnimatioControl] = React.useState(true);
  const handleSelection = (id) => {
    const newArrayCharacters = characters.filter(
      (character) => character !== characterOne && character !== characterTwo
    );
    const chosenCharacter = {
      ...newArrayCharacters.find((character) => character.id === id),
    };

    if (lastChosen && !!chosenCharacter) {
      setCaracterOne(chosenCharacter);
      setlastChosen(false);
    } else if (!lastChosen && !!chosenCharacter) {
      setCaracterTwo(chosenCharacter);
      setlastChosen(true);
    }
    if (!!chosenCharacter) {
      setAnimatioControl(true);
      setTimeout(() => {
        setAnimatioControl(false);
      }, 1000);
    }
  };

  const handleRemove = (characterToRemove) => {
    if (characterToRemove.id === characterOne.id) {
      setCaracterOne({});
      setlastChosen(true);
    } else {
      setCaracterTwo({});
      setlastChosen(false);
    }
  };

  return (
    <div className="App">
      <div
        className={`characterChosen ${
          animationControl && !lastChosen ? "characterChosenAnimationOne" : ""
        }`}
      >
        <h2>{characterOne.nome}</h2>
        {!!characterOne.nome && <img src={characterOne.imagemDetalhe} alt="" />}
        {!!characterOne.nome && (
          <button onClick={() => handleRemove(characterOne)}>
            <p>Remove Fighter</p>
          </button>
        )}
      </div>

      <CharactersListToChoose
        handleSelection={handleSelection}
        characters={characters}
        characterOne={characterOne}
        characterTwo={characterTwo}
        lastChosen={lastChosen}
      />
      <div
        className={`characterChosen characterChosenMirror ${
          animationControl && lastChosen && !!characterTwo.nome
            ? "characterChosenAnimationTwo"
            : ""
        }`}
      >
        <h2>{characterTwo.nome}</h2>
        {!!characterTwo.nome && <img src={characterTwo.imagemDetalhe} alt="" />}
        {!!characterTwo.nome && (
          <button onClick={() => handleRemove(characterTwo)}>
            <p>Remove Fighter</p>
          </button>
        )}
      </div>
    </div>
  );
}

export default App;
