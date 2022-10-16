import { useMemo } from "react";
import { useHttp } from "../base/use-http.hook";
import { ROUTES } from "../../../constants/index.js";

export const useAuthApi = () => {
  const httpInstance = useHttp(ROUTES.API_BASE_URL, {
    "Content-Type": "multipart/form-data",
  });

  const login = async (email, password) => {
    return await httpInstance.post(
      ROUTES.AUTH_LOGIN,
      {},
      {
        auth: { username: email, password: password },
      }
    );
  };

  const register = async (data) => {
    return await httpInstance.post(ROUTES.REGISTER, data);
  };

  return useMemo(
    () => ({
      login,
      register,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
