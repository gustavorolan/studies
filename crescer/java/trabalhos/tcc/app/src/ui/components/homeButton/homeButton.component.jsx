import React from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "../button/button.component";
import "./homeButton.style.css";
export const HomeButton = () => {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate("/home");
  };
  return (
    <div className="buttonHome">
      <Button onClick={handleClick} children={"Home"} />
    </div>
  );
};
