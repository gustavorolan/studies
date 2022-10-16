import { AcademicsData, BottleIndicator } from "../../components";
import React from "react";
import { ARRAY_HOUSES_TO_RAFFLE } from "../../../constants/constants/constants";
import { validationTwo } from "../../../assets/auxFunctions/auxFunctions";
import { useGlobalUser } from "../../../contexts";
import "../enrollmentsHogwarts.style.css";
import { useNavigate } from "react-router-dom";
export const AcademicsDataScreen = ({ logout }) => {
  const [personalData, setPersonalData] = useGlobalUser();
  const navigate = useNavigate();

  const handleChange = (event) => {
    if (event.className === "buttonRaffle" && personalData?.schoolYear > 1) {
      const randomHouse = ARRAY_HOUSES_TO_RAFFLE[parseInt(ARRAY_HOUSES_TO_RAFFLE.length * Math.random())];
      const newPersonalData = { ...personalData, house: randomHouse };
      setPersonalData(newPersonalData);
    } else {
      setPersonalData({ ...personalData, [event.name]: event.value });
    }
  };

  const handleNextTwo = () => {
    const validationObject = validationTwo(personalData);
    const validationArrayBooleans = Object.values(validationObject);
    const validation = validationArrayBooleans.every((boolean) => boolean === true);
    if (validation) {
      navigate("/MatterChoicer");
    } else {
      window.alert("Você não selecionou todas as opções necessárias");
    }
  };

  const handleClickBack = () => {
    navigate("/PersonalData");
  };

  return (
    <>
      <BottleIndicator screenName="AcademicsData" />
      <AcademicsData handleChange={handleChange} handleNextTwo={handleNextTwo} buttonSelected={personalData.schoolYear} buttonSelectedTwo={personalData.house} handleClickBack={handleClickBack} />
      <button className="logoutButton" onClick={() => logout()}>
        logout
      </button>
    </>
  );
};
