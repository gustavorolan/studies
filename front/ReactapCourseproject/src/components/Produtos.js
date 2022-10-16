import React from "react";
import Styles from "./Produtos.module.css";
import { Link } from "react-router-dom";
import Head from "./Head";

const Produtos = () => {
  const [produtos, setProdutos] = React.useState(null);
  React.useEffect(() => {
    fetch("https://ranekapi.origamid.dev/json/api/produto")
      .then((response) => response.json())
      .then((response) => setProdutos(response));
  }, []);
  if (produtos === null) return null;
  return (
    <section className={Styles.Produtos + " animeLeft"}>
      <Head title="Home" />
      {produtos.map((produto) => (
        <Link to={`produto/${produto.id}`} key={produto.id}>
          <img src={produto.fotos[0].src} alt="" />
          <h1 className={Styles.nome}>{produto.nome}</h1>
        </Link>
      ))}
    </section>
  );
};

export default Produtos;
