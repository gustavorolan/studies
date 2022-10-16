import { useEffect, useState } from "react/cjs/react.development";
import { useCourseApi } from "../../../hooks/api/course/use-course-api";
import {
  CourseCard,
  HeaderComponent,
  InputSeparatedFile,
  SectionComponent,
} from "../../components";
import { CourseCardUser } from "../../components/course-card-user/course-card-user.component";

export const MyCoursesScreen = ({ fromModal, roadmapId }) => {
  const courseApi = useCourseApi();
  const { includeCourse } = useCourseApi();
  const [myCourses, setCourses] = useState([]);

  useEffect(() => {
    const getAllCourses = async () => {
      const response = await courseApi.getLoggedUserCourses();
      setCourses(response);
    };
    getAllCourses();
  }, [courseApi]);

  const handleIncludeCourseClick = async (courseId, roadmapId) => {
    const response = await courseApi.includeCourseInRoadmap(
      courseId,
      roadmapId
    );
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <h1>Meus Cursos</h1>
        <div className="upper-div">
              <InputSeparatedFile
                children={"novo curso"}
                includeCallback={includeCourse}
                redirectUrl={"/course-details"}
                includeRoadmapInTeam={false}
                fromVideo={false}
                editId={false}
                videoId={false}
              />
          </div>
        <div className="courses-container">
          {myCourses?.map((course) => {
            return (
              <div key={course.id}>
                {fromModal ? (
                  <button
                    className="novo"
                    onClick={() =>
                      handleIncludeCourseClick(course.id, roadmapId)
                    }
                  >
                    Adicionar Curso
                  </button>
                ) : (
                  <></>
                )}

                <div className="course-card">
                  <CourseCardUser course={course} />
               
                </div>
              </div>
            );
          })}
        </div>
      </SectionComponent>
    </>
  );
};
