import "./modal.style.css"

export const Modal = ({ children, isModalOpen, openModalClick }) => {
  if (!isModalOpen) {
    return null;
  }

  return (
    <div className="modal">
      <div className="modal-conatiner">
      {children}
      <button className="close-button" onClick={openModalClick}>
        Fechar
      </button>
      </div>
    </div>
  );
};
