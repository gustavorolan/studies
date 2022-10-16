import './App.css';
import { CharactersListToChoose } from './ui/screens';
import titulo from './assets/images/titulo.svg'


function App() {
  return (
    <div className="App">
    <div className='logoTitle'>
     <img src={titulo} alt="logo digimon"  />
    </div>
    <CharactersListToChoose/>
    </div>
  );
}

export default App;
