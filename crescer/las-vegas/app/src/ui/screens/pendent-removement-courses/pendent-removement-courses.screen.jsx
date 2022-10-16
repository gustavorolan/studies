import { useEffect, useState } from "react/cjs/react.development";
import { useCourseApi } from "../../../hooks/api/course/use-course-api";
import { CourseCard, HeaderComponent } from "../../components";
import { SectionComponentManager } from "../../components/section-manager/section-manager-component";
import { SectionComponent } from "../../components/section/section-component";

export const PendentRemovementCoursesScreen = ({ fromModal, roadmapId }) => {
  const courseApi = useCourseApi();
  const [myCourses, setCourses] = useState([]);

  useEffect(() => {
    const getPendentRemovementCourses = async () => {
      const response = await courseApi.getPendentRemovementCourses();
      setCourses(response);
    };
    getPendentRemovementCourses();
  }, [courseApi]);

  const handleIncludeCourseClick = async (courseId, roadmapId) => {
    const response = await courseApi.includeCourseInRoadmap(
      courseId,
      roadmapId
    );
  };

  const handleSubmitApprovementClick = async (courseId) => {
    const response = await courseApi.changeCourseApprovementStatus(courseId);
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponentManager>
        <h1 className="courses-title">Meus Cursos</h1>
        <div className="courses-container">
          {myCourses?.map((course, index) => {
            return (
              <>
                {course?.approvementStatus === "PENDENTE_REMOCAO" &&  <button
                    className="btn third"
                    onClick={() =>
                      handleSubmitApprovementClick(course.id)
                    }
                  >
                    Confirmar Remoção de Curso
                </button>}

                {fromModal ? (
                  <button
                    className="btn third"
                    onClick={() =>
                      handleIncludeCourseClick(course.id, roadmapId)
                    }
                  >
                    Adicionar Curso
                  </button>
                ) : (
                  <></>
                )}
                <CourseCard course={course} key={index} />
              </>
            );
          })}
        </div>
      </SectionComponentManager>
    </>
  );
};
