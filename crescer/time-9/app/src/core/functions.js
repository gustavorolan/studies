import { TOAST_ERROR_TIME } from "./constants";

export const timeOutNull = (set) => {
  setTimeout(() => {
    set(null);
  }, TOAST_ERROR_TIME);
};
