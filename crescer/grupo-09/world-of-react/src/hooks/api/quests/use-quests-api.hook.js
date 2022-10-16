import { useMemo } from "react";
import { useHttp } from "../_base/use-http.hook";
import { useGlobalUser } from "../../../context";
import { BASE_WOW_API_URL } from "../../../constants";

export const useQuestsApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(BASE_WOW_API_URL, {
    authorization: globalUser.token,
  });

  const getQuests = async () => {
    const response = await httpInstance.get("/quests");

    return response;
  };

  const getQuestById = async (id) => {
    const response = await httpInstance.get(`/quests/${id}`);

    return response;
  };

  const startQuest = async (characterId, questId) => {
    const response = await httpInstance.post(`/quests/${questId}/start`, {
      characterId,
    });

    return response;
  };

  const finishQuest = async (characterId) => {
    const response = await httpInstance.post("/quests/finish", { characterId });

    return response;
  };

  return useMemo(
    () => ({
      getQuests,
      getQuestById,
      startQuest,
      finishQuest,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [globalUser]
  );
};
