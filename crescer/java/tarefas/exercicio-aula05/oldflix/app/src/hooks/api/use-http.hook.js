import { useAxios } from "./use-axios.hook";

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers);

  async function get(url) {
    return instance.get(url);
  }
  async function put(url, data) {
    return instance.put(url, data);
  }
  async function post(url, data) {
    return instance.post(url, data);
  }
  async function deleteMethod(url) {
    return instance.delete(url);
  }

  return {
    get,
    put,
    post,
    deleteMethod,
  };
}
