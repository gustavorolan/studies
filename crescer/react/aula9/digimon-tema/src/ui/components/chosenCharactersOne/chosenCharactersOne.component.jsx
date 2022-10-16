import './chosenCharactersOne.style.css'

import React from 'react'

export const ChosenCharactersOne = ({img,name,handleConfirm,nowCharacter,booleanControllerToDisableConfirm}) => {

  if(booleanControllerToDisableConfirm){
    return (
    <div className='chosenCharactersOne'>
      <img src={img} alt="" />
      <p>{name}</p>
     {nowCharacter.name===name&&  <button  onClick={()=>handleConfirm()} >Confirmar</button>} 
  </div>
  )}
  else{
    return(
    <div className='chosenCharactersOne'>
      <img src={img} alt="" />
      <p>{name}</p>
     {nowCharacter.name===name&&  <button disabled onClick={()=>handleConfirm()} >Confirmar</button>} 
  </div>)
  }
}

