import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { useGlobalUser } from "../../../context";
import { BASE_WOW_API_URL } from "../../../constants";

export function useUserApi() {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(BASE_WOW_API_URL, {
    authorization: globalUser.token,
  });

  const getUserInformation = async () => {
    const response = await httpInstance.get(`/user/me`);
    return response;
  }

  return useMemo(
    () => ({
      getUserInformation,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser]
  );
}
