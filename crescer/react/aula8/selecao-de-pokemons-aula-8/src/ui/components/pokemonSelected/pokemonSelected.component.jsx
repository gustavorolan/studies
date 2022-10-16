import './pokemonSelected.style.css'
import React from 'react'

export const PokemonSelected = (props) => {
  const {name,image,handleClickUndo} = props
  return (
    <div className='pokemonSelected'>
    <h1>{`Voce escolheu ${name}`}</h1>
    <img src={image} alt={`foto do pokemon ${name}`}  />
    <button onClick={()=> handleClickUndo()}>Selecionar Novamente</button>
    </div>
  )
}

