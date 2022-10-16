export const ROUTES = {
  API_BASE_URL: "http://localhost:8080",
  AUTH_LOGIN: "/login",
  REGISTER: "/user/register",
};

export const DOUBTS = {
  BASE_URL: "/doubts",
  INCLUDE: "/include",
  CHANGE_STATE: "/changeState",
  GET_ALL_DOUBTS: "/allDoubts",
  GET_DETAILED_DOUBT: "/doubt",
};

export const COMMENT = {
  BASE_URL: "/comments",
  INCLUDE: "/include",
};

export const TEAM = {
  BASE_URL: "/teams",
  INCLUDE: "/include",
  UPDATE: "/update",
  INCLUDE_USER: "/includeUserInTeam",
  LIST_LOGGED_USER_TEAMS: "/myTeams",
  DETAILS: "/detail",
  MY_CREATED_TEAMS: "/myCreatedTeams",
  REMOVE_USER: "/removeUserFromTeam",
  ALL_USERS_NOT_IN_TEAM: "/allUsersNotInTeam",
  ALL_USERS_IN_TEAM: "/allUsersInTeam",
  CHANGE_ACTIVE_STATUS: "/changeActiveStatus",
};

export const COURSES = {
  BASE_URL: "/courses",
  LIST_ALL_COURSES: "/allCourses",
  LIST_LOGGED_USER_COURSES: "/myCourses",
  DETAIL: "/detail",
  INCLUDE: "/include",
  INCLUDE_IN_ROADMAP: "/includeCourseInTeam",
  PENDENT_COURSES: "/pendentCourses",
  NOT_IN_TEAM: "/coursesNotInTeam",
  CHANGE_APPROVEMENT_STATUS: "/changeApprovementStatus",
  CHANGE_ACTIVE_STATUS: "/changeActiveStatus",
  UPDATE: "/update",
};

export const USER = {
  BASE_URL: "/me",
  REGISTERED_COURSES: "/registeredCourses",
  PRIVATE_MESSAGE: "/privateMessage",
  GET_CHAT: "chat",
  DETAIL: "/detail",
  MESSAGES: "/messages",
  READ: "/read",
};

export const VIDEO = {
  BASE_URL: "/videos",
  UPLOAD: "/upload",
  LIST: "/list",
  INCLUDE_VIDEO_INTO_COURSE: "/includeVideoIntoCourse",
  REMOVE: "/removeVideo",
  REMOVE_FROM_COURSE: "/removeVideoFromCourse",
  LIST_VIDEO_EVALUATIONS: "/listVideoEvaluations",
  DETAILED_VIDEO: "/detailedVideo",
  LIST_SHORT_VIDEOS: "/listShortVideos",
  DOWNLOAD_SUPPORT_FILE: "/materialSupport",
  INCLUDE_SUPPORT_MATERIAL: "/supportMaterial",
  CHANGE_ASSES_STATUS: "/changeAssesStatus",
  ASSESMENT: "/assessment",
};

export const ROADMAP = {
  BASE_URL: "/roadmaps",
  DETAIL: "/detail",
};

export const IMAGE = {
  UPLOAD: "/upload",
  DOWNLOAD: "/download",
};
