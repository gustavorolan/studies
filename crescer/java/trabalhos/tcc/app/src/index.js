import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { GlobalUserProvider } from "./contexts/user.context";

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

