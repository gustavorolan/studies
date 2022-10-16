import React from "react";
import { Route, Routes } from "react-router-dom";
import "./app.css";
import {
  PrivateRoute,
  DetailedCourse,
  DetailedTeam,
  ManageDetailedTeam,
} from "./ui/components";
import { PrivateRouteManager } from "./ui/components/private-route-manager/private-route-manager-component";
import {
  LoginScreen,
  MyCoursesScreen,
  VideosScreen,
  MyMessages,
  CoursesScreen,
  EvaluationsScreen,
  ForumScreen,
  PendentCoursesScreen,
  TeamIncludeUser,
  TeamRemoveUser,
  DoubtDetailsScreen,
} from "./ui/screens";
import { UserTeam } from "./ui/screens/user-team/user-team.screen";

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<LoginScreen />} />

        <Route
          path="/messages"
          element={
            <PrivateRoute>
              <MyMessages />
            </PrivateRoute>
          }
        />

        <Route
          path="/userTeam"
          element={
            <PrivateRoute>
              <UserTeam />
            </PrivateRoute>
          }
        />

        <Route
          path="/appovementCourses"
          element={
            <PrivateRouteManager>
              <PendentCoursesScreen />
            </PrivateRouteManager>
          }
        />

        <Route
          path="/myCourses"
          element={
            <PrivateRoute>
              <MyCoursesScreen />
            </PrivateRoute>
          }
        />

        <Route
          path="/courses"
          element={
            <PrivateRoute>
              <CoursesScreen />
            </PrivateRoute>
          }
        />

        <Route
          path="/course-details/:courseId"
          element={
            <PrivateRoute>
              <DetailedCourse />
            </PrivateRoute>
          }
        />

        <Route
          path="/pendentCourses"
          element={
            <PrivateRoute>
              <PendentCoursesScreen />
            </PrivateRoute>
          }
        />

        {/* <Route
          path="/team-courses-management"
          element={
            <PrivateRoute>
              <AddCoursesScreen />
            </PrivateRoute>
          }
        /> */}

        <Route
          path="/evaluations/:videoId"
          element={
            <PrivateRoute>
              <EvaluationsScreen />
            </PrivateRoute>
          }
        />

        <Route
          path="/roadmap-active"
          element={
            <PrivateRoute>
              <DetailedTeam />
            </PrivateRoute>
          }
        />

        <Route
          path="/forum"
          element={
            <PrivateRoute>
              <ForumScreen />
            </PrivateRoute>
          }
        />

        <Route
          path="/comunity-videos"
          element={
            <PrivateRoute>
              <VideosScreen />
            </PrivateRoute>
          }
        />

        <Route
          path="/roadmap-active-manager"
          element={
            <PrivateRouteManager>
              <ManageDetailedTeam />
            </PrivateRouteManager>
          }
        />

        <Route
          path="/include-user"
          element={
            <PrivateRouteManager>
              <TeamIncludeUser />
            </PrivateRouteManager>
          }
        />

        <Route
          path="/remove-user"
          element={
            <PrivateRouteManager>
              <TeamRemoveUser />
            </PrivateRouteManager>
          }
        />

        <Route
          path="/doubt-details/:doubtId"
          element={
            <PrivateRoute>
              <DoubtDetailsScreen />
            </PrivateRoute>
          }
        />
      </Routes>
    </>
  );
};

export default App;
