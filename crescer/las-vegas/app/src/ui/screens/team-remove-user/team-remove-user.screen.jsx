import React from "react";
import { useTeamApi } from "../../../hooks/api/team/use-team-api";
import { TeamManagement } from "../../components";

export const TeamRemoveUser = () => {
  const { getAllUsersInTeam, removeUserFromTeam } = useTeamApi();

  return (
    <div>
      <TeamManagement
        callback={getAllUsersInTeam}
        action={removeUserFromTeam}
        actionName="Remover da Equipe"
      />
    </div>
  );
};
