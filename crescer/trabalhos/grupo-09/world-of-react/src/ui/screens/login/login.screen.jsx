import "./login.style.css";

import { useState } from "react";
import { useAuthApi } from "../../../hooks/api";
import { CustomInput, Button, ToastError } from "../../components";
import { useNavigate } from "react-router-dom";

const INITIAL_USER_INFO = {
  username: "",
  password: "",
};

export const Login = () => {
  const [userInfo, setUserInfo] = useState(INITIAL_USER_INFO);
  const [error, setError] = useState(null);
  const { login } = useAuthApi();
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await login(userInfo);
      navigate("/");
    } catch (error) {
      setError(error.response?.data?.message);
    }
  };

  const handleUserInfo = (event) => {
    const { name, value } = event.target;

    setUserInfo({ ...userInfo, [name]: value });
  };

  const handleCloseToast = () => {
    setError(null);
  };

  const handleNavigateToRegister = () => {
    navigate("/register");
  };

  return (
    <div className="login__main-screen">
      <form className="login__form" onSubmit={handleSubmit}>
        <div className="login__form-input">
          <CustomInput
            type="text"
            label="Usuário"
            name="username"
            onChange={handleUserInfo}
            value={userInfo.username}
          />
          <CustomInput
            type="password"
            label="Senha"
            name="password"
            onChange={handleUserInfo}
            value={userInfo.password}
          />
          <Button>Login</Button>
          <Button onClick={handleNavigateToRegister}>Não tenho conta</Button>
          {error && (
            <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
          )}
        </div>
      </form>
    </div>
  );
};
