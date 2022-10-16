import "./toast-error.style.css";

export const ToastError = ({ errorMessage, onCloseToast }) => {
  const handleCloseModal = () => {
    onCloseToast();
  };

  return (
    <div className="toast-error__content">
      <div className="toast-error__btn-flex-end">
        <button className="toast-error__btn-close" onClick={handleCloseModal}>
          X
        </button>
      </div>
      <div className="toast-error__error">
        <i className="fa-solid fa-triangle-exclamation"></i>
        <span className="toast-error__message">{errorMessage}</span>
      </div>
    </div>
  );
};

ToastError.defaultProps = {
  errorMessage: "",
};
