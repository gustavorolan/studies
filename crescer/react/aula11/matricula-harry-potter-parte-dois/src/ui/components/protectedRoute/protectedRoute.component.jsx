import React from "react";
import { Outlet, Navigate } from "react-router-dom";
import { validationByStep } from "../../../assets/auxFunctions/auxFunctions";
import { ARRAY_KEYS_LOGIN } from "../../../constants/constants/constants";
import { useGlobalUser } from "../../../contexts";

export const ProtectedRoute = ({ nomeRota }) => {
  const [personalData] = useGlobalUser();
  const keyByHouses = ARRAY_KEYS_LOGIN.includes(personalData.keyWord);
  const validation = validationByStep(nomeRota, personalData);
  if (keyByHouses && validation) {
    return <Outlet />;
  } else if (keyByHouses && validation) {
    return <Navigate to="/PersonalData" />;
  } else {
    window.alert("Senha Incorreta");
    return <Navigate to="/" />;
  }
};
