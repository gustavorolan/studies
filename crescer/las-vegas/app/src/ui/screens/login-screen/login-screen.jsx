import "./login-screen.css";
import alienImage from "../../../assets/image/alienLoginIcon.svg";
import biluProjectLoginTitle from "../../../assets/image/biluProjectLoginTitle.png";
import alienBackground from "../../../assets/image/login-image.png";
import { LoginForm, RegisterForm, Toaster } from "../../components";
import { useGlobalUser } from "../../../context";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export const LoginScreen = () => {
  const [inLogin, setInLogin] = useState(true);
  const [globalUser] = useGlobalUser();
  const navigate = useNavigate();
  const [toastState, setToastState] = useState({});

  useEffect(() => {
    if (globalUser.token) {
      navigate("/userTeam");
    }
  }, [globalUser, navigate]);

  const handleChangeLoginState = () => {
    setInLogin(!inLogin);
  };
  const handleClickShowToast = (type, message) => {
    setToastState({
      ...toastState,
      showToast: true,
      type: type,
      message: message,
    });
  };
  return (
    <>
      {toastState.showToast && (
        <Toaster
          message={toastState.message}
          type={toastState.type}
          closeToast={() => setToastState({})}
        />
      )}
      <section className="section-box">
        <div className="login-left-box">
          <div className="login-project-title">
            <img src={alienImage} alt="alienLoginIcon" />
            <img src={biluProjectLoginTitle} alt="biluProjectLoginTitle" />
          </div>
          {inLogin ? (
            <LoginForm showToast={handleClickShowToast} />
          ) : (
            <RegisterForm
              toggleInLogin={handleChangeLoginState}
              showToast={handleClickShowToast}
            />
          )}
          <button
            className="create-account-button"
            onClick={handleChangeLoginState}
          >
            {inLogin ? <>Criar uma conta</> : <>Já tenho uma conta</>}
          </button>
        </div>
        <img src={alienBackground} alt="login-backgrund" />
      </section>
    </>
  );
};
