import { Navigate } from "react-router-dom";
import { useGlobalUser } from "../../../context";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import React from "react";

export const PrivateRouteManager = ({ children }) => {
  const { getUserLogged } = useUserApi();
  const [user, setUser] = useGlobalUser();

  React.useEffect(() => {
    const getUserLoggedApi = async () => {
      try {
        await getUserLogged();
      } catch (e) {
        setUser({ token: null });
      }
    };
    getUserLoggedApi();
  }, []);

  if (user.token && user.permission === "MANAGER") {
    return <>{children}</>;
  } else {
    return <Navigate to="/" />;
  }
};
