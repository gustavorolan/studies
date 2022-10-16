import { Route, Routes } from "react-router-dom";
import "./App.css";

import { MovieListContainer, MovieDetailContainer, CreateMovieContainer } from "./ui/screens";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MovieListContainer />} />
        <Route path="/:id" element={<MovieDetailContainer />} />
        <Route path="/create" element={<CreateMovieContainer />} />
      </Routes>
    </div>
  );
}

export default App;
