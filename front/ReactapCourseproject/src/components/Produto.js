import react from "react";
import React from "react";
import { useParams } from "react-router";
import Style from "./Produto.module.css";
import Head from "./Head";

const Produto = () => {
  const [produto, setProduto] = react.useState(null);
  const [error, setError] = react.useState(null);
  const { id } = useParams(null);
  react.useEffect(() => {
    async function fetchProduto(url) {
      try {
        const response = await fetch(url);
        const json = await response.json();
        setProduto(json);
      } catch (error) {
        setError("um erro ocrreu");
      }
    }
    fetchProduto(`https://ranekapi.origamid.dev/json/api/produto/${id}`);
  }, [id]);
  if (produto === null) return null;
  if (error) return <p>{error}</p>;
  return (
    <section className={Style.Produto + " animeLeft"}>
      <Head title={produto.nome} />
      <div className={Style.imgContent}>
        <div className={Style.img}>
          {produto.fotos.map((foto) => (
            <img key={foto.src} src={foto.src} alt={foto.titulo} />
          ))}
        </div>
      </div>
      <div>
        <div className={Style.namepreco}>
          <h1>{produto.nome}</h1>
          <p className={Style.preco}>R$ {produto.preco}</p>{" "}
        </div>
        <p className={Style.descricao}>{produto.descricao}</p>
      </div>
    </section>
  );
};
export default Produto;
