import {
  findIdListByName,
  hexadecimalColorGenerator,
  trelloMain,
  returnsAllTheCardsFromList,
  findCardsByName,
  deletaCardsPelaID,
  criaCardsPelaID,
} from "../src/discordBot";

describe("criaCardsPelaID", () => {
  //
  // retorna se foi possivel deletar o card
  //
  it("deve retornar que criou o card", () => {
    const { id, name } = {
      id: "0",
      name: "card",
    };
    const axiosArray = {
      status: 200,
    };
    const respostaDelete = criaCardsPelaID(id, name, axiosArray);
    const resultadoEsperado = "Você criou o card card";
    expect(respostaDelete).toEqual(resultadoEsperado);
  });
  it("deve retornar que nao criou o card", () => {
    const { id, name } = {
      id: "0",
      name: "card",
    };
    const axiosArray = {
      status: 400,
    };
    const respostaDelete = criaCardsPelaID(id, name, axiosArray);
    const resultadoEsperado = "Não foi possível criar o card card";
    expect(respostaDelete).toEqual(resultadoEsperado);
  });
  it("deve retornar que lista nao existe", () => {
    const { id, name } = {
      id: "",
      name: "card",
    };
    const axiosArray = {
      status: 200,
    };
    const respostaDelete = criaCardsPelaID(id, name, axiosArray);
    const resultadoEsperado = "essa lista não existe";
    expect(respostaDelete).toEqual(resultadoEsperado);
  });
});

describe("deletaCardsPelaID", () => {
  //
  // retorna se foi possivel deletar o card
  //
  it("deve retornar que deletou o card", () => {
    const inputTest = {
      id: "0",
      name: "card",
    };
    const axiosArray = {
      status: 200,
    };
    const respostaDelete = deletaCardsPelaID(inputTest, axiosArray);
    const resultadoEsperado = "Você Deletou o card card";
    expect(respostaDelete).toEqual(resultadoEsperado);
  });
  it("Deve retornar que n foi possivel deletar o card", () => {
    const inputTest = {
      id: "0",
      name: "card",
    };
    const axiosArray = {
      status: 400,
    };
    const respostaDelete = deletaCardsPelaID(inputTest, axiosArray);
    const resultadoEsperado = "Não foi possível deletar card";
    expect(respostaDelete).toEqual(resultadoEsperado);
  });
  it("Deve retornar que o id do card não existe", () => {
    const inputTest = {
      id: "",
      name: "card",
    };
    const axiosArray = {
      status: 400,
    };
    const respostaDelete = deletaCardsPelaID(inputTest, axiosArray);
    const resultadoEsperado = "Card não existe";
    expect(respostaDelete).toEqual(resultadoEsperado);
  });
});

describe("findCardsByName", () => {
  //
  // retorna o card de uma lista de cards
  //
  it("Deve retornar  o cards de um nome passado", () => {
    const inputTest = {
      data: [
        {
          id: 0,
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          id: 1,
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          id: 2,
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
      ],
    };
    const card = findCardsByName("qualquerNome", inputTest);
    const resultadoEsperado = {
      id: 0,
      name: "qualquerNome",
    };
    expect(card).toEqual(resultadoEsperado);
  });

  it("Deve retornar  o cards vazio", () => {
    const inputTest = {
      data: [
        {
          id: 0,
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          id: 1,
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          id: 2,
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
      ],
    };
    const card = findCardsByName("qualquer", inputTest);
    const resultadoEsperado = {
      id: "",
      name: "",
    };
    expect(card).toEqual(resultadoEsperado);
  });
});

describe("returnsAllTheCardsFromList", () => {
  //
  // retorna o id de uma lista pelo nome
  //
  it("Deve retornar todos os cards de uma lista", () => {
    const inputTest = {
      data: [
        {
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
      ],
    };
    const cards = returnsAllTheCardsFromList(1, inputTest).map(
      ({ cor, ...cardsSemCor }) => cardsSemCor
    );
    expect(cards).toEqual(inputTest.data);
  });

  it("Deve retornar o uma string vazia", () => {
    const inputTest = {
      data: [
        {
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
        {
          name: "qualquerNome",
          idList: 1,
          desc: "aaa",
        },
      ],
    };
    const resultadoEsperado = [
      { name: "This list does not exist", cor: "0xff0000", desc: "" },
    ];
    const cards = returnsAllTheCardsFromList(0, inputTest);
    expect(cards).toEqual(resultadoEsperado);
  });
});

describe("findIdListByName", () => {
  //
  // retorna o id de uma lista pelo nome
  //
  it("Deve retornar o id de uma lista", () => {
    const inputTest = {
      data: [
        {
          id: 0,
          name: "qualquerNome",
        },
      ],
    };
    const id = findIdListByName("qualquerNome", inputTest);
    expect(id).toEqual(inputTest.data[0].id);
  });

  it("Deve retornar o uma string vazia", () => {
    const inputTest = {
      data: [
        {
          id: 0,
          name: "qualquerNome",
        },
      ],
    };
    const id = findIdListByName("s", inputTest);
    expect(id).toEqual("");
  });
});

describe("trelloMain", () => {
  //
  // retorna a lista de nomes e iuma cor
  //
  it("Deve retornar um nome e uma cor", () => {
    const inputTest = {
      data: [
        {
          name: "qualquerNome",
        },
      ],
    };
    const retorno = trelloMain(inputTest);
    const verificaSeCorValida =
      0 < parseInt(retorno[0].cor.replace("#", ""), 16) &&
      parseInt(retorno[0].cor.replace("#", ""), 16) < 16777215;
    const resultadoRetorno = [retorno[0].name, verificaSeCorValida];
    const resultadoEsperado = ["qualquerNome", true];

    expect(resultadoRetorno).toEqual(resultadoEsperado);
  });
});

describe("hexadecimal function", () => {
  //
  // Retorna um hexadecimal aleatorio
  //

  it("Deve retornar um numero hexadecimal dentro do padrão", () => {
    const hexadecimal = hexadecimalColorGenerator().replace("#", "");
    const resultadoEsperado = true;
    expect(
      0 < parseInt(hexadecimal, 16) && parseInt(hexadecimal, 16) < 16777215
    ).toEqual(resultadoEsperado);
  });
});
