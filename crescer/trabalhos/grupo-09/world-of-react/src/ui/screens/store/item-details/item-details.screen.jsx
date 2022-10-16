import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useGlobalCharacter } from "../../../../context";
import { useStoreApi } from "../../../../hooks/api/store/use-store-api.hook";
import { Button, Loader, ToastError } from "../../../components";
import { BuyButton } from "../../../components/store/buy-button/buy-button.component";
import "./item-details.style.css";

export const ItemsDetails = () => {
  const { getItemDetailStore } = useStoreApi();
  const [itemDetail, setItemDetail] = useState({});
  const { id } = useParams();
  const navigate = useNavigate();
  const [character] = useGlobalCharacter();
  const idListItemsBought = character.items.map((item) => item.id);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const getStoreApi = async () => {
      try {
        setLoading(true);
        const response = await getItemDetailStore(id);
        setLoading(false);
        setItemDetail(response);
      } catch (error) {
        setError("Algo Inesperado Ocorreu");
      }
    };
    getStoreApi();
  }, [getItemDetailStore, id]);

  const handleBack = () => {
    navigate("/store");
  };
  const handleClickToastError = () => {
    setError(null);
  };
  return (
    <div className="item-details__container">
      <div>
        {!!error && (
          <ToastError
            errorMessage={error}
            onCloseToast={handleClickToastError}
          />
        )}
      </div>
      <div className="item-details__items">
        {itemDetail?.image && (
          <div>
            <Button onClick={handleBack}>Loja</Button>
            <img src={itemDetail?.image} alt="imagem da item" />
          </div>
        )}
        {itemDetail?.image && (
          <div className="item-details__container-text">
            <h1> {itemDetail?.name}</h1>
            <p>Preço: {itemDetail?.price}</p>
            <p>Preço De Venda{itemDetail?.sellPrice}</p>
            <p>
              Expansão necessária:{" "}
              {itemDetail.expansionId ? itemDetail?.expansionId : `Nenhuma`}
            </p>
            <p>Tipo: {itemDetail?.type}</p>
            <p>Aprimoramento: {itemDetail?.enhancement}</p>
            {!idListItemsBought.includes(parseInt(id)) && (
              <BuyButton id={id} setError={setError} />
            )}
          </div>
        )}
      </div>
      <div className="item-details__loader-dois"> {loading && <Loader />}</div>
    </div>
  );
};
