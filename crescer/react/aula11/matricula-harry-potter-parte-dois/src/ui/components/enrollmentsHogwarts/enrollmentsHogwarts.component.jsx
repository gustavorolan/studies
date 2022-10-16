import React from "react";

export const EnrollmentsHogwarts = ({ handleChange, handleClick }) => {
  const onChange = (Event) => {
    handleChange(Event.target);
  };
  const onClick = () => {
    handleClick();
  };
  return (
    <div className="mainContainer EnrollmentsHogwarts">
      <h1>Palavra Passe</h1>
      <input type="password" name="keyWord" onChange={onChange} />
      <button onClick={onClick}>Continuar</button>
    </div>
  );
};
