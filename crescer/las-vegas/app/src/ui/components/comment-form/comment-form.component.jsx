import "./comment-form.css";
import { useState } from "react";
import { useCommentApi } from "../../../hooks/api/comment/use-comment-api";
import { useImageApi } from "../../../hooks/api/image/use-image-api";

const formData = new FormData();
export const CommentForm = ({
  idDoubt,
  reloadDoubt,
  closeModal,
  showToast,
}) => {
  const [comment, setComment] = useState("");
  const [haveImage, setHaveImage] = useState(false);

  const { makeACommentInDoubt } = useCommentApi();
  const { includeImage } = useImageApi();

  const handleChange = (event) => {
    const { value } = event.target;
    setComment(value);
  };

  const onInputFile = (target) => {
    formData.append("file", target.files[0]);
    setHaveImage(true);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (comment.length >= 10 && comment.length <= 255) {
      try {
        if (haveImage) {
          const imageResponse = await includeImage(formData);
          await makeACommentInDoubt(comment, idDoubt, imageResponse.data.id);
          setHaveImage(false);
        } else {
          await makeACommentInDoubt(comment, idDoubt);
        }
        setComment("");
        reloadDoubt();
        showToast("sucess", "Comentário publicado com sucesso");
        closeModal();
      } catch (error) {
        showToast("error", error.message);
      }
    } else {
      showToast("warning", "Deve conter entre 10 e 255 caracteres");
    }
  };

  return (
    <form
      action="submit"
      onSubmit={handleSubmit}
      className="forum-post-form-box commentForm"
    >
      <label htmlFor="doubtTextArea">Publique seu comentário</label>
      <textarea
        name="doubt"
        id="doubtTextArea"
        cols="30"
        rows="10"
        value={comment}
        onChange={handleChange}
      />
      <input type="file" onChange={(Event) => onInputFile(Event.target)} />
      <button type="submit" className="submit-comment">
        Publicar comentário
      </button>
    </form>
  );
};
