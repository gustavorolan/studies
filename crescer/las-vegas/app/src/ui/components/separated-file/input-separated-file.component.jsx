import "./input-separated-file.css";
import React from "react";
import FormData from "form-data";
import { useImageApi } from "../../../hooks/api/image/use-image-api";
import { useNavigate } from "react-router-dom";
import { StarRating, Loading, Modal } from "../index";

const formData = new FormData();
export const InputSeparatedFile = ({
  children,
  includeCallback,
  redirectUrl,
  includeRoadmapInTeam,
  fromVideo,
  videoId,
  editId,
}) => {
  const imageApi = useImageApi();

  const [isModalOpen, setIsModalOpen] = React.useState(false);

  const [data, setData] = React.useState({
    name: "",
    description: "",
    imageId: "",
  });

  const [animationController, setAnimationController] = React.useState(false);

  const navigate = useNavigate();

  const [rating, setRating] = React.useState(0);

  const handleChange = ({ name, value }) => {
    setData({ ...data, [name]: value });
  };

  const onInputFile = (target) => {
    formData.append("file", target.files[0]);
  };

  const handleClick = async () => {
    try {
      setAnimationController(true);
      let imageResponse = null;
      if (!fromVideo) {
        imageResponse = await imageApi.includeImage(formData);
      }

      const id = fromVideo ? videoId : imageResponse.data.id;

      let response = null;

      if (editId) {
        response = await includeCallback(editId, {
          ...data,
          imageId: id,
        });
      } else {
        response = await includeCallback({
          ...data,
          imageId: id,
        });
      }

      if (includeRoadmapInTeam) {
        await includeRoadmapInTeam({
          name: "Roadmap",
          description: "Descrição",
          imageId: imageResponse.data.id,
          teamId: response.data.id,
        });
      }

      setAnimationController(false);

      navigate(`${redirectUrl}/${response.data.id}`);
    } catch (e) {
      setAnimationController(false);
    }
  };

  const openModalClick = () => {
    setIsModalOpen(!isModalOpen);
  };

  const handleStarClick = (rating) => {
    const ratingValue = rating + 1;
    setRating(ratingValue);
    setData({ ...data, name: ratingValue });
  };

  return (
    <>
      <button className={`action ${children} trilha`} onClick={openModalClick}>
        Criar {children}
      </button>
      <Modal
        children={
          <div className="modal-box">
            <div
              onChange={(Event) => handleChange(Event.target)}
              className="name-and-description"
            >
              {!fromVideo && (
                <>
                  <label htmlFor="name">Nome</label>
                  <input type="text" name="name" />
                </>
              )}

              <label htmlFor="description">Descrição</label>
              <input type="text" name="description" />
            </div>
            {fromVideo ? (
              <StarRating filled={rating} onClick={handleStarClick} />
            ) : (
              <div
                onChange={(Event) => onInputFile(Event.target)}
                className="input-file"
              >
                <label htmlFor="inputFile">{`Selecione uma imagem para seu ${children}`}</label>
                <input type="file" />
              </div>
            )}
            {!animationController && (
              <button onClick={handleClick} className="submit-button">
                Enviar
              </button>
            )}
            {animationController && <Loading />}
          </div>
        }
        openModalClick={openModalClick}
        isModalOpen={isModalOpen}
      />
    </>
  );
};
