import "../home/home.style.css";
import React, { useEffect, useState } from "react";
import { HomeButton, LogoutButton, Post } from "../../components";
import { useTccApi } from "../../../hooks/api";
import { useGlobalUser } from "../../../contexts/user.context";
import { useParams } from "react-router-dom";
import { FriendInformationHome } from "../../components/friendInformation/friendInformationHome.component";
import { setToastWithTimout } from "../../../core/functions";
import { HAPPY_FACE, X_TOAST } from "../../../core/constants";

const Profile = ({setToast}) => {
  const [user, setUser] = useGlobalUser();
  const [friend, setFriend] = useState({});
  const [postList, setPostList] = useState([]);
  const { getUserWithPosts, like } = useTccApi();
  const { nickname } = useParams();

  const getFriendsPostApi = async () => {
    try {
      const { data } = await getUserWithPosts(user.token, nickname);
      setPostList(data.postResponseList);
      setFriend(data.userAccountResponse);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast, error.response.data.message, X_TOAST);
    }
  };

  const handleLike = async (postId) => {
    try {
      const{data}= await like(user.token, postId);
      getFriendsPostApi();
      setToastWithTimout(setToast, data.response, HAPPY_FACE);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast, error.response.data.message, X_TOAST);
    }
  };

  useEffect(() => {
    getFriendsPostApi();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <LogoutButton />
      <HomeButton />
      <div className="home">
        <FriendInformationHome setToast={setToast} user={friend} />
        <div className="postList">
          {postList.map(
            ({
              postId,
              userAccountResponseList,
              postText,
              likes,
              comments,
              nickName,
            }) => {
              return (
                <Post setToast={setToast}
                  getFriendsPostApi={getFriendsPostApi}
                  handleLike={handleLike}
                  key={postId}
                  postId={postId}
                  userAccount={userAccountResponseList}
                  postText={postText}
                  likes={likes}
                  comments={comments}
                  nickname={nickname}
                />
              );
            }
          )}
        </div>
      </div>
    </>
  );
};

export default Profile;
