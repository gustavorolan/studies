import React from "react";
import { validationThree } from "../../../assets/auxFunctions/auxFunctions";
import { useGlobalUser } from "../../../contexts";
import { BottleIndicator, MattersChoicer } from "../../components";
import "../enrollmentsHogwarts.style.css";
import { useNavigate } from "react-router-dom";

export const MatterChoicerScreen = ({ logout }) => {
  const [personalData, setPersonalData] = useGlobalUser();
  const navigate = useNavigate();
  const handleChangeClasses = (event) => {
    if (event.checked && !personalData.classes.includes(event.value)) {
      const objectToSet = {
        ...personalData,
        classes: [...personalData.classes, event.value],
      };
      setPersonalData(objectToSet);
    } else if (!event.checked) {
      const objectToSet = {
        ...personalData,
        classes: personalData.classes.filter((classe) => classe !== event.value),
      };
      setPersonalData(objectToSet);
    }
  };
  const handleNextThree = () => {
    const validationObject = validationThree(personalData);
    const validationArrayBooleans = Object.values(validationObject);
    const validation = validationArrayBooleans.every((boolean) => boolean === true);
    if (validation) {
      navigate("/AllDataOfProfile");
    } else {
      window.alert("Você não selecionou pelo menos uma matéria");
    }
  };

  const handleClickBack = () => {
    navigate("/AcademicsData");
  };
  return (
    <>
      <BottleIndicator screenName="MatterChoicer" />
      <MattersChoicer handleChange={handleChangeClasses} handleNextThree={handleNextThree} handleClickBack={handleClickBack} />
      <button className="logoutButton" onClick={() => logout()}>
        logout
      </button>
    </>
  );
};
