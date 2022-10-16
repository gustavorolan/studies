import "./button.style.css";

export function Button({ value, onClick, children, type, isDisabled }) {
  return (
    <button
      disabled={isDisabled}
      type={type || "submit"}
      value={value}
      className="button"
      onClick={onClick}
    >
      {children}
    </button>
  );
}

Button.defaultProps = {
  disabled: false,
};
