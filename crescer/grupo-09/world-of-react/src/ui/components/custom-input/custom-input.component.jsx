import './custom-input.style.css'

export const CustomInput = ({ onChange, type, name, value, label, width }) => {
  const widthInput = width || "100%";

  return (
    <div className="custom-input" style={{ width: widthInput }}>
      <label className="custom-input__label" htmlFor={name}>
        {label}
      </label>
      <input
        type={type}
        className="custom-input__input"
        value={value}
        onChange={onChange}
        name={name}
      />
    </div>
  );
};
