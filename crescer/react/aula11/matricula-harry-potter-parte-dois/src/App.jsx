import "./App.css";
import { AcademicsDataScreen, EnrollmentsHogwartsScreen, MatterChoicerScreen, PersonalDataScreen, AllDataOfProfileScreen } from "./ui/screens";
import { Routes, Route, useNavigate } from "react-router-dom";
import "./ui/screens/enrollmentsHogwarts.style.css";
import { ProtectedRoute } from "./ui/components";
import { useGlobalUser } from "./contexts";

function App() {
  const [personalData, setPersonalData] = useGlobalUser();
  const navigate = useNavigate();

  const handleLogout = () => {
    setPersonalData({ classes: [] });
    navigate("/");
  };

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<EnrollmentsHogwartsScreen />} />
        <Route path="/PersonalData" element={<ProtectedRoute nomeRota="PersonalData" />}>
          <Route path="/PersonalData" element={<PersonalDataScreen logout={handleLogout} />} />
        </Route>
        <Route path="/AcademicsData" element={<ProtectedRoute nomeRota="AcademicsData" />}>
          <Route path="/AcademicsData" element={<AcademicsDataScreen logout={handleLogout} />} />
        </Route>
        <Route path="/MatterChoicer" element={<ProtectedRoute nomeRota="MatterChoicer" />}>
          <Route path="/MatterChoicer" element={<MatterChoicerScreen logout={handleLogout} />} />
        </Route>
        <Route path="/AllDataOfProfile" element={<ProtectedRoute nomeRota="AllDataOfProfile" />}>
          <Route path="/AllDataOfProfile" element={<AllDataOfProfileScreen logout={handleLogout} />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
