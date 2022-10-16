import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useOldflixApi } from "../../../hooks";
import "./moviesDetailContainer.style.css";

export const MovieDetailContainer = () => {
  const { nameUnknown, imageUrlUnknown, typeUnknown } = {
    nameUnknown: "Desconhecido",
    imageUrlUnknown: "https://cdn.neemo.com.br/uploads/settings_webdelivery/logo/1630/no-image.png",
    typeUnknown: "?",
  };

  const { movieById } = useOldflixApi();
  const { id } = useParams();
  const [movie, setMovie] = React.useState([]);
  const [error, setError] = React.useState([]);
  const [nameResponsible, setNameResponsible] = React.useState("");
  const { deleteMovieById, devolutionMovieList, rentMovieList, editMovieList } = useOldflixApi();
  const navigate = useNavigate();
  const [edit, setEdit] = React.useState(false);
  const [editForm, setEditForm] = React.useState({});

  async function getMoviesApi() {
    try {
      const { data } = await movieById(id);
      setMovie(data);
    } catch (error) {
      setError("Ocorreu um erro");
    }
  }

  React.useEffect(() => {
    getMoviesApi();
  }, []);

  const handleChangeEdit = (Event) => {
    Event.preventDefault();
    setEditForm({ ...editForm, [Event.target.name]: Event.target.value });
  };

  const handleRent = async () => {
    try {
      await rentMovieList(id, nameResponsible);
      await getMoviesApi();
    } catch (error) {
      setError("Ocorreu um erro");
    }
  };

  const handleDevolution = async () => {
    try {
      await devolutionMovieList(id, nameResponsible);
      await getMoviesApi();
    } catch (error) {
      setError("Ocorreu um erro");
    }
  };

  const handleChange = (target) => {
    setNameResponsible(target.value);
  };

  const handleGoBack = () => {
    navigate("/");
  };
  const handleDelete = async () => {
    try {
      await deleteMovieById(id);
    } catch (error) {
      setError(error);
    }

    navigate("/");
  };
  const handleEdit = async () => {
    try {
      await editMovieList(id, editForm);
      await getMoviesApi();
    } catch (error) {
      setError(error);
    }
  };

  return (
    <div className="mainContainer">
      <div className="detailContainer">
        <div className="backButton">
          {" "}
          <button onClick={handleGoBack}>x</button>
        </div>

        <div className="imageContainerDetail">
          <img src={movie.imageUrl ? movie.imageUrl : imageUrlUnknown} alt="fotoFilme" />
        </div>
        <div className="imageContainerDetail">
          {movie.itAvailable&&<div className="editionButton" onClick={() => setEdit(!edit)}>
            <button>🖊️</button>
          </div>}
          <h1>{movie.title ? movie.title : nameUnknown} </h1>
          {edit && <input type="text" name="title" onChange={(Event) => handleChangeEdit(Event)} />}
          <h2>Descrição: {movie.desc}</h2>
          {edit && <input type="text" name="desc" onChange={(Event) => handleChangeEdit(Event)} />}
          <p>Categoria: {movie.category}</p>
          {edit && <input type="text" name="category" onChange={(Event) => handleChangeEdit(Event)} />}
          {movie.devolutionDate && <p>Devolução: {movie.devolutionDate}</p>}
          {movie.situation && <p>Situação: {movie.situation}</p>}
          {movie.rentResponsible && <p>Responsável: {movie.rentResponsible}</p>}
          <div className="devolutionAndRentButton">
            {movie.itAvailable && <button onClick={handleRent}>Alugar</button>}
            {!movie.itAvailable && <button onClick={handleDevolution}>Devolver</button>}
            <input type="text" onChange={(Event) => handleChange(Event.target)} />
          </div>
          {movie.itAvailable&& <div className="excludeButton">
            <button onClick={handleDelete}>Excluir</button>
            {edit && <button onClick={handleEdit}>Editar</button>}
          </div>}
        </div>
      </div>
    </div>
  );
};
