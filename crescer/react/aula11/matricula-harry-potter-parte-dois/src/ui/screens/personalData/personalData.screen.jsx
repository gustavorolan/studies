import React from "react";
import { useGlobalUser } from "../../../contexts";
import { BottleIndicator, PersonalData } from "../../components";
import { validationOne } from "../../../assets/auxFunctions/auxFunctions";
import "../enrollmentsHogwarts.style.css";
import { useNavigate } from "react-router-dom";

export const PersonalDataScreen = ({ logout }) => {
  const [personalData, setPersonalData] = useGlobalUser();
  const navigate = useNavigate();

  const handleChange = (event) => {
    setPersonalData({ ...personalData, [event.name]: event.value });
  };
  const handleNextOne = () => {
    const validationObject = validationOne(personalData);
    const validationArrayBooleans = Object.values(validationObject);
    const validation = validationArrayBooleans.every((boolean) => boolean === true);
    if (validation) {
      navigate("/AcademicsData");
    } else {
      window.alert("Você inseriu algum dado Incorretamente");
    }
  };

  return (
    <>
      <BottleIndicator screenName="PersonalData" />
      <PersonalData handleChange={handleChange} handleNextOne={handleNextOne} />
      <button className="logoutButton" onClick={() => logout()}>
        logout
      </button>
    </>
  );
};
