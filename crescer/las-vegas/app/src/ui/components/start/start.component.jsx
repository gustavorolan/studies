import { FaStar } from "react-icons/fa";
import "./start.component.css";

export const StarRating = ({ filled, onClick }) => {
  return (
    <div className="starts">
      {[...Array(5)].map((star, index) => {
        return (
          <label>
            <input
              type="radio"
              name="rating"
              onClick={() => onClick(index)}
            ></input>
            <FaStar
              className="star"
              color={filled <= index ? "#ffffff" : "#EF4910"}
              size={20}
            />
          </label>
        );
      })}
    </div>
  );
};
