import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { useGlobalUser } from "../../../context";
import { BASE_WOW_API_URL } from "../../../constants";

export function useCharactersApi() {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(BASE_WOW_API_URL, {
    authorization: globalUser.token,
  });

  const getCharacters = async () => {
    const response = await httpInstance.get("/user/me/characters");

    return response;
  };

  const postCreateCharacter = async (body) => {
    const response = await httpInstance.post("/user/create-character", body);

    return response;
  };

  const postDeleteCharacter = async (id) => {
    const response = await httpInstance.post(
      `/user/me/characters/${id}/delete`
    );

    return response;
  };

  const getCharacterById = async (id) => {
    const response = await httpInstance.get(`/user/me/characters/${id}`);

    return response;
  };
  const postBattle = async (id, body) => {
    const response = await httpInstance.post(
      `/user/me/characters/${id}/battle`,
      body
    );

    return response;
  };

  return useMemo(
    () => ({
      getCharacters,
      postCreateCharacter,
      postDeleteCharacter,
      postBattle,
      getCharacterById,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser]
  );
}
