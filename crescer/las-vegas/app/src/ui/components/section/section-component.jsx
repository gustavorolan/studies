import { TaskBar } from "../taskbar/taskbar-component";
import "./section.css";

export const SectionComponent = ({ children }) => {
  return (
    <section className="section-component">
      <TaskBar />
      <div className="div-body">{children}</div>
    </section>
  );
};
