import React from "react";
import { useNavigate } from "react-router-dom";
import fotoNulldefault from "../../../assets/images/fotoNulldefault.jpg";
import { Button } from "../button/button.component";
import "./friend.style.css";
export const Friend = ({
  userId,
  username,
  nickname,
  email,
  profileImg,
  handleRemoveFriend,
  buttonName,
}) => {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/profile/${nickname}`);
  };

  return (
    <div className="friend">
      <button onClick={handleClick}>
        <img src={profileImg || fotoNulldefault} alt="foto de perfil" />
      </button>
      <div>
        <button onClick={handleClick}>
          <p>{nickname}</p>
          <p>{username}</p>
          <p>{email}</p>
        </button>
      </div>
      <Button
        onClick={() => handleRemoveFriend(userId)}
        children={buttonName}
      />
    </div>
  );
};
