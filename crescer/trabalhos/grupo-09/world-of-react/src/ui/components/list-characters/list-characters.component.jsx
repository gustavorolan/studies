import './list-characters.style.css'
import { Button } from '../../components'

export const ListCharacters = ({ buttonVisible, characters, handleSelectCharacter, onDeleteCharacter }) => {

  return (
    <div className="listCharacters__character">
      {characters?.map((character) => {
        return (
          <div className="listCharacters__character-content" key={character.id}>
            <div className="listCharacters__character-details">
              <button 
                  onClick={()=>handleSelectCharacter(character)} 
                  className='listCharacters__button'
                >
                <img src={character.race.image} alt="" />
                <p className='listCharacters__character-name'>{character.name}</p>
                <p>Level: {character.level}</p>
                <p>Dano: {character.totalDamage}</p>
                <p>Vigor: {character.totalVigor}</p>
                <p>Life: {character.totalLife}</p>
                <p>$: {character.money}</p>
                <p>Ocupado: {character.busy?'Sim':'Não'}</p>
              </button>
            </div>
            {buttonVisible && 
              <Button onClick={() => onDeleteCharacter(character.id)}>
                Deletar
              </Button>
            }
          </div>
        );
      })}
    </div>
  )
}