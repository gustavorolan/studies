import "./doubt-details.css";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useDoubtApi } from "../../../hooks/api/doubt/use-doubt-api";
import {
  CommentCard,
  DetailedDoubtAuthor,
  HeaderComponent,
  Modal,
  SectionComponent,
} from "../../components";
import { IMAGE, ROUTES } from "../../../constants";

export const DoubtDetailsScreen = () => {
  const [doubt, setDoubt] = useState({});
  const [isModalOpen, setIsModalOpen] = useState(false);

  const { getDetailedDoubt } = useDoubtApi();
  const { doubtId } = useParams();

  const getDoubt = async () => {
    try {
      const response = await getDetailedDoubt(doubtId);

      aaa(response);
    } catch (error) {}
  };

  useEffect(() => {
    getDoubt();
  }, []);

  useEffect(() => {
    setDoubt(doubt);
  }, [doubt]);

  const aaa = (doubtToAdd) => {
    setDoubt(doubtToAdd);
  };

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <div className="doubt-details-container">
          {doubt?.id && (
            <DetailedDoubtAuthor
              doubtId={doubt.id}
              doubtAuthor={doubt.author}
              doubtDescription={doubt.description}
              reloadDoubt={getDoubt}
            />
          )}
          <div className="doubt-details-left-place">
            {doubt?.image && (
              <div className="doubt-details-image">
                <img
                  src={`${ROUTES.API_BASE_URL}${IMAGE.DOWNLOAD}/${doubt?.image.id}`}
                  alt="doubt-img"
                  onClick={openModal}
                />
              </div>
            )}

            <div className="comments-container">
              {doubt?.comments?.map((comment) => {
                return <CommentCard comment={comment} key={comment.id} />;
              })}
            </div>
          </div>
        </div>
      </SectionComponent>
      <Modal isModalOpen={isModalOpen} openModalClick={openModal}>
        <div className="image-on-modal">
          <img
            src={`${ROUTES.API_BASE_URL}${IMAGE.DOWNLOAD}/${doubt?.image?.id}`}
            alt="comment-img"
          />
        </div>
      </Modal>
    </>
  );
};
