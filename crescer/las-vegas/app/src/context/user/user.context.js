import createGlobalState from "react-create-global-state";

const STORAGE_USER = "currentUser";
export const DEFAULT_USER = {
  token: "",
  id: null,
};

export const getLocalStorage = (localStorageKey, defaultValue) => {
  const localStorageValue = localStorage.getItem(localStorageKey);
  return localStorageValue ? JSON.parse(localStorageValue) : defaultValue;
};

export const setLocalStorage = (localStorageKey, value) => {
  localStorage.setItem(localStorageKey, JSON.stringify(value));
};

const initialUser = getLocalStorage(STORAGE_USER, DEFAULT_USER);
const [_useGlobalUser, GlobalUserProvider] = createGlobalState(initialUser);

const useGlobalUser = () => {
  const [globalUser, setGlobalUser] = _useGlobalUser();

  const setState = (value) => {
    setLocalStorage(STORAGE_USER, value);
    setGlobalUser(value);
  };

  return [globalUser, setState];
};

export { useGlobalUser, GlobalUserProvider };
