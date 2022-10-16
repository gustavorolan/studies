//allowed inputs ["Maria Silva", "Maria  Silva", "Maria silva", "maria Silva", "MariaSilva"]
export const NAMES = /[A-Z]?[a-z].* [A-Z]?[a-z].* ?[A-Z]?[a-z]./;
export const ONLY_NUMBER = /^[0-9]/;
export const LENGTH_MAX_NAME = 30;
export const LENGTH_MIN_NAME = 2;
export const LENGTH_MAX_ADRESS = 40;
export const LENGTH_MIN_ADRESS = 2;
export const LENGTH_MAX_NUMBER = 5;
export const LENGTH_MIN_NUMBER = 1;
export const PERSONAL_DATA_KEY = "personalDataKey";
export const pagesArray = ["PersonalData", "AcademicsData", "MattersChoicer", "AllDataOfProfile"];
export const ARRAY_KEYS_LOGIN = ["grifinoria", "corvinal", "lufa-lufa", "sonserina", "harrypotter"];
export const ARRAY_HOUSES_TO_RAFFLE = ["grifinoria", "corvinal", "lufa-lufa", "sonserina"];
export const KEYS = [parseInt(Math.random() * 100), parseInt(Math.random() * 100), parseInt(Math.random() * 100), parseInt(Math.random() * 100), parseInt(Math.random() * 100)];
