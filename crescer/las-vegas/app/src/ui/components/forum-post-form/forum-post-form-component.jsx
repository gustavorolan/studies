import "./forum-post-form.css";
import { useState } from "react";
import { useDoubtApi } from "../../../hooks/api/doubt/use-doubt-api";
import { useImageApi } from "../../../hooks/api/image/use-image-api";

const formData = new FormData();
export const ForumPostForm = ({ reloadDoubts, showToast, closeModal }) => {
  const [doubt, setDoubt] = useState("");
  const [haveImage, setHaveImage] = useState(false);

  const { makeADoubt } = useDoubtApi();
  const { includeImage } = useImageApi();

  const onInputFile = (target) => {
    formData.append("file", target.files[0]);
    setHaveImage(true);
  };

  const handleChange = (event) => {
    const { value } = event.target;
    if (doubt.length <= 255) {
      setDoubt(value);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (doubt.length >= 10 && doubt.length <= 255) {
      try {
        if (haveImage) {
          const imageResponse = await includeImage(formData);
          await makeADoubt(doubt, imageResponse.data.id);
          setHaveImage(false);
        } else {
          await makeADoubt(doubt);
        }
        setDoubt("");
        reloadDoubts();
        showToast("sucess", "Dúvida publicada com sucesso");
        closeModal();
      } catch (error) {
        showToast("error", "Erro ao publicar dúvida");
      }
    } else {
      showToast("warning", "Deve conter entre 10 e 255 caracteres");
    }
  };

  return (
    <>
      <form
        action="submit"
        className="forum-post-form-box"
        onSubmit={handleSubmit}
      >
        <label htmlFor="doubtTextArea">Publique sua dúvida</label>
        <textarea
          name="doubt"
          id="doubtTextArea"
          cols="30"
          rows="10"
          value={doubt}
          onChange={handleChange}
          placeholder="Digite sua dúvida (max 255 caracteres)"
        />
        <input type="file" onChange={(Event) => onInputFile(Event.target)} />
        <button type="submit">Enviar dúvida</button>
      </form>
    </>
  );
};
