import createGlobalState from "react-create-global-state";

const AUTH_KEY = "user";

const initialState = {
  token: "",
};

const stringifyUser = localStorage.getItem(AUTH_KEY);
const user = JSON.parse(stringifyUser) || initialState;

const [_useGlobalUser, UserGlobalProvider] = createGlobalState(user);

const useGlobalUser = () => {
  const [globalUser, _setGlobalUser] = _useGlobalUser();

  const setState = (value) => {
    localStorage.setItem(AUTH_KEY, JSON.stringify(value));
    _setGlobalUser(value);
  };

  return [globalUser, setState];
};

export { useGlobalUser, UserGlobalProvider };
