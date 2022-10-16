import React from "react";
import { Link } from "react-router-dom";

export const ManagerTaskBar = () => {
  return (
    <>
      <div className="taskbar">
        <>
          <Link to="/roadmap-active-manager">
            <button className="taskbar-button">
              <h1> Trilha</h1>
            </button>
          </Link>
          <Link to="/remove-user">
            <button className="taskbar-button">
              <h2>Meu Time</h2>
            </button>
          </Link>
          <Link to="/include-user">
            <button className="taskbar-button">
              <h2>Incluir</h2>
            </button>
          </Link>
          <Link to="/pendentCourses">
            <button className="taskbar-button">
              <h4>Aprovação de cursos</h4>
            </button>
          </Link>
        </>
      </div>
    </>
  );
};
