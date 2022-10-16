import React from "react";
import "./toastError.style.css";
export const ToastError = ({ children }) => {
  return (
    <div className="toastErrorContainer">
      <div className="toastError">
        {/* <p>x</p> */}
        {children}
      </div>
    </div>
  );
};
