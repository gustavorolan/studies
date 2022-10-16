import "./radio-button.style.css";

export const RadioButton = ({
  label,
  name,
  value,
  onChange,
  selectedValue,
}) => {
  const handleChange = (event) => {
    onChange(event);
  };

  const isChecked = value === selectedValue;

  return (
    <label
      className={`radio-button__label ${
        isChecked && "radio-button__selecionado"
      }`}
      key={name}
    >
      <input
        type="radio"
        value={value}
        name={name}
        className="radio-button__caixa-radio"
        onChange={handleChange}
      />
      {label}
    </label>
  );
};

RadioButton.defaultProps = {
  name: "",
  label: "",
  isChecked: false,
};
