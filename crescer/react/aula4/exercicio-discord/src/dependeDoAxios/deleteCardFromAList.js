import {
  queryString,
  findCardsByName,
  deletaCardsPelaID,
  hexadecimalColorGenerator,
} from "../discordBot";

import axios from "axios";
const deletaCardsPelaIDAxios = async (id) => {
  if (id) {
    return await axios.delete(
      `https://api.trello.com/1/cards/${id}?${queryString.key}&${queryString.token}`
    );
  }
};

export const deleteCardFromAList = async (MessageEmbed, msg) => {
  const msgSplit = msg.content.split(" ");
  const firstWordOfMessage = [...msgSplit].shift().toLowerCase();
  const inputWord = [...msgSplit]
    .filter((word) => firstWordOfMessage != word)
    .join(" ")
    .toLowerCase();
  if (firstWordOfMessage === "!delete" && !msg.author.bot) {
    const findCardsByNameArray = await axios({
      url: `https://api.trello.com/1/boards/${queryString.id}/cards?${queryString.key}&${queryString.token}`,
    });
    const idENome = await findCardsByName(inputWord, findCardsByNameArray);
    const deletaCardsPelaIDArray = await deletaCardsPelaIDAxios(idENome.id);
    const deleteResponse = await deletaCardsPelaID(
      idENome,
      deletaCardsPelaIDArray
    );

    const embed = new MessageEmbed()
      .setTitle(deleteResponse)
      .setColor(hexadecimalColorGenerator())
      .setDescription("");
    msg.channel.send(embed);
  }
};
