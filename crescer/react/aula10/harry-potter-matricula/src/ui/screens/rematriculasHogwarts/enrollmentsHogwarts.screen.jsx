import React from "react";
import { validationOne, validationThree, validationTwo } from "../../../assets/auxFunctions/auxFunctions";
import { AcademicsData, AllDataOfProfile, MattersChoicer, PersonalData } from "../../components";
import "./enrollmentsHogwarts.style.css";
export const EnrollmentsHogwarts = () => {
  const pagesArray = ["PersonalData", "AcademicsData", "MattersChoicer", "AllDataOfProfile"];

  const [personalData, setPersonalData] = React.useState({ classes: [] });
  const [pagCounter, setPagCounter] = React.useState(0);
  const handleChange = (event) => {
    setPersonalData({ ...personalData, [event.name]: event.value });
  };

  const handleChangeClasses = (event) => {
    if (event.checked) {
      setPersonalData({
        ...personalData,
        classes: [...personalData.classes, event.value],
      });
    } else {
      setPersonalData({
        ...personalData,
        classes: personalData.classes.filter((classe) => classe !== event.value),
      });
    }
  };

  const handleNextOne = () => {
    const validationObject = validationOne(personalData);
    const validationArrayBooleans = Object.values(validationObject);
    const validation = validationArrayBooleans.every((boolean) => boolean === true);
    if (validation) {
      setPagCounter(pagCounter + 1);
    } else {
      window.alert("Você inseriu algum dado Incorretamente");
    }
  };

  const handleNextTwo = () => {
    const validationObject = validationTwo(personalData);
    const validationArrayBooleans = Object.values(validationObject);
    const validation = validationArrayBooleans.every((boolean) => boolean === true);
    if (validation) {
      setPagCounter(pagCounter + 1);
    } else {
      window.alert("Você não selecionou todas as opções necessárias");
    }
  };

  const handleNextThree = () => {
    const validationObject = validationThree(personalData);
    const validationArrayBooleans = Object.values(validationObject);
    const validation = validationArrayBooleans.every((boolean) => boolean === true);
    if (validation) {
      setPagCounter(pagCounter + 1);
    } else {
      window.alert("Você não selecionou pelo menos uma matéria");
    }
  };

  const handleClickBack = () => {
    setPagCounter(pagCounter - 1);
  };

  if (pagesArray.indexOf("PersonalData") === pagCounter) {
    return <PersonalData key={pagesArray[0]} handleChange={handleChange} handleNextOne={handleNextOne} />;
  } else if (pagesArray.indexOf("AcademicsData") === pagCounter) {
    return <AcademicsData key={pagesArray[1]} handleChange={handleChange} handleNextTwo={handleNextTwo} buttonSelected={personalData.schoolYear} buttonSelectedTwo={personalData.house} handleClickBack={handleClickBack} />;
  } else if (pagesArray.indexOf("MattersChoicer") === pagCounter) {
    return <MattersChoicer key={pagesArray[2]} handleChange={handleChangeClasses} handleNextThree={handleNextThree} handleClickBack={handleClickBack} />;
  } else if (pagesArray.indexOf("AllDataOfProfile") === pagCounter) {
    return <AllDataOfProfile key={pagesArray[2]} fullName={personalData.fullName} animals={personalData.animals} adress={personalData.adress} adressNumber={personalData.adressNumber} house={personalData.house} classes={personalData.classes} schoolYear={personalData.schoolYear} handleClickBack={handleClickBack} />;
  }
};
