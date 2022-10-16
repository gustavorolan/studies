import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, DOUBTS } from "../../../constants/index.js";

export const useDoubtApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + DOUBTS.BASE_URL, {
    "x-auth-token": globalUser.token,
  });

  const makeADoubt = async (description, imageId) => {
    return await httpInstance.post(`${DOUBTS.INCLUDE}/${imageId}`, {
      description: description,
    });
  };

  const getAllDoubts = async (page) => {
    const response = await httpInstance.get(`${DOUBTS.GET_ALL_DOUBTS}`, {
      params: { page: page },
    });
    return response.content;
  };

  const getDetailedDoubt = async (idDoubt) => {
    return await httpInstance.get(`${DOUBTS.GET_DETAILED_DOUBT}/${idDoubt}`);
  };

  return useMemo(
    () => ({
      makeADoubt,
      getAllDoubts,
      getDetailedDoubt,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
