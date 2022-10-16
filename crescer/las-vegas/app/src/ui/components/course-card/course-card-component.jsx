import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react/cjs/react.development";
import { ROUTES } from "../../../constants";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import "./course-card.css";

export const CourseCard = ({
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

  const handleClick = ()=>{
    navigate(`/course-details/${course.id}`)
  }
  
  return (
    <div className="course-card-">
      <button onClick={handleClick} className="course-card-button-to-course">
        <img
          src={`${ROUTES.API_BASE_URL}/download/${course.image?.id}`}
          alt="Course-img"
        />
      </button>
      <h2 className="course-name">{course.name}</h2>
      <p className="course-description">{course.description}</p>

      <div>
        <button
          className="course-approval"
          onClick={() => handlApproveCourseClick(course.id)}
        >
          Aprovar
        </button>

        <button
          className="course-approval"
          onClick={() => handleRemoveCourseClick(course.id)}
        >
          Reprovar
        </button>
      </div>
    </div>
  );
};
