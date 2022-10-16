import React from "react";
import "./commentsAndLikesPeople.style.css";
import fotoNulldefault from "../../../assets/images/fotoNulldefault.jpg";

export const CommentsAndLikesPeople = ({
  setLileAndPostList,
  likeAndPostList,
}) => {
  const handleClick = () => {
    setLileAndPostList(null);
  };
  return (
    <div className="listPeopleContainer">
      {likeAndPostList.map(({ userAccountResponse, commentText }, index) => (
        <div key={index} className="commentAndUserContainer">
          <div className="profilePictureAndNickname">
            <img
              src={userAccountResponse?.profileImg || fotoNulldefault}
              alt="foto perfil"
            />
            <p>{userAccountResponse?.nickName}</p>
          </div>
          {commentText && (
            <div className="peopleComments">
              <p>{commentText}</p>
            </div>
          )}
        </div>
      ))}
      <div className="exitButton">
        <button onClick={handleClick}>x</button>
      </div>
    </div>
  );
};
