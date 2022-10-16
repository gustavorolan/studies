import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, TEAM } from "../../../constants/index.js";

export const useTeamApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + TEAM.BASE_URL, {
    "x-auth-token": globalUser.token,
  });

  const registerTeam = async ({ name, description, imageId }) => {
    return await httpInstance.post(`${TEAM.INCLUDE}/${imageId}`, {
      name,
      description,
    });
  };

  const updateTeam = async ({ name, description, imageId }) => {
    return await httpInstance.post(`${TEAM.UPDATE}/${imageId}`, {
      name,
      description,
    });
  };

  const includeUserInTeam = async (userId) => {
    return await httpInstance.post(`${TEAM.INCLUDE_USER}/${userId}`);
  };

  const removeUserFromTeam = async (userId) => {
    return await httpInstance.post(`${TEAM.REMOVE_USER}/${userId}`);
  };

  const getAllUsersNotInTeam = async (page) => {
    const response = await httpInstance.get(`${TEAM.ALL_USERS_NOT_IN_TEAM}`, {
      params: { size: 20, page },
    });
    return response?.content;
  };

  const getAllUsersInTeam = async () => {
    return await httpInstance.get(TEAM.ALL_USERS_IN_TEAM);
  };

  const getDetailedTeam = async () => {
    return await httpInstance.get(`${TEAM.DETAILS}`);
  };

  const changeTeamStatus = async (teamId) => {
    return await httpInstance.request({
      method: "put",
      url: `${TEAM.CHANGE_ACTIVE_STATUS}/${teamId}`,
      headers: {
        "x-auth-token": globalUser.token,
      },
    });
  };

  const changeTeamActiveStatus = async (teamId) => {
    return await httpInstance.post(`${TEAM.CHANGE_ACTIVE_STATUS}/${teamId}`);
  };

  return useMemo(
    () => ({
      registerTeam,
      includeUserInTeam,
      getDetailedTeam,
      updateTeam,
      changeTeamStatus,
      changeTeamActiveStatus,
      getAllUsersNotInTeam,
      getAllUsersInTeam,
      removeUserFromTeam,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
