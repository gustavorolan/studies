import { hexadecimalColorGenerator } from "../discordBot";

export const helper = async (MessageEmbed, msg) => {
  if (msg.content === "!help" && !msg.author.bot) {
    const embed = new MessageEmbed()
      .setTitle("Lista de Comandos")
      .setColor(hexadecimalColorGenerator())
      .setDescription(
        "1.!Trello \n 2.!List nomeLista \n3.!Create  list:to do  nome:NomeCard  desc:descrição texto \n4.!Delete nomeCard \n5.!Ping\n"
      );
    msg.channel.send(embed);
  }
};
