import "./buttonToSelect.style.css";
export const ButtonToSelect = ({
  handleClickChangeListAvailable,
  handleClickChangeListNotAvailable,
  handleClickToCreate,
}) => {
  return (
    <div className="buttonToSelectContainer">
      <button onClick={handleClickToCreate}>+</button>
      <button onClick={handleClickChangeListAvailable}>Disponiveis</button>
      <button onClick={handleClickChangeListNotAvailable}>Indisponiveis</button>
    </div>
  );
};
