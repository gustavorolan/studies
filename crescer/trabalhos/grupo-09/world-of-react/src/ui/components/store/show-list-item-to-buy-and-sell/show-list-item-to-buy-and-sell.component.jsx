import { Button } from "../../button/button.component";
import { BuyButton } from "../buy-button/buy-button.component";
import "./show-list-item-to-buy-and-sell.style.css";

export const ShowListItemToBuyAndSell = ({
  id,
  name,
  image,
  price,
  sellPrice,
  handleClickDetails,
  handleSell,
  character,
  setError,
}) => {
  const idListMyItems = character.items.map(({ id }) => id);

  return (
    <div className="show-list-item-to-buy-and-sell__store-item">
      <button onClick={() => handleClickDetails(id)}>
        <h1>{name}</h1>
        <img src={image} alt="foto do item" />
        <p>Preço: {price}</p>
        <p>Preço de Venda: {sellPrice}</p>
      </button>
      <div>
        {idListMyItems.includes(id) ? (
          <Button onClick={() => handleSell(id)}>vender</Button>
        ) : (
          <BuyButton setError={setError} id={id} />
        )}
      </div>
    </div>
  );
};
