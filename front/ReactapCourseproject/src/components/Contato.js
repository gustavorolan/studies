import React from "react";
import Styles from "./Contato.module.css";
import foto from "../img/contato.jpg";
import Head from "./Head";

const Contato = () => {
  return (
    // eslint-disable-next-line no-useless-concat
    <section className={Styles.Contato + " " + "animeLeft"}>
      <Head title="Contato" description="Entre em Contato" />
      <img src={foto} alt="" />
      <div>
        <h1>Entre em Contato</h1>
        <ul className={Styles.Dados}>
          <li>gustavorolan@hotmail.com</li>
          <li>53999455027</li>
          <li>Rua Saldanha Marinho, 173</li>
        </ul>
      </div>
    </section>
  );
};

export default Contato;
