export const falarMateriasQueEstuda = (materiasDeEstudo) => {
  return `materias: ${materiasDeEstudo.reduce((acc, cv) => `${acc}, ${cv}`)}.`;
};

export const materiasEstudo = (nome, materiasDeEstudo) => {
  return {
    ...nome,
    materiasDeEstudo,
    falarMateriasQueEstuda: () => falarMateriasQueEstuda(materiasDeEstudo),
  };
};

export const criarBruxo = (nome, casaMagica, arrayMaterias) => {
  return materiasEstudo({ nome, casaMagica }, arrayMaterias);
};
export const criarTrouxa = (nome, arrayMaterias) => {
  return materiasEstudo({ nome }, arrayMaterias);
};
