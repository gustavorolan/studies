import createGlobalState from "react-create-global-state";

const CHARACTER_KEY = "character";

const initialState = {
  token: "",
};

const stringifyCharacter = localStorage.getItem(CHARACTER_KEY);
const character = JSON.parse(stringifyCharacter) || initialState;

const [_useGlobalCharacter, CharacterGlobalProvider] = createGlobalState(character);

const useGlobalCharacter = () => {
  const [globalCharacter, _setGlobalCharacter] = _useGlobalCharacter();

  const setState = (value) => {
    localStorage.setItem(CHARACTER_KEY, JSON.stringify(value));
    _setGlobalCharacter(value);
  };

  return [globalCharacter, setState];
};

export { useGlobalCharacter, CharacterGlobalProvider };
