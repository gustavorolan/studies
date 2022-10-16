import "./toaster.css";
import { useEffect } from "react";

export const Toaster = ({ message, type, closeToast }) => {
  useEffect(() => {
    const timeout = async () => {
      setTimeout(() => {
        closeToast();
      }, 5000);
    };
    timeout();
  }, [closeToast]);

  return (
    <>
      <div className={`toaster-card ${type}`}>
        <button onClick={closeToast} className="close-toast-button">
          X
        </button>
        <p>{message}</p>
      </div>
    </>
  );
};
