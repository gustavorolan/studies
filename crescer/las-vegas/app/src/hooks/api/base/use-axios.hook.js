import axios from "axios";

export const useAxios = (baseUrl, headers) => {
  return axios.create({
    baseURL: baseUrl,
    headers,
  });
};
