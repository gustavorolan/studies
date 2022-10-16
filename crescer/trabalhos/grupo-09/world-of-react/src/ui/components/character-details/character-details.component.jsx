import './character-details.style.css'
import { useGlobalCharacter } from '../../../context'
import { useState } from 'react'
import { Button } from '../button/button.component'

export const CharacterDetails = () => {

  const [globalCharacter] = useGlobalCharacter()
  const [showCharacter, setShowCharacter] = useState(false)

  const {
    name,
    race,
    totalDamage,
    totalVigor,
    totalLife,
    level,
    money,
    experience,
    experienceToNextLevel,
    faction,
    busy,
    kills,
    deaths,
    items,
  } = globalCharacter

  return(
    <section className='charactersDetails__button'>
      { Object.keys(globalCharacter).length > 0 &&
        <Button 
          onClick={
            ()=>showCharacter?setShowCharacter(false):setShowCharacter(true)
          }
        >
          Mostrar Personagem
        </Button>
      }
      {!!showCharacter && Object.keys(globalCharacter).length > 0 &&
        <div className='charactersDetails__content'>
          <div>
          
            <div className='charactersDetails__character'>
              <p>{name}</p>
              <img src={race?.image} alt="Imagem personagem selecionado" />
            </div>
  
            <div className='charactersDetails__stats'>
              <p>Raça: {race?.name}</p>
              <p>Dano: {totalDamage}</p>
              <p>Vigor: {totalVigor}</p>
              <p>Vida: {totalLife}</p>
            </div>
  
          </div>
  
          <div className='charactersDetails__long-info'>
  
            <div className='charactersDetails__actions'>
              <p>Level: {level}</p>
              <p>Dinheiro: {money}</p>
              <p>Experiência: {experience}</p>
              <p>Exp. prox. nível: {experienceToNextLevel}</p>
            </div>
  
            <div className='charactersDetails__info'>
              <p>Facção: {faction}</p>
              <p>Ocupado: {busy?'Sim':'Não'}</p>
              <p>Kills: {kills}</p>
              <p>Deaths: {deaths}</p>
            </div>
          </div>
  
          {items?.length > 0 &&
            <div className='charactersDetails__itens'>
              <p>Inventário</p>
              {items?.map(item=>{
                return(
                  <div className='charactersDetails__itens-slot' key={item.id}>
                    <img title={`${item.name} +${item.enhancement} de ${item.type}`} src={item.image} alt="Imagem do item" />
                  </div>
                )
              })}
            </div>
          }
        </div>
      }
    </section>
  )
}