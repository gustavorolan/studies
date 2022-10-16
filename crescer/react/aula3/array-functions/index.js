const instrutoresCompleto = [
  {
    nome: 'murillo',
    sobrenome: 'peteffi',
    idade: 132,
    cidade: 'Springfield',
  },
  {
    nome: 'camille',
    sobrenome: 'grings',
    idade: 128,
    cidade: 'Hawkins',
  },
  {
    nome: 'william',
    sobrenome: 'cardozo',
    idade: 236,
    cidade: 'Springfield',
  },
  {
    nome: 'gustavo',
    sobrenome: 'buttenbender',
    idade: 319,
    cidade: 'Hawkins',
  },
  {
    nome: 'emerson',
    sobrenome: 'pisoni',
    idade: 325,
    cidade: 'Springfield',
  },
  {
    nome: 'luan',
    sobrenome: 'winck',
    idade: 213,
    cidade: 'Hawkins',
  },
]
const cidade = 'Springfield'
const limiteSomaIdade = 1
console.log(
  instrutoresCompleto
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
)
