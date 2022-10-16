import React, { useEffect, useState } from "react";
import { useGlobalUser } from "../../../contexts/user.context";
import { HAPPY_FACE, X_TOAST } from "../../../core/constants";
import { setToastWithTimout } from "../../../core/functions";
import { useTccApi } from "../../../hooks/api";
import {
  Friend,
  FriendsButtonNavigation,
  LogoutButton,
} from "../../components";
import "./friendsList.style.css";

export const FriendsList = ({setToast}) => {
  const [user, setUser] = useGlobalUser();
  const [friendsList, setFriendsList] = useState([]);
  const { getAllFriends, removeFriend } = useTccApi();

  const getUseApi = async () => {
    try {
      const response = await getAllFriends(user.token);
      setFriendsList(response.data);
      
     
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast,error.response.data.message,X_TOAST)
    }
  };

  useEffect(() => {
    getUseApi();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleRemoveFriend = async (idToRemove) => {
    try {
    const {data} = await removeFriend(user.token, idToRemove);
      getUseApi();
      setToastWithTimout(setToast,data.response,HAPPY_FACE)
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast,error.response.data.message,X_TOAST)
    }
    
  };
  return (
    <>
      <LogoutButton />
      <FriendsButtonNavigation />
      <div className="friendsList">
        {friendsList.map(
          ({ userId, userName, nickName, email, profileImg }) => (
            <Friend
              setToast={setToast}
              key={userId}
              userId={userId}
              username={userName}
              nickname={nickName}
              email={email}
              profileImg={profileImg}
              handleRemoveFriend={handleRemoveFriend}
              buttonName={"Desfazer Amizade"}
            />
          )
        )}
      </div>
    </>
  );
};
