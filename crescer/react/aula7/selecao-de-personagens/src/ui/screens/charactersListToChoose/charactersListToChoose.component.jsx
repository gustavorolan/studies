import React from "react";
import { CharacterToChoose } from "../../components/characterToChoose/characterToChoose.component";
import "./charactersListToChoose.style.css";
export const CharactersListToChoose = (props) => {
  const {
    handleSelection,
    characters,
    characterOne,
    characterTwo,
    lastChosen,
  } = props;

  return (
    <div>
      <img
        className="logoMortalKombat"
        src="https://vignette.wikia.nocookie.net/logopedia/images/8/89/Mk2_logo.png"
        alt=""
      />
      <h2>Choose Your Fighter</h2>
      <div className="listContainer">
        {characters.map(({ id, nome, imagemListagem, imagemDetalhe }) => {
          return (
            <CharacterToChoose
              key={id}
              id={id}
              name={nome}
              listingImage={imagemListagem}
              detailImage={imagemDetalhe}
              handleSelection={handleSelection}
              characterOne={characterOne}
              characterTwo={characterTwo}
              lastChosen={lastChosen}
            />
          );
        })}
      </div>
    </div>
  );
};
