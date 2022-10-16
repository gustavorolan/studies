import "./forum.css";
import { useState, useEffect, useCallback } from "react";
import {
  HeaderComponent,
  DoubtCard,
  SectionComponent,
  Modal,
  ForumPostForm,
  Toaster,
} from "../../components";
import { useDoubtApi } from "../../../hooks/api/doubt/use-doubt-api";

export const ForumScreen = () => {
  const [doubtsList, setDoubtsList] = useState([]);
  const [page, setPage] = useState(0);
  const [isModalFormOpen, setIsModaFormlOpen] = useState(false);
  const [toastState, setToastState] = useState({});

  const { getAllDoubts } = useDoubtApi();

  const getDoubtsList = useCallback(async () => {
    try {
      const response = await getAllDoubts(page);
      if (response.length === 0 && page > 0) {
        setDoubtsList(await getAllDoubts(page - 1));
        setPage(page - 1);
      } else {
        setDoubtsList(response);
      }
    } catch (error) {
    }
  }, [getAllDoubts, page]);

  useEffect(() => {
    getDoubtsList();
  }, [getAllDoubts, page, getDoubtsList]);

  const openModal = () => {
    setIsModaFormlOpen(!isModalFormOpen);
  };
  const handleClickPage = (value) => {
    if (page - value >= 0) {
      setPage(page - value);
    }
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
      <HeaderComponent />
      <SectionComponent>
        <div className="forum-container">
          <button className="forum-button-form" onClick={openModal}>
            Publicar dúvida
          </button>
          {doubtsList.map((doubt) => {
            return <DoubtCard doubt={doubt} key={doubt.id} />;
          })}
          <div className="buttons-page">
            <button
              onClick={() => handleClickPage(1)}
              className="button-control-page back-page"
            >
              Voltar
            </button>
            <h2>{page}</h2>
            <button
              onClick={() => handleClickPage(-1)}
              className="button-control-page up-page"
            >
              Avançar
            </button>
          </div>
        </div>
      </SectionComponent>
      <Modal isModalOpen={isModalFormOpen} openModalClick={openModal}>
        <ForumPostForm
          reloadDoubts={getDoubtsList}
          showToast={handleClickShowToast}
          closeModal={openModal}
        />
      </Modal>
    </>
  );
};
