import { ManagerTaskBar } from "../manager-taskbar/manager-taskbar";
import "./section.css";

export const SectionComponentManager = ({ children }) => {
  return (
    <section className="section-component">
      <ManagerTaskBar />
      <div className="div-body">{children}</div>
    </section>
  );
};
