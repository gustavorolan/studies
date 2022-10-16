import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { useGlobalUser } from "../../../context";
import { BASE_WOW_API_URL } from "../../../constants";

export const useRacesApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(BASE_WOW_API_URL, {
    authorization: globalUser.token,
  });

  const getRaces = async () => {
    const response = await httpInstance.get("/races");

    return response;
  };

  return useMemo(
    () => ({
      getRaces,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser]
  );
};
