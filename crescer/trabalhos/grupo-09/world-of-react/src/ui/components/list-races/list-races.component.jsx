import "./list-races.style.css";

export const ListRaces = ({ races, onChange, nameProp }) => {
  const handleChange = (event) => {
    onChange(event);
  };
  return (
    <div className="list-races__items">
      {races.map(
        ({
          id,
          name,
          baseLife,
          baseVigor,
          baseDamage,
          image,
          type,
          minLvl,
        }) => (
          <label className="list-races__label" key={id}>
            <input
              className="list-races__radio"
              type="radio"
              onChange={handleChange}
              value={id}
              name={nameProp}
            />
            <div className="list-races__info">
              <img
                className="list-races__img"
                src={image}
                alt={`Imagem do ${name}`}
              />
              <h4 className="list-races__race-name">{name}</h4>
              <p className="list-races__info-race">Vida base: {baseLife}</p>
              <p className="list-races__info-race">Dano base: {baseDamage}</p>
              <p className="list-races__info-race">Vigor base: {baseVigor}</p>
              <p className="list-races__info-race">Tipo: {type}</p>
              {minLvl && (
                <p className="list-races__info-race">Level Mínimo: {minLvl}</p>
              )}
            </div>
          </label>
        )
      )}
    </div>
  );
};

ListRaces.defaultProps = {
  races: [],
};
