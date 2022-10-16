import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, COMMENT } from "../../../constants/index.js";

export const useCommentApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + COMMENT.BASE_URL, {
    "x-auth-token": globalUser.token,
  });

  const makeACommentInDoubt = async (description, idDoubt, idImage) => {
    return await httpInstance.post(`${COMMENT.INCLUDE}/${idDoubt}/${idImage}`, {
      description: description,
    });
  };

  return useMemo(
    () => ({
      makeACommentInDoubt,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
