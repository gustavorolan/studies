import "./quests-details.style.css";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useQuestsApi } from "../../../../hooks/api/quests/use-quests-api.hook";
import { Button, Loader, ToastError } from "../../../components";
import { useGlobalCharacter } from "../../../../context";
import { useCharactersApi } from "../../../../hooks/api";

export const QuestsDetails = () => {
  const [selectedQuest, setSelectedQuest] = useState({
    id: 0,
    description: "",
    image: "",
    duration: 0,
    experience: 0,
    money: 0,
  });
  const [character, setCharacter] = useGlobalCharacter();
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const questsApi = useQuestsApi();
  const characterApi = useCharactersApi();
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (character.questInProgress) {
      navigate("/quests");
    }
  }, [character, navigate]);

  useEffect(() => {
    const getQuest = async () => {
      setLoading(true);
      const quest = await questsApi.getQuestById(id);
      setSelectedQuest(quest);
      setLoading(false);
    };

    getQuest();
  }, [id, questsApi]);

  const handleBackToQuests = () => {
    navigate("/quests");
  };

  const handleStartQuest = async () => {
    try {
      await questsApi.startQuest(character.id, id);
      const updatedCharacter = await characterApi.getCharacterById(
        character.id
      );

      setCharacter({ id: character.id, ...updatedCharacter });
      navigate("/quests");
    } catch (error) {
      setError(error.response.data.message);
    }
  };

  const handleCloseToast = () => {
    setError(null);
  };

  return (
    <div className="quests-details__content">
      <div className="quests-details__header">
        <Button onClick={handleBackToQuests}>Voltar para missões</Button>
      </div>
      {loading ? (
        <div className="quests-details__loader">
          <Loader />
        </div>
      ) : (
        <div className="quests-details__card">
          <img
            className="quests-details__image"
            src={selectedQuest.image}
            alt="Quest"
          />
          <h2 className="quests-details__description">
            {selectedQuest.description}
          </h2>
          <p>Tempo de duração: {selectedQuest.duration / 1000} segundos</p>
          <p>Experiência: {selectedQuest.experience}</p>
          <p>Dinheiro: {selectedQuest.money}</p>
          <Button onClick={handleStartQuest}>Iniciar</Button>
          {error && (
            <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
          )}
        </div>
      )}
    </div>
  );
};
