import { useGlobalCharacter } from "../../../../context";
import { useCharactersApi } from "../../../../hooks/api";
import { useStoreApi } from "../../../../hooks/api/store/use-store-api.hook";
import { Button } from "../../button/button.component";

export const BuyButton = ({ id, setError }) => {
  const { buyItemsStore } = useStoreApi();
  const { getCharacterById } = useCharactersApi();
  const [character, setCharacter] = useGlobalCharacter();

  const buyItems = async () => {
    try {
      await buyItemsStore(id);
      const responseCharacter = await getCharacterById(character.id);
      setCharacter({ ...character, ...responseCharacter });
    } catch (error) {
      setError("Algo Inesperado Ocorreu");
      setTimeout(() => {
        setError(null);
      }, 2500);
    }
  };

  return <Button onClick={buyItems}>comprar</Button>;
};
