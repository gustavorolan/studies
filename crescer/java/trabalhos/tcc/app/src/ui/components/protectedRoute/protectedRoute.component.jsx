import React from "react";
import { Outlet, Navigate } from "react-router-dom";
import { useGlobalUser } from "../../../contexts/user.context";

export const ProtectedRoute = () => {
  const [user] = useGlobalUser();
  if (user.token) {
    return <Outlet />;
  } else {
    return <Navigate to="/login" />;
  }
};
