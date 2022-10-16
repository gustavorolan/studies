import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Produtos from "./components/Produtos";
import Produto from "./components/Produto";
import Header from "./components/Header";
import Footer from "./components/Footer";
import "./App.css";
import Contato from "./components/Contato";

const App = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <div className="content">
          <Routes>
            <Route path="/" element={<Produtos />} />
            <Route path="contato" element={<Contato />} />
            <Route path="produto/:id" element={<Produto />} />
          </Routes>
        </div>
        <Footer />
      </BrowserRouter>
    </div>
  );
};

export default App;
