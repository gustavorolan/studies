import { useOldflixApi } from "../../../hooks";
import { ButtonToSelect, MovieContainer } from "../../components";
import "./moviesListContainer.style.css";
import React from "react";
import { useNavigate } from "react-router-dom";

export const MovieListContainer = () => {
  const { getMoviesList, devolutionMovieList, rentMovieList } = useOldflixApi();
  const [moviesList, setMoviesList] = React.useState([]);
  const [showList, setShowList] = React.useState([]);
  const [error, setError] = React.useState([]);
  const [availableMovies, setAvailableMovies] = React.useState(false);
  const navigate = useNavigate();

  async function getMoviesApi() {
    try {
      const { data } = await getMoviesList();
      const moviesToShow = data.filter((movie) => movie.itAvailable !== availableMovies);
      setMoviesList(data);
      setShowList(moviesToShow);
    } catch (error) {
      setError("Ocorreu um erro");
    }
  }

  React.useEffect(() => {
    getMoviesApi();
  }, []);

  const handleClickChangeListNotAvailable = () => {
    const movieListFiltered = moviesList.filter((movie) => movie.itAvailable !== true);
    setAvailableMovies(true);
    setShowList(movieListFiltered);
  };

  const handleClickChangeListAvailable = () => {
    const movieListFiltered = moviesList.filter((movie) => movie.itAvailable !== false);
    setAvailableMovies(true);
    setShowList(movieListFiltered);
  };

  const handleRent = async (id, nameResponsible) => {
    try {
      await rentMovieList(id, nameResponsible);
      await getMoviesApi();
    } catch (error) {
      setError(error);
    }
  };
  const handleDevolution = async (id, nameResponsible) => {
    try {
      await devolutionMovieList(id, nameResponsible);
      await getMoviesApi();
    } catch (error) {
      setError(error);
    }
  };

  const handleClickToCreate = () => {
    navigate("/create");
  };

  return (
    <div className="mainContainer">
      <ButtonToSelect handleClickChangeListNotAvailable={handleClickChangeListNotAvailable} handleClickChangeListAvailable={handleClickChangeListAvailable} handleClickToCreate={handleClickToCreate} />
      <div className="listContainer">
        {showList.map(({ id, title, desc, category, itAvailable, imageUrl }) => (
          <MovieContainer imageUrl={imageUrl} handleRent={handleRent} handleDevolution={handleDevolution} itAvailable={itAvailable} key={id} id={id} name={title} description={desc} type={category} />
        ))}
      </div>
    </div>
  );
};
