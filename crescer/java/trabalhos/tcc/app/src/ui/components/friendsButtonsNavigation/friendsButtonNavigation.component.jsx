import React from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "../button/button.component";
import "./friendsButtonNavigation.style.css";
export const FriendsButtonNavigation = () => {
  const navigate = useNavigate();
  const handleClick = ({ value }) => {
    navigate(value);
  };
  return (
    <div
      onClick={(Event) => handleClick(Event.target)}
      className="navigationButtons"
    >
      <Button value={"/home"} children={"Home"} />
      <Button value={"/friends/friendsList"} children={"Amigos"} />
      <Button value={"/friends/friendsToAccept"} children={"Solicitações"} />
      <Button value={"/friends/inviteToBeFriend"} children={"Adicionar"} />
    </div>
  );
};
