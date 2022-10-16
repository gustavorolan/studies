import {
  queryString,
  findIdListByName,
  criaCardsPelaID,
  hexadecimalColorGenerator,
} from "../discordBot";
import axios from "axios";

export const adicionaCardToListAxios = async (nomeCard, description, id) => {
  const response = await axios.post(
    `https://api.trello.com/1/cards?idList=${id}&${queryString.key}&${queryString.token}`,
    {
      name: `${nomeCard}`,
      desc: `${description}`,
    }
  );
  return response;
};

export const adicionaCardToList = async (MessageEmbed, msg) => {
  const msgSplit = msg.content.split("  ");
  const firstWordOfMessage = [...msgSplit].shift().toLowerCase();
  const { list, nome, desc } = [...msgSplit]
    .filter((palavra) => palavra != "create")
    .map((palavra) => {
      const palavraSeparada = palavra.split(":");
      return { [palavraSeparada[0]]: palavraSeparada[1] };
    })
    .reduce((acc, atributosParaCriacao) => {
      return { ...acc, ...atributosParaCriacao };
    }, {});
  if ((firstWordOfMessage === "!create") & !msg.author.bot) {
    const findIdListByNameArray = await axios({
      url: `https://api.trello.com/1/boards/${queryString.id}/lists?${queryString.key}&${queryString.token}`,
    });
    const listId = await findIdListByName(list, findIdListByNameArray);
    const arrayPostAxios = await adicionaCardToListAxios(nome, desc, listId);
    const deleteResponse = await criaCardsPelaID(listId, nome, arrayPostAxios);
    const embed = new MessageEmbed()
      .setTitle(deleteResponse)
      .setColor(hexadecimalColorGenerator())
      .setDescription("");
    msg.channel.send(embed);
  }
};
