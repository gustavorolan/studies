import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../../../constants";
import { useUserApi } from "../../../hooks/api/user/use-user-api";

export const CourseCardUser = ({
  course,
  handlApproveCourseClick,
  handleRemoveCourseClick,
}) => {
  const userApi = useUserApi();
  const [courseAuthor, setCourseAuthor] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const getDetailedUser = async () => {
      const response = await userApi.getDetailedUser(course.authorId);
      setCourseAuthor(response);
    };

    getDetailedUser();
  }, [userApi, course.authorId]);

  const handleClick = () => {
    navigate(`/course-details/${course.id}`);
  };

  return (
    <div className="course-card-">
      <button onClick={handleClick} className="course-card-button-to-course">
        <img
          src={`${ROUTES.API_BASE_URL}/download/${course.image?.id}`}
          alt="Course"
          className="course-card img"
        />
      </button>
      <h2 className="course-name">{course.name}</h2>
      <p className="course-description">{course.description}</p>
    </div>
  );
};
