import React from "react";
import "./toast.style.css";
export const Toast = ({ children, emoji }) => {
  return (
    <div className="toastContainer">
      <div className="toast">
        <p>{emoji}</p>
        {children}
      </div>
    </div>
  );
};
