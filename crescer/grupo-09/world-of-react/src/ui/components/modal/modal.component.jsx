import "./modal.style.css";

import { Button } from '../../components'

export const Modal = ({
  title,
  description,
  onConfirm,
  itemDelete,
  isOpen,
  onOpen,
  errorMessage,
}) => {
  const handleConfirm = () => {
    onConfirm(itemDelete);
  };

  const handleOpenModal = () => {
    onOpen();
  };

  return (
    <div className={`modal__content ${!isOpen && "modal-hide"}`}>
      <button 
        onClick={handleOpenModal} 
        className="modal__btn-close"
      >
        X
      </button>
      <h1 className="modal__title">{title}</h1>
      <p className="modal__description">{description}</p>
      {!!errorMessage && (
        <span className="modal__error-message">{errorMessage}</span>
      )}
      <Button onClick={handleConfirm} className="modal__confirm">
        Confirmar
      </Button>
    </div>
  );
};

Modal.defaultProps = {
  title: "Modal",
  errorMessage: "",
};
