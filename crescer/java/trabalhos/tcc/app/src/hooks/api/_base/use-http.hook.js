import { useGlobalUser } from "../../../contexts/user.context";
import { useAxios } from "./use-axios.hook";

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers);
  const [, setUser] = useGlobalUser();

  async function get(url, headers) {
    try {
      const response = await instance.get(url, headers);

      return response;
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
        throw error;
      }
    }
  }

  async function request(config) {
    const response = await instance.request(config);
    return response;
  }

  async function post(url, data, headers) {
    const response = await instance.post(url, headers, data);
    return response;
  }

  async function put(url, headers, data) {
    const response = await instance.put(url, headers, data);
    return response;
  }

  return {
    get,
    post,
    put,
    request,
  };
}
