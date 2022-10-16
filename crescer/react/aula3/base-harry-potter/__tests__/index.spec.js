import { criarBruxo, criarTrouxa, falarMateriasQueEstuda } from "../src";

describe("Teste index", () => {
  it("Deve retornar as matérias que o bruxo estuda", () => {
    const arrayMaterias = ["mat", "geo", "cal"];
    const resultadoEsperado = "materias: mat, geo, cal.";
    const fala = falarMateriasQueEstuda(arrayMaterias);
    expect(fala).toEqual(resultadoEsperado);
  });
  it("Deve retornar o bruxo", () => {
    const entrada = ["gustavo", "magica"];
    const arrayMaterias = ["mat", "geo", "cal"];
    const resultadoEsperado = {
      nome: "gustavo",
      casaMagica: "magica",
      materiasDeEstudo: ["mat", "geo", "cal"],
    };
    const { nome, casaMagica, materiasDeEstudo } = criarBruxo(
      entrada[0],
      entrada[1],
      arrayMaterias
    );
    console.log({ nome, casaMagica, materiasDeEstudo });
    expect({ nome, casaMagica, materiasDeEstudo }).toEqual(resultadoEsperado);
  });
  it("Deve retornar o bruxo", () => {
    const entrada = ["gustavo"];
    const arrayMaterias = ["mat", "geo", "cal"];
    const resultadoEsperado = {
      nome: "gustavo",
      materiasDeEstudo: ["mat", "geo", "cal"],
    };
    const { nome, materiasDeEstudo } = criarTrouxa(entrada[0], arrayMaterias);
    expect({ nome, materiasDeEstudo }).toEqual(resultadoEsperado);
  });
  it("Deve retornar as materias do bruxo", () => {
    const entrada = ["gustavo", "magica"];
    const arrayMaterias = ["mat", "geo", "cal"];
    const resultadoEsperado = "materias: mat, geo, cal.";
    const bruxo = criarBruxo(entrada[0], entrada[1], arrayMaterias);
    const falaBruxo = bruxo.falarMateriasQueEstuda();
    expect(falaBruxo).toEqual(resultadoEsperado);
  });
  it("Deve retornar as materias do bruxo", () => {
    const entrada = ["gustavo", "magica"];
    const arrayMaterias = ["mat", "geo", "cal"];
    const resultadoEsperado = "materias: mat, geo, cal.";
    const Trouxa = criarTrouxa(entrada[0], arrayMaterias);
    const falaTrouxa = Trouxa.falarMateriasQueEstuda();
    expect(falaTrouxa).toEqual(resultadoEsperado);
  });
});
