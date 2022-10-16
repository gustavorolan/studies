import React from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalUser } from "../../../contexts/user.context";
import { Button } from "../button/button.component";
import "./logoutButton.style.css";

export const LogoutButton = () => {
  const [, setUser] = useGlobalUser();
  const navigate = useNavigate();
  const handleClick = () => {
    setUser({});
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <div className="logouButton">
      <Button onClick={handleClick} children={"Sair"} />
    </div>
  );
};
