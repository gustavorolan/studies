import React from "react";
import { AllDataOfProfile } from "../../components";
import { useGlobalUser } from "../../../contexts";
import "../enrollmentsHogwarts.style.css";
import { useNavigate } from "react-router-dom";
export const AllDataOfProfileScreen = ({ logout }) => {
  const [personalData] = useGlobalUser();
  const navigate = useNavigate();
  const handleEditButton = () => {
    navigate("/PersonalData");
  };

  return (
    <>
      <AllDataOfProfile fullName={personalData.fullName} animals={personalData.animals} adress={personalData.adress} adressNumber={personalData.adressNumber} house={personalData.house} classes={personalData.classes} schoolYear={personalData.schoolYear} handleEditButton={handleEditButton} />
      <button className="logoutButton" onClick={() => logout()}>
        logout
      </button>
    </>
  );
};
