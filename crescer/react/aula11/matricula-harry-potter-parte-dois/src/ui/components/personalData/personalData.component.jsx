import React from "react";
import animals from "../../../assets/jsonArchives/animals.json";
import { useGlobalUser } from "../../../contexts";
import "./personalData.style.css";

export const PersonalData = ({ handleChange, handleNextOne }) => {
  const [personalData] = useGlobalUser();
  const onChange = (Event) => {
    handleChange(Event.target);
  };

  const onClick = () => {
    handleNextOne();
  };

  return (
    <div className="mainContainer">
      <h1>Dados Pessoais</h1>
      <form>
        <div className="fullName">
          <label htmlFor="fullName">Nome Completo</label>
          <input onChange={onChange} type="text" name="fullName" placeholder={personalData.fullName} />
        </div>

        <div className="AdressAndNumber">
          <div className="adress">
            <label htmlFor="adress">Endereço para cartas</label>
            <input onChange={onChange} type="text" name="adress" placeholder={personalData.adress} />
          </div>

          <div className="adressNumber">
            <label htmlFor="adressNumber">Nº</label>
            <input onChange={onChange} type="text" name="adressNumber" placeholder={personalData.adressNumber} />
          </div>
        </div>

        <div className="petAndBlood">
          <div className="pet">
            <label htmlFor="animals">Animal de Estimação</label>

            <select onChange={onChange} name="animals" htmlFor="animals">
              <option defaultValue={personalData.animals ? personalData.animals : "selecione"} name="animals">
                {personalData.animals ? personalData.animals : "selecione"}
              </option>
              {animals.map(({ nome, id }) => {
                return (
                  <option key={id} name="animals" value={nome}>
                    {nome}
                  </option>
                );
              })}
            </select>
          </div>

          <div className="bloodType" onChange={onChange}>
            <input defaultChecked={personalData.bloodType === "Pure-blood"} type="radio" name="bloodType" value="Pure-blood" />
            <label htmlFor="bloodType">Pure-blood</label>
            <input defaultChecked={personalData.bloodType === "Half-blood"} type="radio" name="bloodType" value="Half-blood" />
            <label htmlFor="bloodType">Half-blood</label>
          </div>
        </div>
      </form>

      <button onClick={onClick}> Proximo</button>
    </div>
  );
};
