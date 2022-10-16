import React from 'react'
import './characterToChoose.style.css'
export const CharacterToChoose = (props) => {
  const {name,img,handleSelection,id, booleanControllerToDisable} = props

if(booleanControllerToDisable){return (
  <div className={`characterOptionContainer`}>
    <button  disabled onClick={()=>handleSelection(id)}><img src={img} alt={name} /></button>
  </div>
)}
else{return (
  <div className={`characterOptionContainer`}>
    <button onClick={()=>handleSelection(id)}><img src={img} alt={name} /></button>
  </div>
)}

  
}

