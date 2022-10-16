import React from "react";
import { NavLink } from "react-router-dom";
import Styles from "./Header.module.css";

const Header = () => {
  return (
    <nav className={Styles.header}>
      <ul>
        <li>
          <NavLink
            activeclassname={Styles.active}
            className={Styles.link}
            to="/"
            end
          >
            Home
          </NavLink>
        </li>
        <li>
          <NavLink
            activeclassname={Styles.active}
            className={Styles.link}
            to="contato"
          >
            contato
          </NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default Header;
