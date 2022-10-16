export const ShowListOfDrivers = ({ drivers, handleClick }) => {
  return (
    <div className="ShowListOfPassangersContainer">
      <div className="passengersContainer">
        {drivers.map((driver) => (
          <div className="nameButton" key={driver.driverId}>
            <button onClick={() => handleClick(driver)}>
              <p>{driver.nameDriver}</p>
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};
