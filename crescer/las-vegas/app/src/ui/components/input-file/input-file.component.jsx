import React from "react";
import FormData from "form-data";
import { Loading } from "../index";

const formData = new FormData();
export const InputVideoFile = ({ children, callback }) => {
  const [data, setData] = React.useState({
    name: "",
    desc: "",
  });

  const [animationController, setAnimationController] = React.useState(false);

  const handleChange = ({ name, value }) => {
    setData({ ...data, [name]: value });
  };

  const onInputFile = (target) => {
    formData.append("file", target.files[0]);
    formData.append("name", data.name);
    formData.append("desc", data.desc);
  };

  const handleClick = async () => {
    try {
      setAnimationController(true);
      await callback(formData);
      setAnimationController(false);
    } catch (e) {
      setAnimationController(false);
    }
  };

  return (
    <div>
      <div onChange={(Event) => handleChange(Event.target)}>
        <label htmlFor="name">Nome</label>
        <input type="text" name="name" />
        <label htmlFor="desc">desc</label>
        <input type="text" name="desc" />
      </div>
      <div onChange={(Event) => onInputFile(Event.target)}>
        <label htmlFor="inputFile">{children}</label>
        <input type="file" />
      </div>
      {!animationController && <button onClick={handleClick}>Enviar</button>}
      {animationController && <Loading />}
    </div>
  );
};
