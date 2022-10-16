export const trocaDeCasa = (
  casasMagicasComListaDeNomes,
  nomeInput,
  nomeCasaInput
) => {
  const { grifinoria, sonseria, corvinal, lufaLufa } =
    casasMagicasComListaDeNomes;
  const newArrayCasasMagicasComListaDeNomes = {
    grifinoria: [...grifinoria],
    sonseria: [...sonseria],
    corvinal: [...corvinal],
    lufaLufa: [...lufaLufa],
  };

  const casasDisponiveis = [
    "grifinoria",
    "sonseria",
    "corvinal",
    "lufaLufa",
  ].filter((nomeCasa) => nomeCasa !== nomeCasaInput);
  const nome = newArrayCasasMagicasComListaDeNomes[nomeCasaInput].find(
    (nome) => nome === nomeInput
  );
  newArrayCasasMagicasComListaDeNomes[nomeCasaInput] =
    newArrayCasasMagicasComListaDeNomes[nomeCasaInput].filter(
      (nome) => nome !== nomeInput
    );
  const casaRandom = parseInt(Math.random() * casasDisponiveis.length);
  const casaSelecionada = casasDisponiveis[casaRandom];
  newArrayCasasMagicasComListaDeNomes[casaSelecionada].push(nome);

  return newArrayCasasMagicasComListaDeNomes;
};
