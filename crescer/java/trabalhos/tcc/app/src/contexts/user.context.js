import createGlobalState from "react-create-global-state";

const USER_KEY = "user";

const localStorageUser = localStorage?.getItem(USER_KEY);
const initialUser = localStorageUser ? JSON.parse(localStorageUser) : {};

const [_useGlobalUser, GlobalUserProvider] = createGlobalState(initialUser);

const useGlobalUser = () => {
  const [globalUser, _setGlobalUser] = _useGlobalUser();

  const setState = (valor) => {
    localStorage.setItem(USER_KEY, JSON.stringify(valor));
    _setGlobalUser(valor);
  };

  return [globalUser, setState];
};

export { useGlobalUser, GlobalUserProvider };
