import "./mattersChoicer.style.css";
import React from "react";
import classes from "../../../assets/jsonArchives/classes.json";
export const MattersChoicer = ({ handleChange, handleNextThree, handleClickBack }) => {
  const onChange = (Event) => {
    handleChange(Event.target);
  };

  const onClick = () => {
    handleNextThree();
  };

  const onClickBack = () => {
    handleClickBack();
  };

  return (
    <div className="mattersChoiceContainer">
      <h1>Aulas</h1>

      <h3>Escolher Maatérias</h3>

      <div className="classesContainer">
        {classes.map(({ aula, id }) => {
          return (
            <div className={`classes`} name="classes" onChange={onChange}>
              <label htmlFor="classes">{aula}</label>
              <input type="checkbox" name="classes" value={aula} />
            </div>
          );
        })}
      </div>

      <button onClick={onClick}>Proximo</button>

      <button className="backButton" onClick={onClickBack}>
        voltar
      </button>
    </div>
  );
};
