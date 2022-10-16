import "./my-messages.style.css";
import React from "react";
import { over } from "stompjs";
import SockJS from "sockjs-client";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import rightArrow from "../../../assets/image/rightArrow.svg";
import { HeaderComponent, TaskBar } from "../../components";
import { ROUTES } from "../../../constants";

let stompClient = null;
let teamMate = "";
let messagetoSend = "";
export const MyMessages = () => {
  const {
    sendMessage,
    getUserLogged,
    getMessages,
    getMyMessages,
    readMessage,
  } = useUserApi();
  const [user, setUser] = React.useState({});
  const [messages, setMessagesReceived] = React.useState([]);
  const [peopleList, setPeopleList] = React.useState([]);
  const [userReceiver, setUserReceiver] = React.useState([]);
  const [value, setValue] = React.useState("");

  const onMessageReceived = async () => {
    const { messageList } = await getMessages(teamMate);
    setMessagesReceived(messageList.reverse());
    const myMessages = await getMyMessages();
    setPeopleList(myMessages);
  };

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

  React.useEffect(() => {
    const getUserLoggedApi = async () => {
      const response = await getUserLogged();
      const myMessages = await getMyMessages();
      setPeopleList(myMessages);
      setUser(response);
      connect(response);
      teamMate = response.email;
    };
    getUserLoggedApi();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleChange = ({ value }) => {
    setValue(value);
    messagetoSend = value;
  };

  const setPersonToChat = async (personToChat) => {
    teamMate = personToChat;
    const { messageList } = await getMessages(personToChat);
    setMessagesReceived(messageList.reverse());
  };

  const handleSelectPerson = async (person) => {
    await readMessage(person.email);
    setPersonToChat(person.email);
    setUserReceiver(person);
    const myMessages = await getMyMessages();
    setPeopleList(myMessages);
  };

  const sendPrivateValue = async () => {
    if (stompClient && !!teamMate && teamMate !== user.email) {
      await sendMessage({ message: messagetoSend, receiverName: teamMate });
      const { messageList } = await getMessages(teamMate);
      setMessagesReceived(messageList.reverse());
      setValue("");
    }
  };

  return (
    <>
      <HeaderComponent />
      <section className="section-component">
        <TaskBar />
        <div className="div-body messages-container">
          <div className="my-messages-people-to-chat">
            <div className="titulo-people-to-chat">
              <h1>Minhas Conversas</h1>
            </div>
            {peopleList.map((person, index) => {
              return (
                <button onClick={() => handleSelectPerson(person)} key={index}>
                  <img
                    src={ROUTES.API_BASE_URL + "/download/" + person.imageId}
                    alt=""
                  />
                  {person.fullName}
                  {!person.read && <p>Não lida</p>}
                </button>
              );
            })}
          </div>
          <div
            className={`my-messages  ${teamMate ? "" : "background-messages"}`}
          >
            <div className="my-messages-profile">
              {teamMate && (
                <div className="my-messages-profile-left">
                  <img
                    src={
                      ROUTES.API_BASE_URL + "/download/" + userReceiver.imageId
                    }
                    alt=""
                  />
                  <p>{userReceiver.fullName}</p>
                </div>
              )}
            </div>
            <div className="my-messages-box">
              {messages.map(({ senderName, message, dateTime }, index) => {
                return (
                  <div
                    className={`${
                      senderName === user.email
                        ? "my-messages-right"
                        : "my-messages-left"
                    }`}
                    key={index}
                  >
                    <div>{message}</div>
                    <span>{dateTime} </span>
                  </div>
                );
              })}
            </div>

            <div className="my-messages-chat">
              {teamMate && (
                <>
                  <input
                    value={value}
                    type="text"
                    onChange={(Event) => handleChange(Event.target)}
                    placeholder="Envie uma mensagem"
                  />
                  <button onClick={sendPrivateValue}>
                    <img src={rightArrow} alt="send arrow" />{" "}
                  </button>
                </>
              )}
            </div>
          </div>
        </div>
      </section>
    </>
  );
};
