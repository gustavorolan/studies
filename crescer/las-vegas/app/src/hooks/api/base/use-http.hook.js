import { useAxios } from "./use-axios.hook";

export const useHttp = (baseURL, headers) => {
  const instance = useAxios(baseURL, headers);

  const get = async (url, headers) => {
    const response = await instance.get(url, headers);
    return response.data;
  };

  const post = async (url, data, headers) => {
    return await instance.post(url, data, headers);
  };

  const put = async (url, data, headers) => {
    return await instance.put(url, data, headers);
  };

  const deleteReq = async (url, data, headers) => {
    return await instance.delete(url, data, headers);
  };

  async function request(config) {
    return await instance.request(config);
  }

  return {
    get,
    post,
    put,
    deleteReq,
    request,
  };
};
