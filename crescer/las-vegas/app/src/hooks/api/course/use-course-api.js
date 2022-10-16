import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../base/use-http.hook";
import { ROUTES, COURSES } from "../../../constants/index.js";

export const useCourseApi = () => {
  const [globalUser] = useGlobalUser();

  const httpInstance = useHttp(ROUTES.API_BASE_URL + COURSES.BASE_URL, {
    "x-auth-token": globalUser.token,
  });

  const includeCourse = async ({ name, description, imageId }) => {
    return await httpInstance.post(`${COURSES.INCLUDE}/${imageId}`, {
      name,
      description,
    });
  };

  const getAllCourses = async () => {
    return await httpInstance.get(
      ROUTES.API_BASE_URL + COURSES.BASE_URL + COURSES.LIST_ALL_COURSES
    );
  };

  const getLoggedUserCourses = async () => {
    return await httpInstance.get(
      ROUTES.API_BASE_URL + COURSES.BASE_URL + COURSES.LIST_LOGGED_USER_COURSES
    );
  };

  const getPendentCourses = async () => {
    return await httpInstance.get(COURSES.PENDENT_COURSES);
  };

  const getCoursesNotInTeam = async () => {
    return await httpInstance.get(COURSES.NOT_IN_TEAM);
  };

  const getDetailedCourse = async (courseId) => {
    return await httpInstance.get(`${COURSES.DETAIL}/${courseId}`);
  };

  const includeCourseInTeam = async (courseId) => {
    return await httpInstance.post(`${COURSES.INCLUDE_IN_ROADMAP}/${courseId}`);
  };

  const changeCourseApprovementStatus = async (courseId) => {
    return await httpInstance.post(
      `${COURSES.CHANGE_APPROVEMENT_STATUS}/${courseId}`
    );
  };

  const changeCourseStatus = async (courseId) => {
    return await httpInstance.post(
      `${COURSES.CHANGE_ACTIVE_STATUS}/${courseId}`
    );
  };

  const approveCourse = async (courseId) => {
    return await httpInstance.post(
      `${COURSES.CHANGE_APPROVEMENT_STATUS}/${courseId}`
    );
  };

  const updateCourse = async (courseId, { name, description, imageId }) => {
    return await httpInstance.post(`${COURSES.UPDATE}/${courseId}/${imageId}`, {
      name,
      description,
    });
  };

  return useMemo(
    () => ({
      getAllCourses,
      getLoggedUserCourses,
      getDetailedCourse,
      includeCourseInTeam,
      includeCourse,
      changeCourseApprovementStatus,
      changeCourseStatus,
      getPendentCourses,
      getCoursesNotInTeam,
      approveCourse,
      updateCourse,
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []
  );
};
