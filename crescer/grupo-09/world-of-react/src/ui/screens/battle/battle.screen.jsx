import "./battle.style.css";
import { Button, ListCharacters, Loader, RadioButton, ToastError } from "../../components";
import { useGlobalCharacter } from "../../../context";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCharactersApi } from "../../../hooks/api";

export const Battle = () => {
  const [characters, setCharacters] = useState();
  const [opponent, setOpponent] = useState({});
  const [battleHasStarted, setBattleHasStarted] = useState(false);
  const [battle, setBattle] = useState({});
  const [error, setError] = useState(null);
  const [battleLog, setBattleLog] = useState([]);

  const [globalCharacter, setGlobalCharacter] = useGlobalCharacter();
  const charactersApi = useCharactersApi();
  const navigate = useNavigate();

  useEffect(() => {
    async function getAllCharacters() {
      try {
        const charactersResponse = await charactersApi.getCharacters();
        const filterCharacters = charactersResponse.filter((character) => {
          return (
            character.id !== globalCharacter.id && character.busy === false
          );
        }).sort((a, b)=>{
          if (a.level < b.level) {
            return 1;
          }
          if (a.level > b.level) {
            return -1;
          }
          return 0;
        });
        setCharacters(filterCharacters);
      } catch (error) {
        setError(error?.response?.data.message);
      }
    }

    getAllCharacters();
  }, [charactersApi]);

  const handleNavigate = () => {
    if (battleHasStarted) {
      setBattleHasStarted(false);
    } else {
      navigate("/");
    }
  };

  const handleCloseToast = () => {
    setError(null);
  };

  const handleSelectCharacter = async (opponentCharacter) => {
    try {
      const charactersResponse = await charactersApi.postBattle(
        globalCharacter.id,
        { opponentId: opponentCharacter.id }
      );
      const updatedCharacter = await charactersApi.getCharacterById(
        globalCharacter.id
      );
      setGlobalCharacter({ id: globalCharacter.id, ...updatedCharacter });
      setOpponent(opponentCharacter);
      setBattle(charactersResponse);
      setBattleHasStarted(true);
    } catch (error) {
      setError(error?.response?.data.message);
    }
  }

  useEffect(()=>{
    if(battleHasStarted){
      handleBattleLog()
    }
  },[battleHasStarted])

  const handleBattleLog = () => {
    const totalLife = {
      [globalCharacter.id]: globalCharacter.totalLife, 
      [opponent.id]: opponent.totalLife
    }

    const filteredBattleLog = battle?.battleLogs?.map((log)=>{
      const enemy = log.character.isOpponent ? globalCharacter : opponent
      return {...log, totalLife: totalLife[enemy.id]}
    }) 
    .reduce((acc, log, index)=>{

      const damage = log.damage < 0 ? 0 : log.damage
      const currentLife = acc[index-2]? acc[index-2].totalLife : log.totalLife
      const totalLife = currentLife - damage
      const parsedTotalLife = totalLife >= 0 ? parseFloat(totalLife.toFixed(2)) : 0

      return [...acc, {...log, totalLife: parsedTotalLife }]
    },[])

    setBattleLog(filteredBattleLog)
  }

  const DrawOrLog = () => {
    if (battle.draw) {
      return (
        <div className="battle__draw">
          <p>A batalha empatou!</p>
        </div>
      );
    }
    return (
      <div className="battle__battle-log">
        <p>{battle?.winner ? globalCharacter.name : opponent.name} venceu!</p>
        <div className="battle__characters-status">
          <div className="battle__your-character">
            <img src={globalCharacter.race.image} alt="" />
            <p className="battle__your-character-name">
              {globalCharacter.name}
            </p>
            <p>Dano: {globalCharacter.totalDamage}</p>
            <p>Vigor: {globalCharacter.totalVigor}</p>
            <p>Vida: {globalCharacter.totalLife}</p>
          </div>

          <div className="battle__opponent-character">
            <img src={opponent.race.image} alt="" />
            <p className="battle__opponent-character-name">{opponent.name}</p>
            <p>Dano: {opponent.totalDamage}</p>
            <p>Vigor: {opponent.totalVigor}</p>
            <p>Vida: {opponent.totalLife}</p>
          </div>
        </div>

        {battleLog?.map((log, index) => {
          return (
            <div
              className={`battle__log 
                ${log.character.isOpponent ? "battle__log-opponent" : ""}
                ${log.critical ? "battle__log-critical" : ""}`}
              key={index}
            >
              <p className="battle__log-status" >
                <span>{log.character.name}</span> atacou com {log.damage < 0 ? 0 : log.damage}{" "}
                de dano {log.critical ? "crítico!" : ""} deixando{" "}
                <span>{log.character.isOpponent ? globalCharacter.name : opponent.name}</span>{" "}
                com {log.totalLife} de vida.
              </p>
            </div>
          );
        })}
      </div>
    );
  };

  const CheckingCharacters = () => {
    if (characters?.length > 0 && !characters?.busy) {
      return (
        <div className="battle__select-opponent">
          <h1>Escolha o oponente</h1>
          <ListCharacters
            buttonVisible={false}
            characters={characters}
            handleSelectCharacter={handleSelectCharacter}
          />
        </div>
      );
    }
    return <p>Você não tem oponentes disponíveis</p>;
  };

  const handleFilterOpponents = (status) => {
    const filterCharacters = characters.filter((character) => {
      return (
        character.id !== globalCharacter.id && character.busy === false
      );
    }).sort((a, b)=>{
      if (a[status] < b[status]) {
        return 1;
      }
      if (a[status] > b[status]) {
        return -1;
      }
      return 0;
    });
    setCharacters(filterCharacters)
  }

  return (
    <div className="battle__content">
      {!battleHasStarted && characters?.length > 0 &&
        <>
          <h1>Batalhar</h1>
          <div className="battle__filter">
            <h2>Organizar por: </h2>
            <Button onClick={()=>handleFilterOpponents('level')}>Level</Button>
            <Button onClick={()=>handleFilterOpponents('totalDamage')}>Damage</Button>
            <Button onClick={()=>handleFilterOpponents('totalVigor')}>Vigor</Button>
            <Button onClick={()=>handleFilterOpponents('totalLife')}>Life</Button>
          </div>
        </>
      }
      {!battleHasStarted && (
        <div className="battle__selection">
          {!characters ? (
            <div className="battle__loader">
              <Loader />
            </div>
          ) : (
            <CheckingCharacters/>
          )}
        </div>
      )}
      {battleHasStarted && <DrawOrLog />}
      <Button onClick={handleNavigate}>Voltar</Button>
      {error && (
        <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
      )}
    </div>
  );
};
