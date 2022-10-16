import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { KEY_TYPE_SEARCH_STORE } from "../../../../constants";
import { useGlobalCharacter } from "../../../../context";
import {
  filterFunctionByTypes,
  functionFilter,
} from "../../../../functions/storeFunctions";
import { useCharactersApi, useUserApi } from "../../../../hooks/api";
import { useStoreApi } from "../../../../hooks/api/store/use-store-api.hook";
import {
  ButtonTypeChooser,
  Loader,
  ShowListItemToBuyAndSell,
  ToastError,
} from "../../../components";
import "./store.style.css";

export const Store = () => {
  const { getUserInformation } = useUserApi();
  const navigate = useNavigate();
  const { sellItemsStore, getItemsStore } = useStoreApi();
  const { getCharacterById } = useCharactersApi();
  const [character, setCharacter] = useGlobalCharacter();
  const [storeList, setStoreList] = useState([]);
  const [storeListUnfilterd, setStoreListUnfilterd] = useState([]);
  const [showList, setShowList] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setloading] = useState(true);
  useEffect(() => {
    const getStoreApi = async () => {
      try {
        const responseUser = await getUserInformation();
        const response = await getItemsStore();
        setloading(false);
        setStoreListUnfilterd(response);
        const responseFiltered = functionFilter(
          response,
          character,
          responseUser
        );
        const lastTypeSearch = window.localStorage.getItem(
          KEY_TYPE_SEARCH_STORE
        );
        filterFunctionByTypes(
          character,
          lastTypeSearch,
          setShowList,
          responseFiltered,
          responseUser,
          response
        );
        setStoreList(responseFiltered);
      } catch (error) {
        setError("Erro Inesperado");
      }
    };
    getStoreApi();

    return () => {
      setStoreList([]);
    };
  }, [character, getItemsStore, getUserInformation]);

  const handleClickFilterItems = async (targetEvent) => {
    try {
      const responseUser = await getUserInformation();
      filterFunctionByTypes(
        character,
        targetEvent.value,
        setShowList,
        storeList,
        responseUser,
        storeListUnfilterd
      );
    } catch (error) {
      setError(true);
      setTimeout(() => {
        setError(null);
      }, 2500);
    }
  };

  const handleClickDetails = (idItem) => {
    navigate(`/store/${idItem}`);
  };

  const handleSell = async (id) => {
    try {
      await sellItemsStore(id);
      const updated = await getCharacterById(character.id);
      setCharacter({ ...character, ...updated });
    } catch (error) {
      setError("Algo Inesperado Ocorreu");
    }
    setTimeout(() => {
      setError(null);
    }, 2500);
  };
  const handleClickToastError = () => {
    setError(null);
  };
  return (
    <div className="store__container">
      <div className="store__loja-title">
        <h1>Loja</h1>
      </div>
      <ButtonTypeChooser handleClick={handleClickFilterItems} />
      <div className="store__loader"> {loading && <Loader />}</div>
      <div>
        {!!error && (
          <ToastError
            errorMessage={error}
            onCloseToast={handleClickToastError}
          />
        )}
      </div>
      <div className="store__list-container">
        {!!showList &&
          showList.map(({ id, type, name, image, price, sellPrice }) => (
            <ShowListItemToBuyAndSell
              loading={loading}
              key={id}
              id={id}
              name={name}
              image={image}
              price={price}
              handleClickDetails={handleClickDetails}
              setError={setError}
              sellPrice={sellPrice}
              type={type}
              character={character}
              handleSell={handleSell}
              storeListUnfilterd={storeListUnfilterd}
            />
          ))}
      </div>
    </div>
  );
};
