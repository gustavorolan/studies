import { useHttp } from "../use-http.hook";

export const useOldflixApi=()=> {
  const http = useHttp("http://localhost:8081/movies");

  const getMoviesList = () => {
    return http.get("/");
  }

  const includeMovieList = (title,desc,category,imageUrl) => {
    return http.post("/", 
                      {title:title,
                      desc:desc,
                      category:category,
                      imageUrl:imageUrl
                    }
                   );
  }

  const rentMovieList = (id,responsible) => {
    return http.put(`/${id}/alugar`, 
                      {
                        rentResponsible:responsible
                      }
                   );
  }

  const devolutionMovieList = (id,responsible) => {
    return http.put(`/${id}/devolver`, 
                      {
                        rentResponsible:responsible
                      }
                   );
  }

  const editMovieList = (id,{title,desc,category}) => {
    return http.put(`/${id}`, 
                      {
                         title:title,
        desc:desc,
        category:category
                        
                      }
                   );
  }

  const movieById =(id)=>{
    return http.get(`/${id}`);

  }
  const deleteMovieById=(id)=>{
    return http.deleteMethod(`/${id}`);
  }


  return {
    getMoviesList,
    includeMovieList,
    rentMovieList,
    devolutionMovieList,
    movieById,
    deleteMovieById,
    editMovieList
  };
}
