import "./user-team.style.css";
import React from "react";
import { useTeamApi } from "../../../hooks/api/team/use-team-api";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import { HeaderComponent, SectionComponent, ChatRoom } from "../../components";
import { ROUTES } from "../../../constants";
import { useGlobalUser } from "../../../context";

let teamMate = "";
export const UserTeam = () => {
  const { getMessages } = useUserApi();
  const [messages, setMessagesReceived] = React.useState([]);
  const [chatController, setChatController] = React.useState(false);
  const [userReceiver, setUserReceiber] = React.useState();
  const teamApi = useTeamApi();
  const [teamArray, setTeamArray] = React.useState([]);
  const [user] = useGlobalUser();

  React.useEffect(() => {
    const getUsersInTeam = async () => {
      const usersInTeam = await teamApi.getAllUsersInTeam();
      const userInTeamWithoutMe = usersInTeam.filter(
        (userTeam) => userTeam.id !== user.id
      );
      setTeamArray(userInTeamWithoutMe);
    };
    getUsersInTeam();
  }, [teamApi]);

  const setPersonToChat = async (personToChat) => {
    setUserReceiber(personToChat);
    setChatController(true);
    teamMate = personToChat.email;
    const { messageList } = await getMessages(personToChat.email);
    setMessagesReceived(messageList.reverse());
  };

  return (
    <>
      {chatController && (
        <ChatRoom
          messages={messages}
          userReceiver={userReceiver}
          setMessagesReceived={setMessagesReceived}
          teamMate={teamMate}
          setChatController={setChatController}
        />
      )}
      <HeaderComponent />
      <SectionComponent>
        <div className="user-team-container">
          <h1>Meu Time</h1>
          <div className="team-mate-container">
            {teamArray.map((teamMate, index) => {
              return (
                <button
                  className="team-mate-button"
                  key={index}
                  onClick={() => setPersonToChat(teamMate)}
                >
                  <img
                    className="img"
                    src={ROUTES.API_BASE_URL + "/download/" + teamMate.imageId}
                    alt="profile team mate"
                  />

                  <p>{teamMate.fullName}</p>
                </button>
              );
            })}
          </div>
        </div>
      </SectionComponent>
    </>
  );
};
