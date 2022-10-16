import Discord from "discord.js";
import { adicionaCardToList } from "./src/dependeDoAxios/adicionaCardToList";
import { deleteCardFromAList } from "./src/dependeDoAxios/deleteCardFromAList";
import { retornaListaDeCards } from "./src/dependeDoAxios/retornaListaDeCards";
import { spamBoot } from "./src/dependeDoAxios/spamBoot";
import { trelloList } from "./src/dependeDoAxios/trelloList";

const token = "OTQ3MTg1NTI0NTg0NjI0MTI5.YhplQg.P5fO9kOY6iIxJCOuFe2X-UBNcLQ";
const client = new Discord.Client();
const { MessageEmbed } = require("discord.js");

spamBoot(client, MessageEmbed);

client.on("ready", async () => {
  const canalGeral = client.channels.cache.get("947186935951159388");
  canalGeral.send("Cheguei");
});

client.login(token);
