import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { GlobalUserProvider } from "./contexts";
import { BrowserRouter } from "react-router-dom";

ReactDOM.render(
  <React.StrictMode>
    <GlobalUserProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </GlobalUserProvider>
  </React.StrictMode>,
  document.getElementById("root")
);
