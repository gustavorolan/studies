import "./header.css";
import biluProject from "../../../assets/image/bilu-project.svg";
import { useEffect, useState } from "react";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import { useGlobalUser, DEFAULT_USER } from "../../../context";
import { ProfileImage } from "../profile-image/profile-image-component";
import { Link } from "react-router-dom";

export const HeaderComponent = () => {
  const [user, setUser] = useState("");
  const { getUserLogged } = useUserApi();

  const [, setGlobalUser] = useGlobalUser();

  useEffect(() => {
    const getUser = async () => {
      try {
        const response = await getUserLogged();
        setUser(response);
      } catch (error) {}
    };
    getUser();
  }, [getUserLogged]);

  const handleLogoutFunciton = () => {
    setGlobalUser(DEFAULT_USER);
  };

  return (
    <>
      <header className="header">
        <Link to="/userTeam">
          <img src={biluProject} alt="biluProject" />
        </Link>
        {user.permission === "MANAGER" && (
          <Link className="manager-button" to="/roadmap-active-manager">
            Manager{" "}
          </Link>
        )}

        <div className="user-and-logout-button">
          <div className="user-info">
            <ProfileImage user={user} />
            <h2>{user.fullName}</h2>
          </div>
        </div>

        <button className="header-logout-button" onClick={handleLogoutFunciton}>
          Logout
        </button>
      </header>
    </>
  );
};
