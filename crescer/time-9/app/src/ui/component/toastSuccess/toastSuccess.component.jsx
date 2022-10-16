import React from "react";
import "./toastSuccess.style.css";
export const ToastSuccess = ({ children }) => {
  return (
    <div className="toastSuccessContainer">
      <div className="toastSuccess">
        <p>:)</p>
        {children}
      </div>
    </div>
  );
};
