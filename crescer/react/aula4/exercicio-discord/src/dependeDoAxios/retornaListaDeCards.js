import {
  queryString,
  findIdListByName,
  returnsAllTheCardsFromList,
} from "../discordBot";
import axios from "axios";

export const retornaListaDeCards = async (MessageEmbed, msg) => {
  const findIdListByNameArray = await axios({
    url: `https://api.trello.com/1/boards/${queryString.id}/lists?${queryString.key}&${queryString.token}`,
  });

  const returnsAllTheCardsFromListArray = await axios({
    url: `https://api.trello.com/1/boards/${queryString.id}/cards?${queryString.key}&${queryString.token}`,
  });

  const msgSplit = msg.content.split(" ");
  const firstWordOfMessage = [...msgSplit].shift().toLowerCase();
  const inputWord = [...msgSplit]
    .filter((word) => firstWordOfMessage != word)
    .join(" ")
    .toLowerCase();
  if (firstWordOfMessage === "!list" && !msg.author.bot) {
    const idList = await findIdListByName(inputWord, findIdListByNameArray);
    const cards = await returnsAllTheCardsFromList(
      idList,
      returnsAllTheCardsFromListArray
    );
    cards.forEach(({ name, desc, cor }) => {
      const embed = new MessageEmbed()
        .setTitle(name)
        .setColor(cor)
        .setDescription(desc);
      msg.channel.send(embed);
    });
  }
};
