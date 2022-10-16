import React from "react";
import { useNavigate } from "react-router-dom";
import { CATEGORY_ARRAY } from "../../../constants/constants";
import { useOldflixApi } from "../../../hooks";
import "./createMovieContainer.style.css";

export const CreateMovieContainer = () => {
  const { includeMovieList } = useOldflixApi();
  const navigate = useNavigate();
  const [form, setForm] = React.useState({});
  const [error, setError] = React.useState([]);

  const handleChange = (Event) => {
    Event.preventDefault();
    setForm({ ...form, [Event.target.name]: Event.target.value });
  };

  const handleCreate = async () => {
    try {
      await includeMovieList(form.title, form.desc, form.category, form.imageUrl);
    } catch (error) {
      setError(error);
    }

    navigate("/");
  };
  const handleGoBack = () => {
    navigate("/");
  };

  return (
    <div className="mainContainer mainContainerCreate">
      <div className="createContainer" onChange={(Event) => handleChange(Event)}>
        <div className="backButton">
          {" "}
          <button onClick={handleGoBack}>x</button>
        </div>

        <div className="inputLabelCreate">
          <label htmlFor="title">Title</label>
          <input type="text" name="title" />
        </div>
        <div className="inputLabelCreate">
          <label htmlFor="desc">Descrição</label>
          <input type="text" name="desc" />
        </div>
        <div className="inputLabelCreate">
          <label htmlFor="">Imagem</label>
          <input type="text" name="imageUrl" />
        </div>

        <div className="inputLabelCreate">
          <label htmlFor="category">Categoria</label>
          <div className="buttonsCategory">
            {CATEGORY_ARRAY.map((element, index) => {
              return (
                <button className={element === form.category ? "selectedButton" : ""} onClick={(Event) => handleChange(Event)} key={index} type="radio" name="category" value={element}>
                  {element}
                </button>
              );
            })}
          </div>
        </div>

        <button onClick={handleCreate}>criar</button>
      </div>
    </div>
  );
};
