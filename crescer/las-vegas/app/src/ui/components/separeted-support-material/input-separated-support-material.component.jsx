import FormData from "form-data";
import { Loading, Modal } from "../index";
import { useState } from "react";

const formData = new FormData();

export const InputSeparatedSupportMaterial = ({
  children,
  includeCallback,
  id,
  fromVideo,
}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const [animationController, setAnimationController] = useState(false);

  const handleChange = ({ name, value }) => {
    formData.append("description", value);
  };

  const onInputFile = (target) => {
    formData.append("supportMaterial", target.files[0]);
  };

  const handleClick = async () => {
    try {
      setAnimationController(true);

      await includeCallback(formData, id);

      setAnimationController(false);
    } catch (e) {
      setAnimationController(false);
    }
  };

  const openModalClick = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <>
      <button className="header-button" onClick={openModalClick}>
        Criar {children}
      </button>
      <Modal
        children={
          <div className="modal-box">
            {!fromVideo && (
              <div
                onChange={(Event) => handleChange(Event.target)}
                className="name-and-description"
              >
                <label htmlFor="description">Descrição</label>
                <input type="text" name="description" />
              </div>
            )}
            <div
              onChange={(Event) => onInputFile(Event.target)}
              className="input-file"
            >
              <label htmlFor="inputFile">{`Selecione uma imagem para ${children}`}</label>
              <input type="file" />
            </div>
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
