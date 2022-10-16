import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, USER } from "../../../constants/index.js";

export const useUserApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + USER.BASE_URL, {
    "x-auth-token": globalUser.token,
  });

  const getUserLogged = async () => {
    return await httpInstance.get();
  };

  const getUserLoggedCourses = async () => {
    return await httpInstance.get(USER.REGISTERED_COURSES);
  };

  const sendMessage = async (data) => {
    return await httpInstance.post(USER.PRIVATE_MESSAGE, data);
  };

  const getMessages = async (email) => {
    return await httpInstance.get(`${USER.GET_CHAT}/${email}`);
  };

  const readMessage = async (email) => {
    return await httpInstance.post(`${USER.MESSAGES}/${email}${USER.READ}`);
  };

  const getMyMessages = async () => {
    return await httpInstance.get(USER.MESSAGES);
  };

  const getDetailedUser = async (userId) => {
    return await httpInstance.get(`${USER.DETAIL}/${userId}`);
  };

  return useMemo(
    () => ({
      getUserLogged,
      getUserLoggedCourses,
      sendMessage,
      getMessages,
      getDetailedUser,
      getMyMessages,
      readMessage,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
