export const exercicio01 = (pokemons, defenseLimit) =>
  pokemons
    .filter(pokemon => {
      const defesa = pokemon.stats.find(item => item.name === 'defense')
      const indexOfDefesa = pokemon.stats.indexOf(defesa)
      return pokemon.stats[indexOfDefesa].value > defenseLimit
    })
    .reduce((acc, cv) => {
      return (acc = [...acc, cv.name])
    }, [])

export const exercicio02 = (pokemons, id, type) =>
  pokemons
    .filter(pokemon => pokemon.id === id)
    .reduce((acc, cv) => {
      acc = [
        ...acc,
        {
          id: cv.id,
          name: cv.name,
          height: cv.height,
          weight: cv.weight,
          types: [...cv.types, type],
        },
      ]
      return acc
    }, [])

export const exercicio03 = pokemons =>
  pokemons
    .filter(pokemon => {
      let count = 0
      pokemon.moves.forEach(element => {
        if (element.levelLearnedAt > 1) {
          count = count + 1
        }
      })
      return count >= 6
    })
    .reduce((acc, cv) => {
      let soma = 0
      cv.stats.forEach(element => {
        soma = element.value + soma
      })
      return { ...acc, [cv.name]: soma }
    }, {})

export const exercicio04 = (pokemons, pokemonTypes) =>
  pokemons
    .filter(pokemon => {
      let count = 0
      pokemonTypes.forEach(pokemonType => {
        pokemon.types.forEach(pokemonTypeFromArray => {
          if (pokemonType === pokemonTypeFromArray) count = count + 1
        })
      })
      return count > 0
    })
    .reduce((acc, cv) => {
      cv.moves.forEach(move => {
        acc = [...acc, move]
      })
      return acc
    }, [])
    .filter(move => parseInt(move.levelLearnedAt) === 1)
    .map(move => move.name)
    .reduce((acc, cv) => {
      if (!acc.includes(cv)) acc = [...acc, cv]
      return acc
    }, [])
    .sort()

export const exercicio05 = (pokemons, heightLimit) =>
  pokemons
    .filter(pokemon => {
      return pokemon.height < heightLimit && pokemon.id <= 151
    })
    .reduce((acc, cv) => {
      const operacaoEmString = cv.weight.toString().split('').join('+')
      const valorSecreto = parseInt(eval(operacaoEmString))
      return (acc = [
        ...acc,
        {
          id: cv.id,
          name: cv.name,
          valorSecreto: valorSecreto,
        },
      ])
    }, [])
