import "./academicsData.style.css";

import React from "react";

export const AcademicsData = ({ handleChange, handleNextTwo, buttonSelected, buttonSelectedTwo, handleClickBack }) => {
  const onChange = (Event) => {
    handleChange(Event.target);
  };

  const onClick = () => {
    handleNextTwo();
  };

  const onClickBack = () => {
    handleClickBack();
  };

  return (
    <div className="mainContainer academicsData">
      <h1>Dados Letivos</h1>
      <h3>Ano Letivo</h3>
      <div onClick={onChange} className="schoolYearContainer">
        <button className={buttonSelected === "1" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={1}>
          1
        </button>

        <button className={buttonSelected === "2" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={2}>
          2
        </button>

        <button className={buttonSelected === "3" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={3}>
          3
        </button>

        <button className={buttonSelected === "4" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={4}>
          4
        </button>

        <button className={buttonSelected === "5" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={5}>
          5
        </button>

        <button className={buttonSelected === "6" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={6}>
          6
        </button>

        <button className={buttonSelected === "7" ? "buttonSelected" : "schoolYear"} name="schoolYear" value={7}>
          7
        </button>
      </div>

      <div onClick={onChange} className="houseContainer" name="house" onChange={onChange}>
        <div>
          <button className={`houseButton ${buttonSelectedTwo === "grifinoria" ? "houseButtonSelected" : ""} orange`} name="house" value="grifinoria">
            grifinoria
          </button>

          <button className={`houseButton ${buttonSelectedTwo === "corvinal" ? "houseButtonSelected" : ""} blue`} name="house" value="corvinal">
            corvinal
          </button>
        </div>

        <div>
          <button className={`houseButton ${buttonSelectedTwo === "lufa-lufa" ? "houseButtonSelected" : ""} yellow`} name="house" value="lufa-lufa">
            lufa-lufa
          </button>

          <button className={`houseButton ${buttonSelectedTwo === "sonserina" ? "houseButtonSelected" : ""} green`} name="house" value="sonserina">
            sonserina
          </button>
        </div>
      </div>

      <button onClick={onClick}>Proxima</button>

      <button className="backButton" onClick={onClickBack}>
        voltar
      </button>
    </div>
  );
};
