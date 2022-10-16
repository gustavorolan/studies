import "./teams-screen.css";
import {
  HeaderComponent,
  InputSeparatedFile,
  SectionComponent,
} from "../../components";
import { useTeamApi } from "../../../hooks/api/team/use-team-api";
import { useEffect, useState } from "react/cjs/react.development";
import { Link } from "react-router-dom";
import { ROUTES } from "../../../constants";

export const MyTeamsScreen = () => {
  const teamApi = useTeamApi();

  const [myTeams, setMyTeams] = useState([]);
  const [myCreatedTeams, setMyCreatedTeams] = useState([]);

  useEffect(() => {
    const getMyTeams = async () => {
      const myTeamsResponse = await teamApi.getLoggedUserTeams();
      const myCreatedTeamsresponse = await teamApi.getLoggedUserCreatedTeams();
      setMyTeams(myTeamsResponse);
      setMyCreatedTeams(myCreatedTeamsresponse);
    };

    getMyTeams();
  }, [teamApi]);

  const handleChangeTeamStatus = async (teamId) => {
    const myTeamsResponse = await teamApi.changeTeamActiveStatus(teamId);
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <div className="div-body">
          <h1>Times Gerenciados por Você</h1>
          {myCreatedTeams?.map((team, index) => {
            return (
              <>
                <button onClick={() => handleChangeTeamStatus(team.id)}>
                  Remover Time
                </button>
                <InputSeparatedFile
                  children={"Atualização de Time"}
                  includeCallback={teamApi.updateTeam}
                  redirectUrl={"/team-details"}
                  includeRoadmapInTeam={false}
                  fromVideo={false}
                  editId={team.id}
                  videoId={false}
                />
                <Link to={`/team-details/${team?.id}`}>
                  <button>
                    {team?.image?.id && (
                      <img
                        className="imageDetail"
                        src={`${ROUTES.API_BASE_URL}/download/${team?.image?.id}`}
                        alt="Team"
                      />
                    )}
                    <div key={index}>
                      <Link to={`/user-details/${team?.creatorId}`}>
                        <button className="course-card-button">
                          Ver criador
                        </button>
                      </Link>
                      <h1>{team?.name}</h1>
                      <h2>{team?.description}</h2>
                    </div>
                  </button>
                </Link>
              </>
            );
          })}

          <h1>Seus Times</h1>
          {myTeams?.map((team, index) => {
            return (
              <Link to={`/team-details/${team?.id}`}>
                <button>
                  {team?.image?.id && (
                    <img
                      className="imageDetail"
                      src={`ROUTES.API_BASE_URL/download/${team?.image?.id}`}
                      alt="Team"
                    />
                  )}
                  <div key={index}>
                    <h1>{team?.name}</h1>
                    <h2>{team?.description}</h2>
                  </div>
                </button>
              </Link>
            );
          })}
        </div>
      </SectionComponent>
    </>
  );
};
