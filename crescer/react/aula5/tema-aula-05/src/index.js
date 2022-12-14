import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import { ListaContainer } from "./components/listaContainer.components";
import { Header } from "./components/header.components";
ReactDOM.render(
  <React.StrictMode>
    <Header />
    <ListaContainer />
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
