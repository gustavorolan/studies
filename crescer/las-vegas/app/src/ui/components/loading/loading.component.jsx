import React from "react";
import "./loading.style.css";
import loading from "../../../assets/image/loading.png";

export const Loading = () => {
  return <img className="rotating" src={loading} alt="loading" />;
};
