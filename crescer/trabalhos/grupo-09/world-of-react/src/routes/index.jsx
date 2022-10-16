import { Routes, Route, Navigate } from "react-router-dom";
import { Login, Home, SelectCharacter, CreateCharacter, ItemsDetails, Store, Battle, Quests, QuestsDetails, Register } from "../ui/screens";
import { Guest, Private } from "../ui/components";

export const AllRoutes = () => {
  return (
    <Routes>
      <Route
        path="/store"
        element={
          <Private>
            <Store />
          </Private>
        }
      />
      <Route
        path="/store/:id"
        element={
          <Private>
            <ItemsDetails />
          </Private>
        }
      />
      <Route
        path="/login"
        element={
          <Guest>
            <Login />
          </Guest>
        }
      />
      <Route
        path="/"
        element={
          <Private>
            <Home />
          </Private>
        }
      />
      <Route
        path="/select-character"
        element={
          <Private>
            <SelectCharacter />
          </Private>
        }
      />
      <Route
        path="/create-character"
        element={
          <Private>
            <CreateCharacter />
          </Private>
        }
      />
      <Route
        path="/battle"
        element={
          <Private>
            <Battle />
          </Private>
        }
      />
      <Route
        path="/quests"
        element={
          <Private>
            <Quests />
          </Private>
        }
      />
      <Route
        path="/quests/:id"
        element={
          <Private>
            <QuestsDetails />
          </Private>
        }
      />

      <Route
        path="/register"
        element={
          <Guest>
            <Register />
          </Guest>
        }
      />
      <Route path="*" element={<Navigate to="/login" />} />
    </Routes>
  );
};
