import { KEY_TYPE_SEARCH_STORE } from "../constants";

export const functionFilter = (arrayItems, character) => {
  const arrayItemsFilteredByPrice = arrayItems.filter((item) => item.price <= character.money);
  const arrayFilteredByLvlMin = arrayItemsFilteredByPrice.filter((item) => (item.minLvl ? item.minLvl <= character.level : item));
  const arrayFilteredByIdExpansao = arrayFilteredByLvlMin.filter((item) => !item.expansionId);
  const characterItemsId = character.items.map(({ id }) => id);
  const arrayMyItens = arrayFilteredByIdExpansao.filter((itemStore) => !characterItemsId.includes(itemStore.id));
  return arrayMyItens;
};

export const filterFunctionByTypes = (character, inputValue, setShowList, storeList, userInformation, storeListUnfiltered) => {
  const idListMyItems = character.items.map(({ id }) => id);
  const expansions = storeListUnfiltered.filter((item) => userInformation.expansions.includes(item.expansionId) && item.type === "EXPANSAO");
  const expansionId = expansions.map(({ id }) => id);
  const expansionsToSell = storeListUnfiltered.filter((item) => !expansionId.includes(item.id) && item.type === "EXPANSAO");
  const expansionsToSellIfIHaveMoney = expansionsToSell.filter((item) => item.price <= character.money);
  const expansionsToSellIfIMinLevel = expansionsToSellIfIHaveMoney.filter((item) => (item.minLvl ? item.minLvl <= character.level : item));

  if ("LISTA" === inputValue) {
    setShowList(storeList);
    window.localStorage.setItem(KEY_TYPE_SEARCH_STORE, "LISTA");
  } else if ("MY_ITEMS" === inputValue) {
    setShowList(character.items);
    window.localStorage.setItem(KEY_TYPE_SEARCH_STORE, "MY_ITEMS");
  } else if ("MY_EXP" === inputValue) {
    setShowList(expansions);
    window.localStorage.setItem(KEY_TYPE_SEARCH_STORE, "MY_ITEMS");
  } else if ("EXPANSAO" === inputValue) {
    setShowList(expansionsToSellIfIMinLevel);
  } else {
    const arrayFilteredByType = storeList.filter((item) => item.type === inputValue && !idListMyItems.includes(item.id));
    setShowList(arrayFilteredByType);
    window.localStorage.setItem(KEY_TYPE_SEARCH_STORE, inputValue);
  }
};
