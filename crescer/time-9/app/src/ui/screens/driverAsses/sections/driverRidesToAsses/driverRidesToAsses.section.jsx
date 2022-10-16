export const DriverRidesToAsses = ({ rides, handleClick }) => {
  return (
    <div className="ShowListOfPassangersContainer">
      <div className="passengersContainer">
        {rides.map((ride, index) => (
          <div key={index} className="nameButton">
            <button onClick={() => handleClick(ride)}>
              <p>{ride.passenger.namePassenger}</p>
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};
