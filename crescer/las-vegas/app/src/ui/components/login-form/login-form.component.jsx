import "./login-form.css";
import axios from "axios";
import { useState } from "react";
import { useGlobalUser } from "../../../context";
import { useAuthApi } from "../../../hooks/api/auth/use-auth-api.hook";

export const LoginForm = ({ showToast }) => {
  const [credentials, setCredentials] = useState({});
  const [globalUser, setGlobalUser] = useGlobalUser();
  const { login } = useAuthApi();

  const handleChange = (event) => {
    const { name, value } = event.target;
    setCredentials({ ...credentials, [name]: value });
  };

  const handleSubmitForm = async (event) => {
    event.preventDefault();
    try {
      const loginResponse = await login(
        credentials.email,
        credentials.password
      );
      setGlobalUser({
        ...globalUser,
        token: loginResponse.headers["x-auth-token"],
        id: loginResponse.data.usuarioId,
        permission: loginResponse.data.permission,
      });

      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${globalUser.token}`;
    } catch (error) {
      showToast("error", error.message);
    }
  };

  return (
    <form action="submit" className="login-form" onSubmit={handleSubmitForm}>
      <div>
        <label htmlFor="email-input">Email</label>
        <input
          id="email-input"
          name="email"
          type="text"
          placeholder="Digite seu email"
          value={credentials.email}
          onChange={handleChange}
          className="login-form-input"
        />
      </div>
      <div>
        <label htmlFor="password-input">Senha</label>
        <input
          id="password-input"
          name="password"
          type="password"
          placeholder="Digite sua senha"
          value={credentials.password}
          onChange={handleChange}
          className="login-form-input"
        />
      </div>
      <button className="submit-login-button">Entrar</button>
    </form>
  );
};
