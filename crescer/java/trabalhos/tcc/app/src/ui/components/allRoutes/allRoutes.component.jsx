import React from "react";
import { Routes, Route } from "react-router-dom";
import { FriendsToAccept, Home, RequestPeople } from "../../screen";
import { FriendsList } from "../../screen/friendsList/friendsList.screen";
import { Login } from "../../screen/login/login.screen";
import Profile from "../../screen/profile/profile.screen";
import { SingUp } from "../../screen/signUp/signUp.screen";
import { ProtectedRoute } from "../protectedRoute/protectedRoute.component";

export const AllRoutes = ({setToast}) => {
  return (
    <Routes>
      <Route path="/signUp" element={<SingUp setToast={setToast}/>} />
      <Route path="/login"  element={<Login setToast={setToast}/>} />
      <Route path="/" element={<Login setToast={setToast}/>} />
      <Route path="/home" element={<ProtectedRoute />}>
        <Route path="/home" element={<Home setToast={setToast}/>} />
      </Route>
      <Route path="/friends/friendsList" element={<ProtectedRoute />}>
        <Route path="/friends/friendsList" element={<FriendsList setToast={setToast}/>} />
      </Route>
      <Route path="/friends/friendsToAccept" element={<ProtectedRoute />}>
        <Route path="/friends/friendsToAccept" element={<FriendsToAccept setToast={setToast}/>} />
      </Route>
      <Route path="/friends/inviteToBeFriend" element={<ProtectedRoute />}>
        <Route path="/friends/inviteToBeFriend" element={<RequestPeople setToast={setToast} />} />
      </Route>
      <Route path="/profile/:nickname" element={<ProtectedRoute />}>
        <Route path="/profile/:nickname" element={<Profile setToast={setToast} />} />
      </Route>
    </Routes>
  );
};
