import { useEffect, useState } from "react";
import { useCourseApi } from "../../../hooks/api/course/use-course-api";
import { CourseCard } from "../../components";

export const AddCoursesScreen = () => {
  const courseApi = useCourseApi();
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const getAllCourses = async () => {
      const response = await courseApi.getCoursesNotInTeam();
      setCourses(response);
    };
    getAllCourses();
  }, [courseApi]);

  const handleIncludeCourseClick = async (courseId) => {
    await courseApi.includeCourseInTeam(courseId);
  };

  return (
    <>
      <h1 className="courses-title">Cursos da comunidade</h1>
      <h2 className="courses-subtitle">
        Selecione um curso para adicionar a sua trilha
      </h2>
      <div className="courses-container">
        {courses.map((course) => {
          return (
            <>
              <button
                className="trilha"
                onClick={() => handleIncludeCourseClick(course.id)}
              >
                Adicionar Curso
              </button>
              <CourseCard course={course} key={course.id} />
            </>
          );
        })}
      </div>
    </>
  );
};
