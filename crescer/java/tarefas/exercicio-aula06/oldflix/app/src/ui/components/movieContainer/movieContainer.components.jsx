import React from "react";
import { Navigate, useNavigate } from "react-router-dom";
import "./movieContainer.style.css";
export const MovieContainer = ({ id, name, description, imageUrl, type, itAvailable, handleDevolution, handleRent }) => {
  const { nameUnknown, imageUrlUnknown, typeUnknown } = {
    nameUnknown: "Desconhecido",
    imageUrlUnknown: "https://cdn.neemo.com.br/uploads/settings_webdelivery/logo/1630/no-image.png",
    typeUnknown: "?",
  };
  const [nameResponsible, setNameResponsible] = React.useState("");
  const navigate = useNavigate();
  const handleChange = (target) => {
    setNameResponsible(target.value);
  };
  const handleNavigate = () => {
    navigate(`/${id}`);
  };
  return (
    <div className="movieWithButton">
      <button onClick={handleNavigate}>
        <div className="movieContainer">
          <img src={imageUrl ? imageUrl : imageUrlUnknown} alt="" />
          <div className={`textContainer ${type}`}>
            <h1>{name ? name : nameUnknown}</h1>

            <span>{(type ? type : typeUnknown).toUpperCase()}</span>
          </div>
        </div>
      </button>
      <div className="devolutionAndRentButton">
        {itAvailable && (
          <button type="submit" onClick={() => handleRent(id, nameResponsible)}>
            Alugar
          </button>
        )}
        {!itAvailable && (
          <button type="submit" onClick={() => handleDevolution(id, nameResponsible)}>
            Devolver
          </button>
        )}
        <input type="text" onChange={(Event) => handleChange(Event.target)} />
      </div>
    </div>
  );
};
