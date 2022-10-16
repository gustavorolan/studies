import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { X_TOAST } from "../../../core/constants";
import { setToastWithTimout } from "../../../core/functions";
import { useTccApi } from "../../../hooks/api";
import { Button } from "../../components/button/button.component";
import "../login/login.style.css";
export const SingUp = ({ setToast }) => {
  const { createNewUser } = useTccApi();

  const [login, setLogin] = useState({});
  const navigate = useNavigate();

  const handleChange = ({ value, name }) => {
    setLogin({ ...login, [name]: value });
  };
  const handleClick = async () => {
    try {
      await createNewUser(
        login.userName,
        login.nickName,
        login.password,
        login.email
      );
      navigate("/login");
    } catch (error) {
      navigate("/signUp");

      setToastWithTimout(setToast, error.response.data.message, X_TOAST);
    }
  };
  return (
    <div className="login">
      <div
        className="formContainer"
        onChange={(Event) => handleChange(Event.target)}
      >
        <label htmlFor="userName">Nome Completo</label>
        <input type="text" name="userName" />
        <label htmlFor="nickName">Apelido</label>
        <input type="text" name="nickName" />
        <label htmlFor="email">Email</label>
        <input type="text" name="email" />
        <label htmlFor="password">Senha</label>
        <input type="password" name="password" />
        <label htmlFor="password">profileImg</label>
        <input type="text" name="profileImg" />
        <Button onClick={handleClick} children={"Criar Conta"} />
      </div>
    </div>
  );
};
