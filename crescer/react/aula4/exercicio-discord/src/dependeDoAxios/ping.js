export const ping = async (msg) => {
  // If the message is "ping"
  if (msg.content === "!ping" && !msg.author.bot) {
    // Send "pong" to the same channel
    msg.channel.send("pong");
  }
};
