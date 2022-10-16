import'./showPokemons.style.css'

import React from 'react'


export const ShowPokemon = (props) => {
  const {id,name,type,image,handleClickChoose} = props
  return (
    <div className={`showPokemon ${type}`}>
      <button  onClick={()=>handleClickChoose(id)}><img src={image} alt={`foto do pokemon ${name}`}  />
      <h1>{name}</h1>
      <h2>{type}</h2>
      </button>
    </div>
  )
}



