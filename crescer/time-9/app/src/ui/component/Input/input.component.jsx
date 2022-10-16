import "./input.style.css";

export function Input({ name, placeholder, type = "text", value, onChange }) {
  return (
    <div className="inputComponent">
      <input
        type={type}
        name={name}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
    </div>
  );
}
