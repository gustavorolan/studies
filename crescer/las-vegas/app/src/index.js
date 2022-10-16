import React from "react";
import ReactDOM from "react-dom";
import App from "./app";
import { GlobalUserProvider } from "./context";
import { BrowserRouter } from "react-router-dom";

import "./index.css";

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
