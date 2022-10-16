import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalUser } from "../../../contexts/user.context";
import { X_TOAST } from "../../../core/constants";
import { setToastWithTimout } from "../../../core/functions";
import { useTccApi } from "../../../hooks/api";
import { Button } from "../../components/button/button.component";
import "./login.style.css";
export const Login = ({setToast}) => {
  const [, setUser] = useGlobalUser();
  const { getLogin } = useTccApi();
  const navigate = useNavigate();
  const [login, setLogin] = useState({});
  const handleChange = ({ value, name }) => {
    setLogin({ ...login, [name]: value });
  };

  const handleClickToSignUp = () => {
    navigate("/signUp");
  };
  const handleClick = async () => {
    try {
      const response = await getLogin(login);
      setUser({ token: response.headers["x-auth-token"] });
      navigate("/home");
      
    } catch (error) {
      setToastWithTimout(setToast, error.response.data.message||"Entrada Invalida", X_TOAST);
    }
  };
  return (
    <div className="login">
      <div
        className="formContainer"
        onChange={(Event) => handleChange(Event.target)}
      >
        <label htmlFor="email">Email</label>
        <input type="text" name="username" />
        <label htmlFor="password">Senha</label>
        <input type="password" name="password" />
        <Button onClick={handleClick} children={"Login"} />
        <button onClick={handleClickToSignUp}>
          <p>Criar Conta</p>
        </button>
      </div>
    </div>
  );
};
