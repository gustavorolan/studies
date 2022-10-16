import "./courses-screen.css";
import { useEffect, useState } from "react";
import { useCourseApi } from "../../../hooks/api/course/use-course-api";
import {
  CourseCard,
  HeaderComponent,
  InputSeparatedFile,
  SectionComponent,
} from "../../components";

export const CoursesScreen = () => {
  const courseApi = useCourseApi();
  const [courses, setCourses] = useState([]);
  const { includeCourse } = useCourseApi();

  useEffect(() => {
    const getAllCourses = async () => {
      const response = await courseApi.getAllCourses();
      setCourses(response);
    };
    getAllCourses();
  }, [courseApi]);

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <h1 className="courses-title">Cursos da comunidade</h1>

        <div className="upper-div">
          <h2 className="courses-subtitle">
            Acesse todos os cursos disponíveis na plataforma
            <InputSeparatedFile
              children={"novo curso"}
              includeCallback={includeCourse}
              redirectUrl={"/course-details"}
              includeRoadmapInTeam={false}
              fromVideo={false}
              editId={false}
              videoId={false}
            />
          </h2>
        </div>
        <div className="courses-container">
          {courses.map((course) => {
            return <CourseCard course={course} key={course.id} />;
          })}
        </div>
      </SectionComponent>
    </>
  );
};
