import "./select-character.css";

import { useNavigate } from "react-router-dom";
import { useCharactersApi } from "../../../hooks/api";
import { useEffect, useState } from "react";
import { useGlobalCharacter } from "../../../context";
import { Button, Modal, ListCharacters, Loader } from "../../components";

export const SelectCharacter = () => {
  const [characters, setCharacters] = useState();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [deletedCharacter, setDeletedCharacter] = useState({});
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [, setGlobalCharacter] = useGlobalCharacter();
  const navigate = useNavigate();
  const charactersApi = useCharactersApi();

  useEffect(() => {
    async function getAllCharacters() {
      setLoading(true)
      const charactersResponse = await charactersApi.getCharacters();
      setCharacters(charactersResponse);
      setLoading(false)
    }

    getAllCharacters();
  }, [charactersApi]);

  const handleSelectCharacter = (character) => {
    if (character) {
      setGlobalCharacter({ ...character });
      navigate("/");
    }
  };

  const handleConfirmDelete = async () => {
    setError(null);
    try {
      await charactersApi.postDeleteCharacter(deletedCharacter.id);
      const updatedCharacters = characters.filter(
        (character) => character.id !== deletedCharacter.id
      );

      setCharacters(updatedCharacters);
      setDeletedCharacter({});
      setIsModalOpen(false);
    } catch (error) {
      const { message } = error?.response?.data;
      setError(message);
    }
  };

  const openCloseModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const onDeleteCharacter = (characterId) => {
    const findCharacter = characters.find(
      (character) => character.id === characterId
    );
    setDeletedCharacter(findCharacter);
    openCloseModal();
  };

  const handleBackToMenu = () => {
    navigate("/");
  };

  const CheckingCharacters = () => {
    if (characters?.length > 0) {
      return (
        <ListCharacters
          buttonVisible={true}
          characters={characters}
          handleSelectCharacter={handleSelectCharacter}
          onDeleteCharacter={onDeleteCharacter}
        />
      );
    }
    return <p className="selectCharacter__no-characters">Você não tem personagens</p>;
  };

  return (
    <div className="selectCharacter__content">
      <Modal
        description="Caso voce queira realmente deletar o personagem basta confirmar."
        isOpen={isModalOpen}
        onConfirm={handleConfirmDelete}
        onOpen={openCloseModal}
        errorMessage={error}
        title={`Voce realmente deseja excluir ${deletedCharacter?.name}?`}
      />
      {!loading ? (
       <CheckingCharacters/>
      ) : (
        <div className="selectCharacter__loader">
          <Loader />
        </div>
      )}
      <Button onClick={handleBackToMenu}>Voltar para menu</Button>
    </div>
  );
};
