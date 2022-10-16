import React, { useState } from "react";

import { timeOutNull } from "../../../core/functions";
import { useRidesApi } from "../../../hooks/api";
import { Button } from "../button/button.component";
import { Input } from "../Input/input.component";

import "./moneyPrinter.style.css";

export const MoneyPrinter = ({
  setError,
  setPassenger,
  passenger,
  setSuccess,
}) => {
  const [requestMoneyToAdd, setRequestMoneyToAdd] = useState(null);
  const { moneyPrinterPassenger } = useRidesApi();

  const handleChange = ({ value }) => {
    setRequestMoneyToAdd(value);
  };

  const handleClickToPutMoney = async () => {
    try {
      const response = await moneyPrinterPassenger({
        passengerId: passenger.passengerId,
        moneyToAdd: requestMoneyToAdd,
      });
      setPassenger(null);
      setSuccess(response.moneyResponse);
      timeOutNull(setSuccess);
    } catch (error) {
      setError(error?.response?.data?.message);
      timeOutNull(setError);
    }
  };

  return (
    <>
      <div className="moneyPrinter">
        <h1>{passenger.namePassenger}</h1>
        <div onChange={(Event) => handleChange(Event.target)}>
          <Input />
        </div>
        <div>
          <Button children="Depositar" onClick={handleClickToPutMoney} />
        </div>
      </div>
    </>
  );
};
