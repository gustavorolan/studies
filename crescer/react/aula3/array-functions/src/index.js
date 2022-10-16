export const exercicio01 = numeros => numeros.filter(item => item % 2 == 0)

export const exercicio02 = numeros => numeros.some(item => item > 10)

export const exercicio03 = numeros =>
  numeros.reduce((acc, cv) => {
    return (acc = cv % 2 === 1 && acc)
  }, true)

export const exercicio04 = numeros => numeros.map(item => item * item)

export const exercicio05 = numeros => numeros.reduce((acc, cv) => acc + cv)

export const exercicio06 = numeros =>
  numeros.sort((b, a) => {
    if (a < b) return -1
    if (a > b) return +1
    return 0
  })

export const exercicio07 = numeros => numeros.find(item => item < 100)

export const exercicio08 = (instrutores, letra) =>
  instrutores.filter(item => {
    return item.nome.split('').includes(letra)
  })

export const exercicio09 = instrutores => instrutores.map(item => item.sobrenome.split('')[0])

export const exercicio10 = (instrutores, letra) =>
  instrutores
    .map(
      item =>
        (item = {
          nomeInicial: item.nome.split('')[0],
          sobrenomeInicial: item.sobrenome.split('')[0],
        })
    )
    .map(item => {
      return item.nomeInicial === letra || item.sobrenomeInicial === letra ? true : false
    })
    .includes(true)

export const exercicio11 = instrutores =>
  instrutores.sort((a, b) => {
    if (a.sobrenome < b.sobrenome) return -1
    if (a.sobrenome > b.sobrenome) return +1
    return 0
  })

export const exercicio12 = (instrutores, letra) =>
  instrutores.reduce((acc, cv) => {
    return (acc = acc && (cv.nome.includes(letra) || cv.sobrenome.includes(letra)))
  }, true)

export const exercicio13 = instrutores =>
  instrutores.reduce((acc, instrutor) => {
    const { nome, sobrenome } = instrutor
    return (acc = {
      ...acc,
      [nome]: sobrenome,
    })
  }, {})
export const exercicio14 = (instrutores, letra) => instrutores.find(item => item.sobrenome.includes(letra))

export const exercicio15 = (instrutores, limiteIdade) =>
  instrutores.filter(item => item.idade < limiteIdade).map(item => item.sobrenome)

export const exercicio16 = (instrutores, cidade, limiteIdade) =>
  instrutores.some(item => item.idade < limiteIdade && cidade === item.cidade)

export const exercicio17 = (instrutores, cidade, letra) =>
  instrutores
    .filter(item => cidade === item.cidade)
    .every(item => {
      return item.nome.includes(letra) || item.sobrenome.includes(letra)
    })

export const exercicio18 = (instrutores, cidade) =>
  instrutores
    .filter(item => cidade === item.cidade)
    .reduce((acc, cv) => {
      return (acc = acc + cv.idade)
    }, 0)

export const exercicio19 = (instrutores, cidade) =>
  instrutores
    .filter(item => item.cidade === cidade)
    .sort((b, a) => {
      if (a.idade < b.idade) return -1
      if (a.idade > b.idade) return +1
      return 0
    })

export const exercicio20 = (instrutores, limiteIdade) =>
  instrutores
    .filter(item => item.idade > limiteIdade)
    .sort((b, a) => {
      if (a.sobrenome < b.sobrenome) return -1
      if (a.sobrenome > b.sobrenome) return +1
      return 0
    })
    .reduce((acc, cv) => {
      return (acc = [...acc, cv.sobrenome.toUpperCase()])
    }, [])

export const exercicio21 = (instrutores, cidade, limiteMetadeIdade) =>
  instrutores.filter(item => item.cidade === cidade).some(item => item.idade / 2 > limiteMetadeIdade)

export const exercicio22 = (instrutores, limiteDobroIdade) =>
  instrutores
    .filter(instrutor => instrutor.idade * 2 > limiteDobroIdade)
    .map(item => item.sobrenome.split('')[0])
    .sort()
    .reduce((acc, cv, index) => {
      return (acc = { ...acc, [index + 1]: cv })
    }, 0)

export const exercicio23 = (instrutores, limiteSomaIdade) =>
  instrutores
    .map(item => {
      const { idade, sobrenome } = item
      const operacao = idade.toString().split('').join('+')
      const somaIdade = parseInt(eval(operacao))
      return { sobrenome: sobrenome.split(''), somaIdade: somaIdade }
    })
    .map(item => {
      const vogais = ['a', 'e', 'i', 'o', 'u']
      const novaPalavra = item.sobrenome.filter(letra => !vogais.includes(letra))
      return { nome: novaPalavra.join('').toUpperCase(), somaIdade: item.somaIdade }
    })
    .filter(item => item.somaIdade > limiteSomaIdade)
    .map(item => item.nome)
    .toString()
    .split('')
    .reduce((acc, cv) => {
      if (!acc.includes(cv)) acc = [...acc, cv.replace(',', '')]
      return acc
    }, [])
    .sort()
    .join('')
