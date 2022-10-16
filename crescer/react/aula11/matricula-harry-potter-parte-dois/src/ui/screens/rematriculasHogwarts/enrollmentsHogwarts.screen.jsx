import React from "react";
import { EnrollmentsHogwarts } from "../../components";
import { useNavigate } from "react-router-dom";
import "../enrollmentsHogwarts.style.css";
import { useGlobalUser } from "../../../contexts";

export const EnrollmentsHogwartsScreen = ({ logout }) => {
  const [personalData, setPersonalData] = useGlobalUser();
  const navigate = useNavigate();
  const handleChange = (event) => {
    setPersonalData({ ...personalData, [event.name]: event.value });
  };
  const handleClick = () => {
    navigate("/PersonalData");
  };

  return <EnrollmentsHogwarts handleChange={handleChange} handleClick={handleClick} />;
};
