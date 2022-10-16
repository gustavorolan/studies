import "./comment-card.css";
import { useState } from "react";
import { Modal } from "../index";
import { ProfileImage } from "../index";
import { ChatRoom } from "../chat-room/chat-room.component";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import { ROUTES } from "../../../constants";

export const CommentCard = ({ comment }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [userReceiver, setUserReceiber] = useState();
  const [messages, setMessagesReceived] = useState([]);
  const [chatController, setChatController] = useState(false);
  const [teamMate, setTeamMate] = useState("");
  const { getMessages } = useUserApi();

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const setPersonToChat = async (email, personToChat) => {
    setUserReceiber(personToChat);
    setChatController(true);
    setTeamMate(email);
    const { messageList } = await getMessages(personToChat);
    setMessagesReceived(messageList.reverse());
  };
  return (
    <>
      {chatController && (
        <ChatRoom
          setTeamMate={setTeamMate}
          messages={messages}
          userReceiver={userReceiver}
          setMessagesReceived={setMessagesReceived}
          teamMate={teamMate}
          setChatController={setChatController}
        />
      )}

      <div className="comment-card">
        <div className="comment-user-author-information">
          <ProfileImage user={comment.author} />

          <h2>{comment.author.fullName}</h2>
          <h6>{comment.dateTimeCreation}</h6>
        </div>
        <div className="comment-text">
          <h4>{comment.description}</h4>
        </div>
        <div className="comment-image">
          {comment.image && (
            <img
              src={`${ROUTES.API_BASE_URL}/download/${comment.image.id}`}
              alt="comment-img"
              onClick={openModal}
            />
          )}
        </div>
      </div>
      <Modal openModalClick={openModal} isModalOpen={isModalOpen}>
        <div className="image-on-modal">
          <img
            src={`ROUTES.API_BASE_URL/download/${comment.image?.id}`}
            alt="comment-img"
          />
        </div>
      </Modal>
    </>
  );
};
