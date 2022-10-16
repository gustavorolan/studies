import React from "react";
import { tradutorCasasMagicas } from "../../funcoesJS/tradutorCasasMagicas";
import "./listaDePessoasDaCasaMagica.style.css";

export function ListaDePessoasDaCasaMagica(props) {
  const{ nomeCasa, arrayNomes,handleClickSorteio,handleClickTrocaDeCasa,casaColor}=props

   return (
    <div className={`listaDeCasasContainer ${casaColor}ButtonColor `}>
    <button className={`${casaColor}ButtonColor `}  onClick={handleClickSorteio}><h1>{tradutorCasasMagicas(nomeCasa)}</h1></button>
      <div className="listaDeNomesContainer">
        {
        arrayNomes.map(( nome ) => {
           return <button onClick={() => handleClickTrocaDeCasa(nome,nomeCasa)}><p>{nome}</p></button>;
        })}
      </div>
    </div>
  );
}

export default ListaDePessoasDaCasaMagica;
