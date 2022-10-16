import "./mattersChoicer.style.css";
import React from "react";
import classes from "../../../assets/jsonArchives/classes.json";
import { useGlobalUser } from "../../../contexts";
export const MattersChoicer = ({ handleChange, handleNextThree, handleClickBack }) => {
  const [personalData] = useGlobalUser();

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

      <h3>Escolher Matérias</h3>
      <div className="classesContainer">
        <div className="classesSmaller">
          {classes.map(({ aula, id }) => {
            return (
              <div key={id} className={personalData.classes.includes(aula) ? "selectedClass classes" : "classes"} name="classes" onChange={onChange}>
                <label htmlFor="classes">{aula}</label>
                <input defaultChecked={personalData.classes.includes(aula)} type="checkbox" name="classes" value={aula} />
              </div>
            );
          })}
        </div>
      </div>

      <button onClick={onClick}>Proximo</button>

      <button className="backButton" onClick={onClickBack}>
        voltar
      </button>
    </div>
  );
};
