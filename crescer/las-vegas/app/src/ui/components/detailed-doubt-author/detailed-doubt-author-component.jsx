import "./detailed-doubt-author.css";
import { useState } from "react";
import {
  Toaster,
  ProfileImage,
  CommentForm,
  Modal,
} from "../../components/index";

export const DetailedDoubtAuthor = ({
  doubtId,
  doubtAuthor,
  doubtDescription,
  reloadDoubt,
}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [toastState, setToastState] = useState({});

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
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
      <div className="doubt-author-and-text">
        <div className="doubt-author-info">
          <ProfileImage user={doubtAuthor} />
          <h2>{doubtAuthor?.fullName}</h2>
          <h5>{doubtAuthor?.email}</h5>
        </div>
        <div className="doubt-text">
          <h3>{doubtDescription}</h3>
        </div>
        <div className="comment-button-container">
          <button className="comment-button" onClick={openModal}>
            Responder
          </button>
        </div>
      </div>
      <Modal isModalOpen={isModalOpen} openModalClick={openModal}>
        {doubtId && (
          <CommentForm
            idDoubt={doubtId}
            reloadDoubt={reloadDoubt}
            closeModal={openModal}
            showToast={handleClickShowToast}
          />
        )}
      </Modal>
    </>
  );
};
