import "./App.css";
import { useGlobalCharacter } from "./context";
import { AllRoutes } from "./routes";
import { CheatInput } from "./ui/components";

function App() {
  const [globalCharacter] = useGlobalCharacter();
  return (
    <>
      {globalCharacter.id && <CheatInput />}
      <AllRoutes />
    </>
  );
}

export default App;
