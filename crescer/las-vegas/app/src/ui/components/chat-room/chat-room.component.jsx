import "./chat-room.style.css";
import React from "react";
import { over } from "stompjs";
import SockJS from "sockjs-client";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import rightArrow from "../../../assets/image/rightArrow.svg";
import { IMAGE, ROUTES } from "../../../constants";

let stompClient = null;
let messagetoSend = "";
export const ChatRoom = ({
  messages,
  setMessagesReceived,
  userReceiver,
  teamMate,
  setChatController,
}) => {
  const { sendMessage, getUserLogged, getMessages } = useUserApi();
  const [user, setUser] = React.useState({});
  const [value, setValue] = React.useState("");

  const onConnected = (response) => {
    stompClient.subscribe(
      "/user/" + response.email + "/chat",
      onMessageReceived
    );
  };

  const connect = async (response) => {
    const Sock = new SockJS(`${ROUTES.API_BASE_URL}/ws`);
    stompClient = over(Sock);
    stompClient.connect({}, () => onConnected(response));
  };

  const onMessageReceived = async () => {
    const { messageList } = await getMessages(teamMate);
    setMessagesReceived(messageList.reverse());
    setChatController(true);
  };

  React.useEffect(() => {
    const getUserLoggedApi = async () => {
      const response = await getUserLogged();
      setUser(response);
      connect(response);
      if (!teamMate) teamMate(response.email);
    };
    getUserLoggedApi();
  }, []);

  const handleChange = ({ value }) => {
    setValue(value);
    messagetoSend = value;
  };

  const sendPrivateValue = async () => {
    if (stompClient && !!teamMate && teamMate !== user.email) {
      await sendMessage({ message: messagetoSend, receiverName: teamMate });
      const { messageList } = await getMessages(teamMate);
      setMessagesReceived(messageList.reverse());
      setValue("");
    }
  };

  const handleExit = () => {
    setChatController(false);
  };

  return (
    <div className="chat">
      <div className="user-profile">
        <div onClick={handleExit}>
          <button> x </button>
        </div>
        <p>{userReceiver.fullName}</p>
        <img
          className="img-user"
          src={`${ROUTES.API_BASE_URL}${IMAGE.DOWNLOAD}/${userReceiver?.imageId}`}
          alt="User"
        />
      </div>
      <div className="chat-box">
        {messages.map(({ senderName, message, dateTime }, index) => {
          return (
            <div
              className={`${senderName === user.email ? "right" : "left"}`}
              key={index}
            >
              <div>{message}</div>
              <span>{dateTime} </span>
            </div>
          );
        })}
      </div>

      <div className="sent-chat">
        <input
          value={value}
          type="text"
          onChange={(Event) => handleChange(Event.target)}
          placeholder="Envie uma mensagem"
        />
        <button onClick={sendPrivateValue}>
          <img src={rightArrow} alt="send arrow" srcset="" />{" "}
        </button>
      </div>
    </div>
  );
};
