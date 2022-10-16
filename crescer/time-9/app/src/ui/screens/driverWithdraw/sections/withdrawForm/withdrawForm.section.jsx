import { useEffect, useState } from "react";
import { Input, Button } from "../../../../component";
import "./withdrawForm.style.css";

export const WithdrawForm = ({ driver, onWithdraw,setSelectedDriver }) => {
  const [withdrawValue, setWithdrawValue] = useState(driver.balance);

  useEffect(() => {
    setWithdrawValue(driver.balance);
  }, [driver]);

  const handleSubmit = (event) => {
    event.preventDefault();
    onWithdraw(driver.driverId, withdrawValue);
  };

  const handleChange = (event) => {
    event.preventDefault();
    const { value } = event.target;
    setWithdrawValue(value);
  };
  
  const handleClose=()=>{
    setSelectedDriver({})
  }

  return (
    <div className="drawInput">
      <form onSubmit={handleSubmit}>
        <p>Disponivel: {driver.balance}</p>

        <label htmlFor="withdraw">Quantidade</label>

        <Input
          type="number"
          name="withdraw"
          value={withdrawValue}
          onChange={handleChange}
        />

        <div>
          <Button>Sacar</Button>
        </div>
      </form>
      <div onClick={handleClose} className="buttonClose"><Button>x</Button></div>
    </div>
  );
};
