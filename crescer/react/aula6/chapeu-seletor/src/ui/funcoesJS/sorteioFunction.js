export const sortear = (arraySortear) => {
  const casasMagicas = ["grifinoria", "sonseria", "corvinal", "lufaLufa"];
  let newArrayParaSorteio = [...arraySortear];

  const arrayRetorno = [];
  newArrayParaSorteio.map((nome, index) => {
    if (!arrayRetorno[index % casasMagicas.length])
      arrayRetorno[index % casasMagicas.length] = [];
    const random = parseInt(Math.random() * newArrayParaSorteio.length);
    const sorteado = newArrayParaSorteio[random];
    arrayRetorno[index % casasMagicas.length].push(sorteado);
    newArrayParaSorteio = newArrayParaSorteio.filter(
      (nome) => nome !== sorteado
    );
  });
  const arrayComListasSeparada = arrayRetorno.reduce(
    (acumulador, nomes, index) => {
      return { ...acumulador, [casasMagicas[index]]: nomes };
    },
    {}
  );
  return arrayComListasSeparada;
};
