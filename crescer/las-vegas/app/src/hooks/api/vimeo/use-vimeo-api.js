import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, VIDEO } from "../../../constants/index.js";

export const useVimeoApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + VIDEO.BASE_URL, {
    "x-auth-token": globalUser.token,
    "Content-Type": "multipart/form-data",
  });

  const upload = async (data) => {
    return await httpInstance.post(VIDEO.UPLOAD, data);
  };

  return useMemo(
    () => ({ upload }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
