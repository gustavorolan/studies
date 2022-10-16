import "./register-form.css";
import { useState } from "react";
import { useAuthApi } from "../../../hooks/api/auth/use-auth-api.hook";

const formData = new FormData();
export const RegisterForm = ({ toggleInLogin, showToast }) => {
  const [registerCredentials, setRegisterCredentials] = useState({});
  const { register } = useAuthApi();

  const handleChangeCredentials = (event) => {
    const { name, value } = event.target;
    setRegisterCredentials({ ...registerCredentials, [name]: value });
  };

  const onInputFile = (target) => {
    formData.append("file", target.files[0]);
    formData.append("fullName", registerCredentials.fullName);
    formData.append("email", registerCredentials.email);
    formData.append("password", registerCredentials.password);
  };

  const handleSubmitRegister = async (event) => {
    event.preventDefault();
    console.log(registerCredentials);
    try {
      const response = await register(formData);
      showToast("sucess", response.data.response);
      toggleInLogin();
    } catch (error) {
    }
  };

  return (
    <>
      <form
        action="submit"
        onSubmit={handleSubmitRegister}
        className="register-form"
      >
        <div>
          <label htmlFor="name-input">Nome</label>
          <input
            id="name-input"
            type="text"
            name="fullName"
            value={registerCredentials.fullName}
            placeholder="Informe seu nome completo"
            onChange={handleChangeCredentials}
            className="register-form-input"
          />
        </div>
        <div>
          <label htmlFor="email-input">Email</label>

          <input
            id="email-input"
            type="text"
            name="email"
            value={registerCredentials.email}
            placeholder="Informe seu endereço de email"
            onChange={handleChangeCredentials}
            className="register-form-input"
          />
        </div>
        <div>
          <label htmlFor="password-input">Senha</label>
          <input
            id="password-input"
            type="password"
            name="password"
            value={registerCredentials.password}
            placeholder="Informe uma senha"
            onChange={handleChangeCredentials}
            className="register-form-input"
          />
        </div>
        <input type="file" onChange={(Event) => onInputFile(Event.target)} />
        <button type="submit" className="submit-register-button">
          Criar conta
        </button>
      </form>
    </>
  );
};
