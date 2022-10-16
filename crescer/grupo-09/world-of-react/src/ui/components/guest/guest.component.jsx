import { Navigate } from "react-router-dom";
import { useGlobalUser } from "../../../context";

export const Guest = ({ children }) => {
  const [user] = useGlobalUser();

  if (user.token) {
    return <Navigate to="/" />;
  } else {
    return children;
  }
};
