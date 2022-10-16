import "./doubt-card.css";
import { useNavigate } from "react-router-dom";
import { ProfileImage } from "../index";
import { IMAGE, ROUTES } from "../../../constants";

export const DoubtCard = ({ doubt }) => {
  const navigate = useNavigate();

  const handleClickNavigate = () => {
    navigate(`/doubt-details/${doubt.id}`);
  };

  return (
    <button className="doubt-card" onClick={handleClickNavigate}>
      <div className="doubt-author-information">
        <div className="doubt-author">
          <ProfileImage user={doubt.author} />
          <div className="author-name-email">
            <h2>{doubt.author.fullName}</h2>
            <h4>{doubt.author.email}</h4>
          </div>
        </div>
        <div>
          <h3>{doubt.dateTimeCreation}</h3>
        </div>
      </div>
      <div className="doubt-post">
        <div className="doubt-card-text">
          <h5>{doubt.description}</h5>
        </div>
        {doubt.image && (
          <div className="doubt-image">
            <img
              src={`${ROUTES.API_BASE_URL}${IMAGE.DOWNLOAD}/${doubt.image.id}`}
              alt="doubt-img"
            />
          </div>
        )}
      </div>
    </button>
  );
};
