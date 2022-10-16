export const queryString = {
  key: "key=22d0b1ddff3e24b434bc536897972164",
  token:
    "token=180e3348a500aa290f3f88f6171066ad21cc926644f0d13c78322bde3f03038f",
  id: "jopkg4zN",
};

export const trelloMain = (axiosArray) => {
  const { data } = axiosArray;
  const arrayDeNomes = data.map((item) => {
    return { name: item.name, cor: hexadecimalColorGenerator() };
  });

  return arrayDeNomes;
};
export const findIdListByName = (nameList, axiosArray) => {
  const { data } = axiosArray;
  const listIds = data.map(({ id, name }) => {
    return { id, name };
  });
  const idNecessaria = listIds.find(
    (lista) => lista.name.toLowerCase() === nameList.toLowerCase()
  );
  return idNecessaria ? idNecessaria.id : "";
};
export const returnsAllTheCardsFromList = (id, axiosArray) => {
  const { data } = axiosArray;
  const doesNotExist = [
    { name: "This list does not exist", cor: "0xff0000", desc: "" },
  ];
  const cards = data
    .filter((card) => card.idList === id)
    .map((card) => {
      const { name, desc, idList } = card;
      return {
        name,
        desc,
        idList,
        cor: hexadecimalColorGenerator(),
      };
    });
  return cards.length === 0 ? doesNotExist : cards;
};

export const findCardsByName = (nome, axiosArray) => {
  const { data } = axiosArray;
  const arrayDeNomeEId = data.map(({ name, id }) => {
    return { name, id };
  });
  const idENome = arrayDeNomeEId.find(
    ({ name }) => nome.toLowerCase() === name.toLowerCase()
  );
  return idENome ? idENome : { name: "", id: "" };
};

export const deletaCardsPelaID = (idENome, axiosArray) => {
  const { name, id } = idENome;
  if (id != "") {
    const { status } = axiosArray;
    return status === 200
      ? `Você Deletou o card ${name}`
      : `Não foi possível deletar ${name}`;
  } else return "Card não existe";
};

export const criaCardsPelaID = (id, nomeCard, axiosArray) => {
  if (id != "") {
    const { status } = axiosArray;
    return status === 200
      ? `Você criou o card ${nomeCard}`
      : `Não foi possível criar o card ${nomeCard}`;
  } else return "essa lista não existe";
};

export const hexadecimalColorGenerator = () => {
  return (
    "#" +
    parseInt(Math.random() * 0xffffff)
      .toString(16)
      .padStart(6, "0")
  );
};
