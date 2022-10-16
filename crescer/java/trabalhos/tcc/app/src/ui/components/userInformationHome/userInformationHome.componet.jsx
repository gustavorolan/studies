import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import fotoNulldefault from "../../../assets/images/fotoNulldefault.jpg";
import { useGlobalUser } from "../../../contexts/user.context";
import { HAPPY_FACE, X_TOAST } from "../../../core/constants";
import { setToastWithTimout } from "../../../core/functions";
import { useTccApi } from "../../../hooks/api";
import { Button } from "../button/button.component";
import "./userInformationHome.style.css";

export const UserInformationHome = ({
  user,
  getUserApi,
  getFriendsPostApi,
  setToast
}) => {
  const navigate = useNavigate();
  const { changeUserInformation } = useTccApi();
  const [editButtonController, setEditButtonController] = useState(false);
  const [requestToChange, setRequestToChange] = useState({
    username: "",
    nickname: "",
    profileImg: "",
  });
  const [userGlobal, setUserGlobal] = useGlobalUser();

  const handleChange = ({ value, name }) => {
    setRequestToChange({ ...requestToChange, [name]: value });
  };
  const handleClick = async () => {
    try {
      const {data} = await changeUserInformation(
        userGlobal.token,
        requestToChange.username,
        requestToChange.nickname,
        requestToChange.profileImg
      );
      await getUserApi();
      await getFriendsPostApi();
      setEditButtonController(false);
      setToastWithTimout(setToast,data.response,HAPPY_FACE)
      
    } catch (error) {
      if (error.response.status === 401) {
        setUserGlobal({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast,error.response.data.message,X_TOAST)
    }
  };

  const handleClickToFriends = () => {
    navigate("/friends/friendsList");
  };
  const handleClickProfile = () => {
    navigate(`/profile/${user.nickName}`);
  };
  return (
    <div className="UserInformationHomeContiner">
      <div className="profileUserNameContainer">
        <img src={user.profileImg || fotoNulldefault} alt="foto do usuario" />
        <h1>{user.nickName}</h1>
      </div>
      <div className="InformationUserContainer">
        <p>{user.userName}</p>
        <p>{user.email}</p>
        <div className="detailsInformationUserContainer">
          <div>
            <button onClick={handleClickToFriends}>
              {" "}
              <p>Amigos</p>
            </button>
          </div>
          <div>
            <button onClick={handleClickProfile}>
              <p>Jogos</p>
            </button>
          </div>
        </div>
      </div>
      <div className="editarButton">
        {" "}
        <button onClick={() => setEditButtonController(!editButtonController)}>
          {" "}
          🖊️
        </button>
      </div>

      {editButtonController && (
        <div
          onChange={(Event) => handleChange(Event.target)}
          className="editar"
        >
          <div className="editarButton">
            <button
              onClick={() => setEditButtonController(!editButtonController)}
            >
              🖊️
            </button>
          </div>
          <label htmlFor="userName">nome</label>
          <input type="text" name="username" />
          <label htmlFor="nickName">apelido</label>
          <input type="text" name="nickname" />
          <label htmlFor="profileImg">imagem</label>
          <input type="text" name="profileImg" />
          <Button onClick={handleClick} children={"Mudar"} />
        </div>
      )}
    </div>
  );
};
