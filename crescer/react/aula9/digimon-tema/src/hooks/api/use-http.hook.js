import { useAxios } from "./use-axios.hook"

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers)

  async function get(url) {
    return instance.get(url)
  }

  return {
    get,
  }
}
