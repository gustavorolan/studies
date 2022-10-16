import './chosenCharactersTwo.style.css'

import React from 'react'

export const ChosenCharactersTwo = ({img,name}) => {
  return (
    <div className='chosenCharactersTwo'>
      <img src={img} alt="" />
      <p>{name}</p>
  </div>
  )
}

