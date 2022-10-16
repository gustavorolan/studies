import './showPokemonsToChoose.style.css'
import React, { useEffect, useState } from 'react'
import { PokemonSelected, ShowPokemon } from '../../components'
import characters from '../../../assets/json/characters.json'
import { CHARACTER_SAVED_KEY } from '../../../constants/constants/constants'


export const ShowPokemonsToChoose = () => {
  const [selectedCharacter,setSelectedCharacter]=useState({})

  useEffect(()=>{
    const characterSaved = window.localStorage.getItem(CHARACTER_SAVED_KEY)
    if(characterSaved)setSelectedCharacter(JSON.parse(characterSaved))
    
  },[])

  const handleClickChoose=(id)=>{
    const selectedCharacterFromButton = characters.find(character=> character.id===id)
    window.localStorage.setItem(CHARACTER_SAVED_KEY,JSON.stringify({...selectedCharacterFromButton}))
    setSelectedCharacter({...selectedCharacterFromButton}) 
  }

  const handleClickUndo = ()=>{
    setSelectedCharacter({})
    window.localStorage.setItem(CHARACTER_SAVED_KEY,JSON.stringify({}))
  }

  return (
    <>
    {!selectedCharacter.id && (characters.map(({id,name,type,image})=>{
          return <ShowPokemon key={id} id={id} name={name} type={type} image={image} handleClickChoose={handleClickChoose} />
        }))}
       {!!selectedCharacter.id && (<PokemonSelected key={selectedCharacter.id} id={selectedCharacter.id} name={selectedCharacter.name} type={selectedCharacter.type} image={selectedCharacter.image} handleClickUndo={handleClickUndo}/>)}
    </>
    )
}

