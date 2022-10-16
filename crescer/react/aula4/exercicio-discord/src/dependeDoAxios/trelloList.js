import { queryString, trelloMain } from "../discordBot";
import axios from "axios";

export const trelloList = async (MessageEmbed, msg) => {
  const trelloMainArray = await axios({
    url: `https://api.trello.com/1/boards/${queryString.id}/lists?${queryString.key}&${queryString.token}`,
  });

  if (msg.content === "!trello" && !msg.author.bot) {
    const lists = await trelloMain(trelloMainArray);
    lists.forEach(({ name, cor }) => {
      const embed = new MessageEmbed().setTitle(name).setColor(cor);
      msg.channel.send(embed);
    });
  }
};
