import "../style/listaContainer.style.css"
import arrayDeFilmes from "./arrayDeFilmes.json"
import { FilmeContainer } from "./filmeContainer.components"
export const ListaContainer = () =>{
  return(<div className="containerPrincipal"><div className="listaContainer">
    {arrayDeFilmes.map(({name,description,imageUrl,type},index)=> <FilmeContainer key={index} name={name} description={description} imageUrl={imageUrl}  type={type}/>)}
   </div></div>)
}