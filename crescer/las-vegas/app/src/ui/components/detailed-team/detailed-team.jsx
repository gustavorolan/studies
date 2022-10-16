import { useEffect, useState } from "react/cjs/react.development";
import { HeaderComponent } from "..";
import { SectionComponent } from "../section/section-component";
import { useTeamApi } from "../../../hooks/api/team/use-team-api";
import { CourseCard } from "../course-card/course-card-component";
import { ChatRoom } from "../chat-room/chat-room.component";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import "./detailed-team.css"
import { CourseCardUser } from "../course-card-user/course-card-user.component";

export const DetailedTeam = () => {
  const teamApi = useTeamApi();
  const [team, setTeam] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { getMessages } = useUserApi();

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

 

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
      <h1>Minha Trilha </h1>

        <div className="courses-container">
          {team?.courses?.map((course) => {
              return (
                <>
                <div>
                  <CourseCardUser course={course} key={course.id} />
                </div>
                </>
            )})}
        </div>
      </SectionComponent>
    </>
  );
};
