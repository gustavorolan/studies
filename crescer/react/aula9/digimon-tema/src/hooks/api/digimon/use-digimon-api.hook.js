import { useHttp } from "../use-http.hook";

export function useDigimonApi() {
  const http = useHttp("https://digimon-api.vercel.app/");

  function getDigimon() {
    return http.get("api/digimon");
  }

  return {
    getDigimon,
  };
}
