import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, IMAGE } from "../../../constants/index.js";

export const useImageApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL, {
    "x-auth-token": globalUser.token,
    "Content-Type": "multipart/form-data",
  });

  const includeImage = async (data) => {
    return await httpInstance.post(IMAGE.UPLOAD, data);
  };

  return useMemo(
    () => ({
      includeImage,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
