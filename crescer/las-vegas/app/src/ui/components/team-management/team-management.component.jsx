import React from "react";
import { HeaderComponent } from "../header/header-component";
import { ManagerTaskBar } from "../manager-taskbar/manager-taskbar";
import "./team-management.style.css";
import { ROUTES } from "../../../constants";
import { useGlobalUser } from "../../../context";
export const TeamManagement = ({ callback, action, actionName }) => {
  const [users, setUsers] = React.useState();
  const [user] = useGlobalUser();

  const getUsersToManage = async () => {
    const users = await callback();
    const usersWithoutMe = users.filter(
      (userToFilter) => user.id !== userToFilter.id
    );
    setUsers(usersWithoutMe);
  };

  React.useEffect(() => {
    getUsersToManage();
  }, [users]);

 
  const handleClick = (id) => {
    action(id);
    getUsersToManage();
  };

  return (
    <div>
      <HeaderComponent />
      <section className="section-component">
        <ManagerTaskBar />
        <div className="div-body user-team-container">
          <h1>Gerenciar Time</h1>
          <div className="people-to-add-or-remove-container">
            {users?.map((user) => {
              return (
                <>
                  <div key={user.id} className="people-to-add-button">
                    <img
                      src={`${ROUTES.API_BASE_URL}/download/${user.imageId}`}
                      alt="profile team mate"
                    />
                    <h1>{user.fullName}</h1>

                    <button
                      className=""
                      onClick={() => handleClick(user?.id)}
                    >
                      {actionName}
                    </button>
                  </div>
                </>
              );
            })}
          </div>
        </div>
      </section>
    </div>
  );
};
