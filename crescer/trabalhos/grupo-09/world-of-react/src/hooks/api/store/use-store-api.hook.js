import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { useGlobalCharacter, useGlobalUser } from "../../../context";
import { BASE_WOW_API_URL } from "../../../constants";

export function useStoreApi() {
  const [globalUser] = useGlobalUser();
  const [globalCharacter] = useGlobalCharacter();

  const httpInstance = useHttp(BASE_WOW_API_URL, {
    authorization: globalUser.token,
  });

  const getItemsStore = async () => {
    const response = await httpInstance.get("/shop");

    return response;
  }
  const getItemDetailStore = async (id) => {
    const response = await httpInstance.get(`/shop/${id}`);

    return response;
  }
  const buyItemsStore = async (id) => {
    const response = await httpInstance.post(`/shop/${id}/buy`, { characterId: globalCharacter.id });
    return response;
  }
  const sellItemsStore = async (id) => {
    const response = await httpInstance.post(`/shop/${id}/sell`, { characterId: globalCharacter.id });
    return response;
  }

  return useMemo(
    () => ({
      getItemsStore,
      buyItemsStore,
      getItemDetailStore,
      sellItemsStore,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser, globalCharacter]
  );
}
