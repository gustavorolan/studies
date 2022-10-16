// nome completo, endereço, n animal de estimção e tipo sanguineo
import { LENGTH_MAX_ADRESS, LENGTH_MAX_NAME, LENGTH_MAX_NUMBER, LENGTH_MIN_ADRESS, LENGTH_MIN_NAME, LENGTH_MIN_NUMBER, NAMES, ONLY_NUMBER } from "../../constants/constants/constants.js";

export const validationOne = (data) => {
  const { fullName, adress, adressNumber, animals, bloodType } = data;
  const booleans = {};
  NAMES.test(fullName) && LENGTH_MIN_NAME <= fullName.length <= LENGTH_MAX_NAME ? (booleans.name = true) : (booleans.name = false);

  NAMES.test(adress) && LENGTH_MIN_ADRESS <= adress.length <= LENGTH_MAX_ADRESS ? (booleans.adress = true) : (booleans.adress = false);

  ONLY_NUMBER.test(adressNumber) && LENGTH_MIN_NUMBER <= adressNumber.toString().length && adressNumber.toString().length <= LENGTH_MAX_NUMBER ? (booleans.adressNumber = true) : (booleans.adressNumber = false);

  animals ? (booleans.animals = true) : (booleans.animals = false);
  bloodType ? (booleans.bloodType = true) : (booleans.bloodType = false);
  return booleans;
};

export const validationTwo = (data) => {
  const { schoolYear, house } = data;
  const booleans = {};
  schoolYear ? (booleans.schoolYear = true) : (booleans.schoolYear = false);
  house ? (booleans.house = true) : (booleans.house = false);
  return booleans;
};

export const validationThree = (data) => {
  const { classes } = data;
  const booleans = {};
  classes.length > 0 ? (booleans.classes = true) : (booleans.classes = false);
  return booleans;
};

export const validationByStep = (pathStep, personalData) => {
  const validationOneBoolean = Object.values(validationOne(personalData)).every((personalDataIsTrue) => personalDataIsTrue === true);
  const validationTwoBoolean = Object.values(validationTwo(personalData)).every((personalDataIsTrue) => personalDataIsTrue === true);
  const validationThreeBoolean = Object.values(validationThree(personalData)).every((personalDataIsTrue) => personalDataIsTrue === true);

  switch (pathStep) {
    case "AcademicsData":
      return validationOneBoolean;
    case "MatterChoicer":
      return [validationOneBoolean && validationTwoBoolean];
    case "AllDataOfProfile":
      return validationOneBoolean && validationTwoBoolean && validationThreeBoolean;
    default:
      return true;
  }
};
