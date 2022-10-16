import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { BASE_WOW_API_URL } from "../../../constants";
import { useGlobalCharacter, useGlobalUser } from "../../../context";

export const useAuthApi = () => {
  const httpInstance = useHttp(BASE_WOW_API_URL);
  const [globalUser, setGlobalUser] = useGlobalUser();
  const [globalCharacter, setGlobalCharacter] = useGlobalCharacter();

  const register = async ({username, password, confirmPassword}) => {
    await httpInstance.post("/auth/register", {
      username,
      password,
      confirmPassword,
    });

    const response = await login({username, password})

    return response;
  }

  const login = async ({username, password}) => {
    const response = await httpInstance.post("/auth/login", {
      username,
      password,
    });

    if (response) {
      setGlobalCharacter({});
      setGlobalUser(response);
    }

    return response;
  }

  return useMemo(
    () => ({
      login,
      register
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser, globalCharacter]
  );
};
