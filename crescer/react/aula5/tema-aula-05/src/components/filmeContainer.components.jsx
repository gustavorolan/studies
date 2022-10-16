import "../style/filmeContainer.style.css"
export const FilmeContainer = (props) =>{
  const {nameUnknown,imageUrlUnknown,typeUnknown} = {
  nameUnknown: 'Desconhecido',
  imageUrlUnknown: 'https://cdn.neemo.com.br/uploads/settings_webdelivery/logo/1630/no-image.png',
  typeUnknown: '?',
}
  const {name,description,imageUrl,type} = props
  
  console.log(props?imageUrl:imageUrlUnknown)
  return(<div className="filmeContainer" >
    <img src={imageUrl?imageUrl:imageUrlUnknown} alt="" />
    <div className={`textContainer ${type}`}>
      <h1>{name?name:nameUnknown}</h1>
      <p>{name?description:''}</p>
      <span>{(type?type:typeUnknown).toUpperCase()}</span>
    </div>
  </div>)
}