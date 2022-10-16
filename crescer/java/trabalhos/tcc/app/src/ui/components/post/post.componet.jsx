import "./post.style.css";
import React, { useState } from "react";
import fotoNulldefault from "../../../assets/images/fotoNulldefault.jpg";
import { Button } from "../button/button.component";
import { useTccApi } from "../../../hooks/api";
import { useGlobalUser } from "../../../contexts/user.context";
import { CommentsAndLikesPeople } from "../commentsAndLikes/commentsAndLikesPeople.component";
import { setToastWithTimout } from "../../../core/functions";
import { HAPPY_FACE, X_TOAST } from "../../../core/constants";
export const Post = ({
  getFriendsPostApi,
  userAccount,
  postText,
  postId,
  likes,
  comments,
  nickname,
  setToast,
}) => {
  const [user, setUser] = useGlobalUser();
  const [commentInput, setCommentsInput] = useState();
  const { includeMyselfOnGame, like, comment, showComments, showLikes } =
    useTccApi();
  const [likeAndPostList, setLileAndPostList] = useState(null);

  const handleLikeShowUp = async () => {
    try {
      const { data } = await showLikes(user.token, postId);
      setLileAndPostList(data);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
        setToastWithTimout(setToast, error.response.data.message, X_TOAST);
      }
    }
  };
  const handleCommentShowUp = async () => {
    try {
      const { data } = await showComments(user.token, postId);
      setLileAndPostList(data);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
        setToastWithTimout(setToast, error.response.data.message, X_TOAST);
      }
    }
  };

  const handleAddPerson = async () => {
    try {
      const { data } = await includeMyselfOnGame(user.token, postId);
      await getFriendsPostApi();
      setToastWithTimout(setToast, data.response, HAPPY_FACE);
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
      const { data } = await like(user.token, postId);
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
  const handleCommentInput = async (postId) => {
    try {
      const { data } = await comment(user.token, postId, commentInput);
      getFriendsPostApi();
      setToastWithTimout(setToast, data.response, HAPPY_FACE);
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
      setToastWithTimout(setToast, "Comentário inválido ", X_TOAST);
    }
  };
  const handleChangeCommentInput = (value) => {
    setCommentsInput(value);
  };

  return (
    <div className="postContainer">
      <div className="post">
        <div className="postAuthor">
          <img
            src={
              userAccount &&
              (userAccount.profileImg ||
                userAccount[0].profileImg ||
                fotoNulldefault)
            }
            alt="foto perfil"
          />
          <p>{userAccount ? userAccount[0].nickName : nickname}</p>
        </div>
        <div className="players">
          <div>
            <button onClick={handleAddPerson}>
              <img
                src={
                  userAccount &&
                  (userAccount.profileImg ||
                    userAccount[0].profileImg ||
                    fotoNulldefault)
                }
                alt="foto usuario"
              />
            </button>
            <button onClick={handleAddPerson}>
              <img
                src={
                  userAccount &&
                  (userAccount[1] ? userAccount[1].profileImg : fotoNulldefault)
                }
                alt="foto usuario"
              />
            </button>
          </div>
          <div>
            <button onClick={handleAddPerson}>
              <img
                src={
                  userAccount &&
                  (userAccount[2] ? userAccount[2].profileImg : fotoNulldefault)
                }
                alt="foto usuario"
              />
            </button>
            <button onClick={handleAddPerson}>
              <img
                src={
                  userAccount &&
                  (userAccount[3] ? userAccount[3].profileImg : fotoNulldefault)
                }
                alt="foto usuario"
              />
            </button>
          </div>
        </div>
        <div>
          <p>{postText}</p>
          <p></p>
        </div>
        <div className="likesAndComments">
          <button onClick={handleLikeShowUp}>
            <p>{likes} curtidas</p>
          </button>
          <button onClick={handleCommentShowUp}>
            <p>{comments} comentarios</p>
          </button>
        </div>
      </div>
      <div
        onChange={(Event) => handleChangeCommentInput(Event.target.value)}
        className="commentInput"
      >
        <input type="text" placeholder="Escreva um Comentario" />
      </div>
      <div className="buttonsPost">
        <Button onClick={() => handleLike(postId)} children={` Curtir`} />{" "}
        <Button
          onClick={() => handleCommentInput(postId)}
          children={` Comentar`}
        />
      </div>
      {likeAndPostList && (
        <div className="commentsAndLikes">
          <CommentsAndLikesPeople
            setLileAndPostList={setLileAndPostList}
            likeAndPostList={likeAndPostList}
          />
        </div>
      )}
    </div>
  );
};
