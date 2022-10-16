import { adicionaCardToList } from "./adicionaCardToList";
import { deleteCardFromAList } from "./deleteCardFromAList";
import { helper } from "./helper";
import { ping } from "./ping";
import { retornaListaDeCards } from "./retornaListaDeCards";
import { trelloList } from "./trelloList";

export const spamBoot = async (client, MessageEmbed) => {
  const arrayDeMensagens = [];
  let booleanBot = false;

  client.on("message", async (msg) => {
    const msgSplit = msg.content.split(" ");
    const firstWordOfMessage = [...msgSplit].shift().toLowerCase();
    const isItCommand =
      firstWordOfMessage === "!trello" ||
      firstWordOfMessage === "!create" ||
      firstWordOfMessage === "!delete" ||
      firstWordOfMessage === "!list" ||
      firstWordOfMessage === "!ping" ||
      firstWordOfMessage === "!help";

    const spanControl = () => {
      if (arrayDeMensagens.length < 3) {
        return false;
      } else {
        return (
          Math.abs(
            arrayDeMensagens[arrayDeMensagens.length - 1] -
              arrayDeMensagens[arrayDeMensagens.length - 3]
          ) <= 5000
        );
      }
    };

    if (isItCommand && !msg.author.bot && !booleanBot) {
      const dataHoraMessage = new Date(msg.createdTimestamp);
      arrayDeMensagens.push(dataHoraMessage.getTime());
      if (spanControl()) {
        msg.channel.send("bootIsBlocked");
        booleanBot = true;
        setTimeout(() => {
          msg.channel.send("botAtivado");
        }, 10000);
        booleanBot = false;
      } else {
        await trelloList(MessageEmbed, msg);
        await retornaListaDeCards(MessageEmbed, msg);
        await deleteCardFromAList(MessageEmbed, msg);
        await adicionaCardToList(MessageEmbed, msg);
        await ping(MessageEmbed, msg);
        await helper(MessageEmbed, msg);
      }
    }
  });
};
