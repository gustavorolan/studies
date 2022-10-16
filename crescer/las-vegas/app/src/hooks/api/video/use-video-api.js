import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, VIDEO } from "../../../constants/index.js";

export const useVideoApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + VIDEO.BASE_URL, {
    "x-auth-token": globalUser.token,
  });

  const httpInstanceMultipart = useHttp(ROUTES.API_BASE_URL + VIDEO.BASE_URL, {
    "x-auth-token": globalUser.token,
    "Content-Type": "multipart/form-data",
  });

  const listVideosByPage = async (page, filter) => {
    const response = await httpInstance.get(VIDEO.LIST, {
      params: { size: 1, page, filter: filter },
    });
    return response.content;
  };

  const listShortVideosByPage = async (page, filter) => {
    const response = await httpInstance.get(VIDEO.LIST_SHORT_VIDEOS, {
      params: { size: 1, page, filter: filter },
    });
    return response.content;
  };

  const includeVideoIntoCourse = async (videoId, courseId) => {
    return await httpInstance.post(
      `${VIDEO.INCLUDE_VIDEO_INTO_COURSE}/${courseId}/${videoId}`
    );
  };

  const removeVideoFromCourse = async (videoId, courseId) => {
    return await httpInstance.post(
      `${VIDEO.REMOVE_FROM_COURSE}/${courseId}/${videoId}`
    );
  };

  const evaluateVideo = async (data) => {
    const scoreParam = data.name;
    const commentParam = data.description;
    const videoId = data.imageId;

    return await httpInstance.post(`/${videoId}${VIDEO.ASSESMENT}`, {
      comment: commentParam,
      score: scoreParam,
    });
  };

  const removeVideoEvaluation = async (evaluationId) => {
    return await httpInstance.post(
      `${VIDEO.CHANGE_ASSES_STATUS}${VIDEO.ASSESMENT}/${evaluationId}`
    );
  };

  const getDetailedVideo = async (videoId) => {
    return await httpInstance.get(`${VIDEO.DETAILED_VIDEO}/${videoId}`);
  };

  const listVideoEvaluations = async (
    videoId,
    page,
    score,
    filter,
    ordenation
  ) => {
    return await httpInstance.get(
      `${VIDEO.LIST_VIDEO_EVALUATIONS}/${videoId}`,
      {
        params: {
          size: 1,
          page,
          score: score,
          filter: filter,
          ordenation: ordenation,
        },
      }
    );
  };

  const removeVideo = async (videoId) => {
    return await httpInstance.post(`${VIDEO.REMOVE}/${videoId}`);
  };

  const includeSupportMaterial = async (data, videoId) => {
    return await httpInstanceMultipart.post(
      `/${videoId}${VIDEO.INCLUDE_SUPPORT_MATERIAL}`,
      data
    );
  };

  const downloadSupportFile = async (videoId) => {
    return await httpInstance.get(`/${videoId}${VIDEO.DOWNLOAD_SUPPORT_FILE}`);
  };

  return useMemo(
    () => ({
      listVideosByPage,
      includeVideoIntoCourse,
      removeVideoFromCourse,
      evaluateVideo,
      listVideoEvaluations,
      removeVideo,
      includeSupportMaterial,
      downloadSupportFile,
      removeVideoEvaluation,
      listShortVideosByPage,
      getDetailedVideo,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
