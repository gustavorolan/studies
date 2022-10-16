import "./button.style.css";

export function Button({ value, onClick, children, type, isDisabled }) {
  return (
    <div className="buttonComponent">
      <button disabled={isDisabled} type={type} value={value} onClick={onClick}>
        {children}
      </button>
    </div>
  );
}
