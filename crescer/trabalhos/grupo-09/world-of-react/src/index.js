import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import './assets/styles/variables.css'
import App from './App';
import { BrowserRouter } from 'react-router-dom'
import { UserGlobalProvider, CharacterGlobalProvider  } from './context'

ReactDOM.render(
  <React.StrictMode>
    <UserGlobalProvider>
      <CharacterGlobalProvider>
        <BrowserRouter>
          <App /> 
        </BrowserRouter>
      </CharacterGlobalProvider>
    </UserGlobalProvider>
  </React.StrictMode>,
  document.getElementById('root')
);