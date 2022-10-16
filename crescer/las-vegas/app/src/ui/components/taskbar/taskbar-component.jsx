import "./taskbar.css";
import React from "react";
import { Link } from "react-router-dom";
import teamIcon from "../../../assets/image/teamIcon.svg";
import chatIcon from "../../../assets/image/chatIcon.svg";
import colectionsIcon from "../../../assets/image/colectionsIcon.svg";
import roadsmapIcon from "../../../assets/image/roadsmapIcon.svg";
import forumIcon from "../../../assets/image/forumIcon.svg";

export const TaskBar = () => {
  return (
    <>
      <div className="taskbar">
        <>
          <Link to="/userTeam">
            <button className="taskbar-button">
              <img src={teamIcon} alt="teamIcon" />
              <h4>Meu time</h4>
            </button>
          </Link>
          <Link to="/messages">
            <button className="taskbar-button">
              <img src={chatIcon} alt="teamIcon" />
              <h4>Conversas</h4>
            </button>
          </Link>
          <Link to="/myCourses">
            <button className="taskbar-button">
              <img src={colectionsIcon} alt="colectionsIcon" />
              <h4>Cursos criados</h4>
            </button>
          </Link>

          <Link to={`/roadmap-active`}>
            <button className="taskbar-button">
              <img src={roadsmapIcon} alt="roadmapIcon" />
              <h4>Trilha</h4>
            </button>
          </Link>

          <Link to="/forum">
            <button className="taskbar-button">
              <img src={forumIcon} alt="forumIcon" />
              <h4>Forum</h4>
            </button>
          </Link>

          <Link to="/comunity-videos">
            <button className="taskbar-button">
              <img src={colectionsIcon} alt="colectionsIcon" />
              <h4>Vídeos</h4>
            </button>
          </Link>
        </>
      </div>
    </>
  );
};
