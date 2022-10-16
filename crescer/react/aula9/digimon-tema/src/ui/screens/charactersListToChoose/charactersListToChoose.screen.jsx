import React from 'react'
import "./charactersListToChoose.style.css"
import { CharacterToChoose, ChosenCharactersOne, ChosenCharactersTwo } from '../../components'
import { useDigimonApi } from '../../../hooks'

export const CharactersListToChoose = () => {
  //const arrayCharactersComId = digimon.map((digimon,index)=>{
 // return {...digimon,id:index}
 //})
  const{getDigimon}= useDigimonApi()
  const [charactersArrayOne,setCharactersArrayOne] = React.useState([]);
  const [charactersArrayTwo,setCharactersArrayTwo] = React.useState([]);
  const [arrayDigimon,setArrayDigimon] = React.useState([])
  const [chosenCharacter,setChosenCharacter] = React.useState([])
  const [booleanControllerToDisable,setbooleanControllerToDisable ]=React.useState(false)
 

  React.useEffect(() => {
   async function getPokemonApi() {
      try {
        const {data} = await getDigimon()
        const digimonTreatedArray =  data.map((digimon, index) => {
          return {...digimon, id:index}
      })
        setArrayDigimon(digimonTreatedArray)
      } catch (error) {
        window.alert('Ocorreu um erro')
      }
    }
    getPokemonApi()
  },[])

 
  const handleConfirm=()=>{
     if(charactersArrayOne.length<3){ setCharactersArrayOne([...charactersArrayOne,chosenCharacter]);
      setTimeout(()=>{
      const chosenCharacterRandom = {...arrayDigimon[parseInt(Math.random()*arrayDigimon.length)]}
      setCharactersArrayTwo([...charactersArrayTwo,chosenCharacterRandom]);
  
      const newArrayDigimon2 = arrayDigimon.filter(character=> character.id!==chosenCharacter.id)
      setArrayDigimon(newArrayDigimon2)
     },100)}
     setbooleanControllerToDisable(false)
}
  const handleSelection = async (id) => {
  
   const chosenCharacterFind = {
      ...arrayDigimon.find((character) => character.id === id),
    };
    if(chosenCharacter.length<3 ){ setChosenCharacter([...chosenCharacter,chosenCharacterFind])
    const newArrayDigimon = arrayDigimon.filter(character=> character.id!==chosenCharacterFind.id)
    setArrayDigimon(newArrayDigimon)}
    setbooleanControllerToDisable(true)
  }

  const handleReset = ()=>{
  setCharactersArrayOne([])
  setCharactersArrayTwo([])
  setChosenCharacter([])
  setbooleanControllerToDisable(false)
}

 
  return (
  <div className='characterListToChooseContainer'>
 
    <div className='selectedCharacters selectedCharactersOne'>{chosenCharacter.length>0 && chosenCharacter.map(({img,name},id)=>{
      return <ChosenCharactersOne  key={id} img={img}  name={name} handleConfirm={handleConfirm} nowCharacter={chosenCharacter[chosenCharacter.length-1]} booleanControllerToDisableConfirm={booleanControllerToDisable}/>
    })}</div>
    
    <dir className='listContainer'>{arrayDigimon.map(({name,img,level,id})=>{
    return  <CharacterToChoose booleanControllerToDisable={booleanControllerToDisable} key={id} id={id}  name={name} img={img}  level={level}  handleSelection={handleSelection} />
    })}</dir>
   
    <div className='selectedCharacters selectedCharactersTwo'>{charactersArrayTwo.length>0 && charactersArrayTwo.map(({img,name},id)=>{
     return <ChosenCharactersTwo key={id} img={img} id={id} name={name}/>
    })}</div>
    <div className='buttonReset'><button onClick={handleReset}>Reset</button></div>
    </div>)
}

