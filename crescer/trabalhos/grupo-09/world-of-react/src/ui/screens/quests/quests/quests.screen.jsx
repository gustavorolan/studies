import "./quests.style.css";
import { useEffect, useState } from "react";
import { useQuestsApi } from "../../../../hooks/api/quests/use-quests-api.hook";
import { useGlobalCharacter } from "../../../../context";
import { useNavigate } from "react-router-dom";
import { Button, Loader, ToastError } from "../../../components";
import { useCharactersApi } from "../../../../hooks/api";

const MESSAGE_QUEST = {
  IN_PROGRESS: "Você está em uma missão em andamento.",
  FINISHED: "Você finalizou a missão, clique abaixo para finalizar.",
};

const ListQuests = ({ quests, onSelectQuest, disabled }) => {
  const handleSelectQuest = (questId) => {
    onSelectQuest(questId);
  };

  return (
    <div className="list-quests__list">
      {quests.map(({ id, description, image }) => (
        <button
          disabled={disabled}
          onClick={() => handleSelectQuest(id)}
          key={id}
          className="list-quests__button"
        >
          <img className="list-quests__quest-image" src={image} alt={`Quest`} />
          <h3 className="list-quests__quest-description">{description}</h3>
        </button>
      ))}
    </div>
  );
};

export const Quests = () => {
  const [quests, setQuests] = useState([]);
  const [error, setError] = useState(null);
  const [character, setCharacter] = useGlobalCharacter();
  const [loading, setLoading] = useState(false);
  const charactersApi = useCharactersApi();
  const navigate = useNavigate();
  const questsApi = useQuestsApi();

  const handleSelectQuest = (questId) => {
    navigate(`/quests/${questId}`);
  };

  useEffect(() => {
    const getQuests = async () => {
      setLoading(true);
      const allQuests = await questsApi.getQuests();
      setQuests(allQuests);
      setLoading(false);
    };

    getQuests();
  }, [questsApi]);

  const handleFinishQuest = async () => {
    try {
      await questsApi.finishQuest(character.id);
      const updatedCharacter = await charactersApi.getCharacterById(
        character.id
      );
      setCharacter({ id: character.id, ...updatedCharacter });
    } catch (error) {
      setError(error.response.data.message);
    }
  };

  const handleCloseToast = () => {
    setError(null);
  };

  const handleBackToMenu = () => {
    navigate("/");
  };

  const disabledButtonFinishQuest = () => {
    const finishAt = character?.questInProgress?.finishAt;
    if (!finishAt) {
      return true;
    }
    const now = Date.now();
    return finishAt - now >= 0;
  };

  const isDisabledButton = disabledButtonFinishQuest();

  return (
    <div className="quests__content">
      <header className="quests__title-header">
        <h1 className="quests__title">Missões</h1>
      </header>
      {loading ? (
        <div className="quests__loader">
          <Loader />
        </div>
      ) : (
        <ListQuests
          disabled={character.questInProgress}
          quests={quests}
          onSelectQuest={handleSelectQuest}
        />
      )}

      {character.questInProgress && (
        <div className="quests__quest-in-progress">
          <h4>
            {isDisabledButton
              ? MESSAGE_QUEST.IN_PROGRESS
              : MESSAGE_QUEST.FINISHED}
          </h4>
          <Button isDisabled={isDisabledButton} onClick={handleFinishQuest}>
            Finalizar
          </Button>
        </div>
      )}
      <Button onClick={handleBackToMenu}>Voltar para menu</Button>
      {error && (
        <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
      )}
    </div>
  );
};
