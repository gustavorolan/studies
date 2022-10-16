import "./register.style.css";
import { useNavigate } from "react-router-dom";
import { useAuthApi } from "../../../hooks/api";
import { useState } from "react";
import { Button, CustomInput, ToastError } from "../../components";

const INITIAL_USER_INFO = {
  username: "",
  password: "",
  confirmPassword: "",
};

export const Register = () => {
  const [userInfo, setUserInfo] = useState(INITIAL_USER_INFO);
  const [error, setError] = useState(null);
  const { register } = useAuthApi();
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await register(userInfo);
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

  const handleNavigateToLogin = () => {
    navigate("/login");
  };

  return (
    <div className="register__main-screen">
      <form className="register__form" onSubmit={handleSubmit}>
        <div className="register__form-input">
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
          <CustomInput
            type="password"
            label="Confirmar Senha"
            name="confirmPassword"
            onChange={handleUserInfo}
            value={userInfo.confirmPassword}
          />
          <Button>Register</Button>
          <Button onClick={handleNavigateToLogin}>Já tenho uma conta</Button>
          {error && (
            <ToastError errorMessage={error} onCloseToast={handleCloseToast} />
          )}
        </div>
      </form>
    </div>
  );
};
