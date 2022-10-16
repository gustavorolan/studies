import "./home.style.css";

import React, { useEffect, useState } from "react";
import { LogoutButton, Post, UserInformationHome } from "../../components";
import { useTccApi } from "../../../hooks/api";
import { useGlobalUser } from "../../../contexts/user.context";
import { Button } from "../../components/button/button.component";
import { setToastWithTimout } from "../../../core/functions";
import { HAPPY_FACE, X_TOAST } from "../../../core/constants";

export const Home = ({ setToast }) => {
  const [user, setUser] = useGlobalUser();
  const [postList, setPostList] = useState([]);
  const { getFriendsPost, createPost, getUser } = useTccApi();
  const [post, setPost] = useState({});

  const handleChange = ({ value, name }) => {
    setPost({ ...post, [name]: value });
  };
  const handleSentPost = async () => {
    try {
      const {data} = await createPost(user.token, post.postText, post.privatePost);
      await getFriendsPostApi();
      setToastWithTimout(setToast,data.response,HAPPY_FACE)
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast, error.response.data.message, X_TOAST);
    }
  };
  const getFriendsPostApi = async () => {
    try {
      const { data } = await getFriendsPost(user.token);
      setPostList(data);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
        setToastWithTimout(setToast, error.response.data.message, X_TOAST);
      }
    }
  };

  const getUserApi = async () => {
    try {
      const { data } = await getUser(user.token);
      setUser({ ...user, ...data });
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
    }
  };

  useEffect(() => {
    getFriendsPostApi();
    getUserApi();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <LogoutButton />
      <div className="home">
        <UserInformationHome
          setToast={setToast}
          getFriendsPostApi={getFriendsPostApi}
          getUserApi={getUserApi}
          user={user}
        />
        <div className="postList">
          <div
            onChange={(Event) => handleChange(Event.target)}
            className="createPost"
          >
            <input
              type="text"
              name="postText"
              placeholder="Insira a descrição do post"
            />
            <div>
              <Button onClick={handleSentPost} children={"Publicar"} />
              <select
                name="privatePost"
                defaultValue={"selecionar"}
                placeholder=""
                id=""
              >
                <option disabled value="selecionar">
                  Privado
                </option>
                <option value="true">Sim</option>
                <option value="false">Não</option>
              </select>
            </div>
          </div>
          {postList.map(
            (
              { postId, userAccountResponse, postText, likes, comments },
              index
            ) => {
              return (
                <Post
                  setToast={setToast}
                  key={index}
                  postId={postId}
                  userAccount={userAccountResponse}
                  postText={postText}
                  likes={likes}
                  comments={comments}
                  getFriendsPostApi={getFriendsPostApi}
                />
              );
            }
          )}
        </div>
      </div>
    </>
  );
};
