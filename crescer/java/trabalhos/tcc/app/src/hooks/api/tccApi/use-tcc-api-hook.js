import { useMemo } from "react";
import { URL_BASE_TCC } from "../../../constants/constants";
import { useHttp } from "../_base/use-http.hook";

export function useTccApi() {
  const httpInstance = useHttp(URL_BASE_TCC);

  const getLogin = async ({ username, password }) => {
    // const response = await httpInstance.post("/login", {}, {auth:{username:usernameInput,password:passwordInput}});

    const response = await httpInstance.request({
      url: "/login",
      method: "post",
      auth: { username, password },
    });

    return response;
  };

  const createNewUser = async (userName, nickName, password, email) => {
    const response = await httpInstance.post(
      "/createNewUser",
      {},
      {
        userName,
        nickName,
        password,
        email,
      }
    );
    return response;
  };

  const getAllFriends = async (token) => {
    const response = await httpInstance.request({
      method: "get",
      url: "/user/getAllFriends",
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const searchFriends = async (token, search) => {
    const response = await httpInstance.request({
      method: "post",
      url: "/search",
      headers: {
        "X-Auth-Token": token,
      },
      data: { search: search },
    });
    return response;
  };

  const getAllRequestsToBeFriends = async (token) => {
    const response = await httpInstance.request({
      method: "get",
      url: "/user/getAllRequestFriends",
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const getFriendsPost = async (token) => {
    const response = await httpInstance.request({
      method: "get",
      url: "/user/getPostFriends",
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const removeFriend = async (token, idRemove) => {
    const response = await httpInstance.request({
      method: "put",
      url: "/user/undoFriendship",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        idToUndoFriendShip: idRemove,
      },
    });
    return response;
  };

  const acceptFriend = async (token, idAccept) => {
    const response = await httpInstance.request({
      method: "put",
      url: "/user/acceptNewFriendship",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        friendshipId: idAccept,
      },
    });
    return response;
  };

  const requestFriendShip = async (token, idFriend) => {
    const response = await httpInstance.request({
      method: "post",
      url: "/user/requestNewFriendship",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        friendId: idFriend,
      },
    });
    return response;
  };

  const like = async (token, postId) => {
    const response = await httpInstance.request({
      method: "post",
      url: "/post/like",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        postId: postId,
      },
    });
    return response;
  };

  const createPost = async (token, postText, pricatePost) => {
    const response = await httpInstance.request({
      method: "post",
      url: "/post/create",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        postText: postText,
        privatePost: pricatePost,
      },
    });
    return response;
  };

  const getUser = async (token) => {
    const response = await httpInstance.request({
      method: "get",
      url: "/user",
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const getUserWithPosts = async (token, nickName) => {
    const response = await httpInstance.request({
      method: "get",
      url: `/friends/${nickName}`,
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const includeMyselfOnGame = async (token, postId) => {
    const response = await httpInstance.request({
      method: "put",
      url: "/post/includeNewUser",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        idPost: postId,
      },
    });
    return response;
  };

  const comment = async (token, postId, comment) => {
    const response = await httpInstance.request({
      method: "post",
      url: "/post/comment/create",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        postId: postId,
        comment: comment,
      },
    });
    return response;
  };

  const showComments = async (token, id) => {
    const response = await httpInstance.request({
      method: "get",
      url: `/post/${id}/comments`,
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const showLikes = async (token, id) => {
    const response = await httpInstance.request({
      method: "get",
      url: `/post/${id}/likes`,
      headers: {
        "X-Auth-Token": token,
      },
    });
    return response;
  };

  const changeUserInformation = async (
    token,
    username,
    nickname,
    profileImg
  ) => {
    const response = await httpInstance.request({
      method: "put",
      url: "/user/changeUser",
      headers: {
        "X-Auth-Token": token,
      },
      data: {
        userName: username,
        nickName: nickname,
        profileImg: profileImg,
      },
    });
    return response;
  };

  return useMemo(
    () => ({
      getLogin,
      createNewUser,
      getAllFriends,
      removeFriend,
      getAllRequestsToBeFriends,
      acceptFriend,
      searchFriends,
      requestFriendShip,
      getFriendsPost,
      like,
      createPost,
      getUser,
      getUserWithPosts,
      includeMyselfOnGame,
      comment,
      showComments,
      showLikes,
      changeUserInformation,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
}
