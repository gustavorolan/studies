import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { useGlobalUser, useGlobalCharacter } from "../../../context";
import { BASE_WOW_API_URL } from "../../../constants";

export function useCheatsApi() {
  const [globalUser] = useGlobalUser();
  const [globalCharacter] = useGlobalCharacter();
  const httpInstance = useHttp(BASE_WOW_API_URL, {
    authorization: globalUser.token,
  });

  const cheat = async (code) => {
    const response = await httpInstance.post("/cheat", {
      code,
      characterId: globalCharacter.id,
    });
    return response;
  };

  return useMemo(
    () => ({
      cheat,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser, globalCharacter]
  );
}
