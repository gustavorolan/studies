import { TOAST_TIME } from "./constants";

export const setToastWithTimout = (set,response,emoji) => {
  const toast = {message:response, emoji:emoji}
  set(toast)
  setTimeout(() => {
    set(null);
  }, TOAST_TIME);
};
