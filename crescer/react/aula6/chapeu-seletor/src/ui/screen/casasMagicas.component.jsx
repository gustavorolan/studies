import React, { useState } from 'react'
import { ListaDePessoasDaCasaMagica } from '../components'
import { sortear } from '../funcoesJS/sorteioFunction'
import listaDeNomes from'../../listaDeNomes.json'
import './casasMagicas.style.css'
import { trocaDeCasa } from '../funcoesJS/trocaDeCasa'
import hatImage from "../../imgs/hatImage.png"
function CasasMagicas() {
  const objectInicial ={
  grifinoria: ['Ainda não há nomes'],
  sonseria: ['Ainda não há nomes'],
  corvinal: ['Ainda não há nomes'],
  lufaLufa: ['Ainda não há nomes']
}
  const [casasMagicasComListaDeNomes, setCasasMagicasComListaDeNomes] = useState(objectInicial)
  const [buttonActive,setButtonActive] = useState(false)

  const handleClickTrocaDeCasa=( nomeInput, nomeCasaInput)=>{
   const arrayComTrocaDeCasa = trocaDeCasa (
      casasMagicasComListaDeNomes,
      nomeInput,
      nomeCasaInput
    )
    setButtonActive(!buttonActive)
    setTimeout(()=>{
      setButtonActive(buttonActive)
    },1000)
    setCasasMagicasComListaDeNomes(arrayComTrocaDeCasa)

  }

  const handleClickSorteio=()=>{
    setButtonActive(!buttonActive)
    setTimeout(()=>{
      setButtonActive(buttonActive)
    },1000)
    setCasasMagicasComListaDeNomes(sortear(listaDeNomes)) 
  }
  
  
  return (
    <div className='containerPrincipal'>
    <h1>Sorting Hat</h1>
   <div className='containerCasasENomes'> 
   <div className='casasMagicas'>
     <ListaDePessoasDaCasaMagica  casaColor='red' arrayNomes={casasMagicasComListaDeNomes.grifinoria} nomeCasa='grifinoria' handleClickSorteio={handleClickSorteio} handleClickTrocaDeCasa={handleClickTrocaDeCasa} />
     <ListaDePessoasDaCasaMagica casaColor='green' arrayNomes={casasMagicasComListaDeNomes.sonseria} nomeCasa='sonseria' handleClickSorteio={handleClickSorteio} handleClickTrocaDeCasa={handleClickTrocaDeCasa}/>
     <ListaDePessoasDaCasaMagica casaColor='yellow' arrayNomes={casasMagicasComListaDeNomes.corvinal}   nomeCasa='corvinal' handleClickSorteio={handleClickSorteio} handleClickTrocaDeCasa={handleClickTrocaDeCasa}/>
     <ListaDePessoasDaCasaMagica casaColor='blue' arrayNomes={casasMagicasComListaDeNomes.lufaLufa} nomeCasa='lufaLufa' handleClickSorteio={handleClickSorteio} handleClickTrocaDeCasa={handleClickTrocaDeCasa}/>
  </div>
  </div>
  <button className={`chapeuSeletor ${buttonActive?'chapeuSeletorIsOn':''}`}  onClick={handleClickSorteio}><img src={hatImage} alt="" srcset="" /></button>
  </div>
  )
}

export default CasasMagicas

