import React from "react";
import fotoNulldefault from "../../../assets/images/fotoNulldefault.jpg";
import "../userInformationHome/userInformationHome.style.css";

export const FriendInformationHome = ({ user }) => {
  return (
    <div className="UserInformationHomeContiner">
      <div className="profileUserNameContainer">
        <img src={user.profileImg || fotoNulldefault} alt="foto do usuario" />
        <h1>{user.nickName}</h1>
      </div>
      <div className="InformationUserContainer">
        <p>{user.userName}</p>
        <p>{user.email}</p>
      </div>
    </div>
  );
};
