import React from "react";
import { useTeamApi } from "../../../hooks/api/team/use-team-api";
import { TeamManagement } from "../../components";

export const TeamIncludeUser = () => {

const { getAllUsersNotInTeam, includeUserInTeam } = useTeamApi();

  return (
    <div>
      <TeamManagement callback={getAllUsersNotInTeam} action={includeUserInTeam} actionName="Adicionar a Equipe" />
    </div>
  );
};
