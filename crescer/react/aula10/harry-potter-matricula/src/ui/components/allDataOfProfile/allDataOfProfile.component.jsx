import React from "react";
import "./allDataOfProfile.style.css";

export const AllDataOfProfile = ({ fullName, animals, adress, adressNumber, house, classes, schoolYear }) => {
  const [mattersArrayFilterd, setMattersArrayFilterd] = React.useState(classes);
  const handleFilter = (Event) => {
    if (Event.target.value.length === 0) {
      setMattersArrayFilterd(classes);
    } else {
      const arrayFilterd = classes.filter((classChosen) => classChosen.toLowerCase() === Event.target.value.toLowerCase());
      setMattersArrayFilterd(arrayFilterd);
    }
  };

  return (
    <div className="mainContainer">
      <div className="personalData">
        <div className="nameProfile">
          <h1>{fullName}</h1>
          <p>{animals}</p>
          <p>
            {adress},nº{adressNumber}
          </p>
        </div>

        <div className="schoolYearChosen">
          <h2> Nº{schoolYear}</h2>
        </div>
        <div className="houseSelecionada">
          <h3>{house}</h3>
        </div>
      </div>

      <div className="inputBotton">
        <input onChange={handleFilter} type="text" placeholder="Insira aqui a materia para ser pesquisada" />
      </div>

      <h1>Aulas</h1>

      <div className="listaDeClassesSelecionadas">
        {mattersArrayFilterd.map((classe) => (
          <span>{classe}</span>
        ))}
      </div>
    </div>
  );
};

export default AllDataOfProfile;
