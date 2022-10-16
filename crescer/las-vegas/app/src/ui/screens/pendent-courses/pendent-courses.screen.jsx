import { useEffect, useState } from "react/cjs/react.development";
import { useCourseApi } from "../../../hooks/api/course/use-course-api";
import { CourseCard, HeaderComponent } from "../../components";
import { SectionComponentManager } from "../../components/section-manager/section-manager-component";
import "./pendente-courses.screen.css";

export const PendentCoursesScreen = ({ fromModal, roadmapId }) => {
  const courseApi = useCourseApi();
  const [myCourses, setCourses] = useState([]);

  const getAllCourses = async () => {
    const response = await courseApi.getPendentCourses();
    setCourses(response);
  };

  useEffect(() => {
    getAllCourses();
  }, [courseApi]);

  const handleRemoveCourseClick = async (courseId) => {
    await courseApi.changeCourseStatus(courseId);
    getAllCourses();
  };

  const handlApproveCourseClick = async (courseId) => {
    await courseApi.approveCourse(courseId);
    getAllCourses();
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponentManager>
        <h1>Aprovação de Cursos</h1>
        <div className="courses-container">
          {myCourses &&
            myCourses.map((course, index) => {
              return (
                <div key={index} className="course-card">
                  <CourseCard
                    handleRemoveCourseClick={handleRemoveCourseClick}
                    handlApproveCourseClick={handlApproveCourseClick}
                    course={course}
                  />
                  <div className="courses-subtitle "></div>
                </div>
              );
            })}
        </div>
      </SectionComponentManager>
    </>
  );
};
