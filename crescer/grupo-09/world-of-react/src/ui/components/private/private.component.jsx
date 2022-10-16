import { Navigate } from "react-router-dom";
import { useGlobalUser } from "../../../context";
import { Logout, CharacterDetails } from '../../components'

export const Private = ({ children }) => {
  const [user] = useGlobalUser();

  if (user.token) {
    return (
      <>
        <CharacterDetails/>
        <Logout>Sair</Logout>
        {children}
      </>
    )
  } else {
    return <Navigate to="/login" />;
  }
};
