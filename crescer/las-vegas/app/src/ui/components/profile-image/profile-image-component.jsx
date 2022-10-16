import "./profile-image.css";
import defaultUserImage from "../../../assets/image/defaultUserImage.png";
import { IMAGE, ROUTES } from "../../../constants";

export const ProfileImage = ({ user }) => {
  return (
    <>
      {user.imageId ? (
        <img
          src={`${ROUTES.API_BASE_URL}${IMAGE.DOWNLOAD}/${user.imageId}`}
          alt="user-img"
          className="user-profile-image"
        />
      ) : (
        <img
          src={defaultUserImage}
          alt="user-img"
          className="user-profile-image"
        />
      )}
    </>
  );
};
