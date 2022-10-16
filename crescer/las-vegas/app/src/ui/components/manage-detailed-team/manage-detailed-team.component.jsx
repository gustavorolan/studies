import { useEffect, useState } from "react/cjs/react.development";
import { HeaderComponent } from "..";
import { useTeamApi } from "../../../hooks/api/team/use-team-api";
import { Modal } from "../modal/modal";
import { AddCoursesScreen } from "../../screens/add-courses-screen/add-courses-screen";
import { InputSeparatedFile } from "../separated-file/input-separated-file.component";
import { ChatRoom } from "../chat-room/chat-room.component";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import { CourseCard } from "../course-card/course-card-component";
import "./manage-detailed-team.component.css";
import { ManagerTaskBar } from "../manager-taskbar/manager-taskbar";
import { SectionComponentManager } from "../section-manager/section-manager-component";
import { CourseCardUser } from "../course-card-user/course-card-user.component";

export const ManageDetailedTeam = () => {
  const teamApi = useTeamApi();
  const { registerTeam, updateTeam } = useTeamApi();
  const [team, setTeam] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { getMessages } = useUserApi();
  const [messages, setMessagesReceived] = useState([]);
  const [chatController, setChatController] = useState(false);
  const [teamMate, setTeamMate] = useState("");
  const [userReceiver, setUserReceiber] = useState();

  useEffect(() => {
    const getDetailedTeam = async () => {
      const detailedTeamResponse = await teamApi.getDetailedTeam();
      setTeam(detailedTeamResponse);
    };
    getDetailedTeam();
  }, [teamApi]);

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const setPersonToChat = async (email, personToChat) => {
    setUserReceiber(personToChat);
    console.log(personToChat);
    setChatController(true);
    setTeamMate(email);
    const { messageList } = await getMessages(personToChat);
    setMessagesReceived(messageList.reverse());
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponentManager>
        <h1>Trilha</h1>

        <div className="divBody">
          <InputSeparatedFile
            children={"nova trilha"}
            includeCallback={registerTeam}
            redirectUrl={"/#"}
            includeRoadmapInTeam={false}
            fromVideo={false}
            editId={false}
            videoId={false}
          />

          <InputSeparatedFile
            children={"atualiz. de trilha"}
            includeCallback={updateTeam}
            redirectUrl={"/#"}
            includeRoadmapInTeam={false}
            fromVideo={false}
            editId={false}
            videoId={false}
          />

          <button className="action trilha" onClick={openModal}>
            Adicionar novo curso
          </button>
          <Modal
            children={<AddCoursesScreen />}
            isModalOpen={isModalOpen}
            openModalClick={openModal}
          />
        </div>

        <div className="courses-container">
          {team?.courses?.map((course) => {
            return (
              <>
                <div>
                  <CourseCardUser course={course} key={course.id} />
                </div>
              </>
            );
          })}
        </div>
      </SectionComponentManager>
    </>
  );
};
