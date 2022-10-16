import React from 'react'
import './characterToChoose.style.css'
export const CharacterToChoose = (props) => {
  const {id,name,listingImage,handleSelection,characterOne,
  characterTwo,lastChosen} = props

  const isCharacterSelected=(id,characterOneId,characterTwoId)=>{
    const  charactersSelectedNamesArray = []
    if(characterOneId===id) charactersSelectedNamesArray.push('thisIsCharacterOne')
    if(characterTwoId===id) charactersSelectedNamesArray.push('thisIsCharacterTwo')
    return charactersSelectedNamesArray.join(' ')
  }

  return (
    <div className={`characterOptionContainer ${isCharacterSelected(id,characterOne.id,characterTwo.id)} ${lastChosen?'firsIsBeingChosen':'secondIsBeingChosen'}`}>
      <button onClick={()=>handleSelection(id)}><img src={listingImage} alt={name} /></button>
      
      </div>
  )
}

