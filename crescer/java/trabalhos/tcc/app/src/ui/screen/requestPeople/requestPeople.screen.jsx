import React, { useState } from "react";
import { useGlobalUser } from "../../../contexts/user.context";
import { HAPPY_FACE, X_TOAST } from "../../../core/constants";
import { setToastWithTimout } from "../../../core/functions";
import { useTccApi } from "../../../hooks/api";
import {
  Friend,
  FriendsButtonNavigation,
  LogoutButton,
} from "../../components";
import "../friendsList/friendsList.style.css";
import "./requestPeople.style.css";

export const RequestPeople = ({setToast}) => {
  const [user, setUser] = useGlobalUser();
  const [friendsList, setFriendsList] = useState([]);
  const { searchFriends, requestFriendShip } = useTccApi();
  const [search, setSearch] = useState("");

  const handleChange = async (searchInput) => {
    try {
      setSearch(searchInput);
      const response = await searchFriends(user.token, searchInput);
      setFriendsList(response.data);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
    }
  };

  const handleRequestFriend = async (idFriend) => {
    try {
      const {data}=await requestFriendShip(user.token, idFriend);
      const response = await searchFriends(user.token, search);
      setFriendsList(response.data);
      setToastWithTimout(setToast,data.response,HAPPY_FACE)
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast, error.response.data.message, X_TOAST);
    }
  };

  return (
    <>
      <LogoutButton setToast={setToast}/>
      <FriendsButtonNavigation setToast={setToast}/>
      <div className="friendsList">
        <div
          className="inputDiv"
          onChange={(Event) => handleChange(Event.target.value)}
        >
          <input type="text" placeholder="Pesquisar Contato" />
        </div>

        {friendsList.map(
          ({ userId, userName, nickName, email, profileImg }) => (
            <Friend
              key={userId}
              userId={userId}
              username={userName}
              nickname={nickName}
              email={email}
              profileImg={profileImg}
              handleRemoveFriend={handleRequestFriend}
              buttonName={"Solicitar Amizade"}
            />
          )
        )}
      </div>
    </>
  );
};
