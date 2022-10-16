# ❤Dev's-Learning

## Visão Geral
 
A proposta de solução consiste em uma aplicação web para empresas do ramo de tecnologia 
que visam desenvolver softwares, e por consequência enfrentam dificuldades ao compartilhar 
conhecimento e ao transitar ou integrar profissionais em times 
de desenvolvimento. Nossa solução busca fazer isso de forma ágil, eficiente 
e fluida. Desta forma, partindo do pressuposto que nossos usuários são colaboradores da 
empresa que possuem conhecimento em programação, buscamos facilitar a troca de 
conhecimentos sobre assuntos diversos e a integração dos novos profissionais aos 
times e as tecnologias utilizadas na empresa.
A solução conta com:
- Upload de imagens
- Upload de textos
- Upload de vídeos

# ❔Problema

Com o recorrente encarecimento da mão de obra dos desenvolvedores, faz-se necessario centralizar o conhecimento existente em um time de tecnologia em apenas uma plataforma, de modo que ele fique acessível a todo momento para todos do time.

- Dificuldade para focar no processo de aprendizagem.
- Demora do tempo de aprendizagem de uma nova tecnologia.
- Dificuldade por parte do desenvolvedor para adentrar em uma nova equipe.

## 🔒Autenticação
- Spring web security;
- Possibilidade de realizar login pelo auth da CWI.

## ⁉Tecnologias

- Spring boot (back end)
- React (Front end)
- PostgreSQL (Banco de dados)
- API Vimeo para videos.
- Web socket.
- API Externa para tratar conteúdo de imagens.

## ⚙️Funcionalidades

### Tratamento de imagens
- Uploads de imagens irão possuir validações para verificar o conteúdo das mesmas.

### Chat
- Será possivel conversar entre as pessoas do time.

### Upload de video

- Será possível realizar upload de vídeo educacionais na plataforma e, a 
partir destes vídeos, serão montadas playlists de aula. OBS: Usuários comuns 
precisam de aprovação de gestores em seus uploads.

### Upload de vídeos curtos

- Qualquer usuário pode compartilhar vídeos curtos como tutoriais para resolução de problemas.

### Cadastro de usuários

- Será possível cadastrar usuários com diferentes níveis de permissões.
- É necessário que o email esteja presente no banco de dados da empresa.

### Cadastro de times

- Será possível cadastrar times e alocar usuários nos mesmos.

### Cadastro de cursos

- Será possível cadastrar cursos sobre assuntos diversos dentro do escopo da empresa.

### Cadastro de trilhas de aprendizagem

- Será possível cadastrar trilhas de aprendizagem, com base nas necessidade do time, que incluam um ou mais cursos.

### Fórum
- Existirá um fórum de dúvidas dentro da empresa onde todos os usuários podem publicar dúvidas.

### Sistema de avaliações (Conteúdo)
- Os usuários poderão avaliar os conteúdos baseado em sua qualidade.


## Autores

- @Gabriel Alves da Silva
- @Gustavo Rolan Soares
- @Vítor Teixeira Bitencourt