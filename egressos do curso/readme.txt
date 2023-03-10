Esse arquivo corresponde ao trabalho final da disciplina de LP2.

Enunciado do problema:

Tema #1: Egressos do Curso

Vários alunos passaram pelo curso de Ciência da Computação da Universidade Federal do Maranhão e estão hoje atuando em diferentes áreas. Entretanto, o curso não mantém uma pesquisa ativa para informar onde cada egresso se encontra, se está trabalhando na área ou não, onde está trabalhando, dentre outras perguntas. Este também é um problema presente em outros cursos. Esse mapeamento poderia ajudar o curso a melhorar aspectos de continuidade, integração com o mercado e ainda permitir maior interação com os antigos alunos.

Seu trabalho aqui é construir o sistema que permita gerenciar toda a coleta e apresentação dessas informações. O sistema terá egressos (ex-alunos), formações, posição no mercado, assim como a possibilidade do egresso emitir um depoimento acerca da situação atual.

Um egresso deve ter um identificador (sugere-se o cpf), nome e data de conclusão do curso. Espera-se que ele tenha 1 ou mais contatos, sendo que o contato pode ser email, telefone, redes sociais, ... Cada egresso também pode possuir várias formações além do curso de Ciência da Computação. As formações podem ser a nível de curso complementar, especialização, graduação ou pós-graduação. E, ao longo de sua vida profissional, o egresso ocupou vários cargos ou empreendeu como dono de empresa. Permita que o egresso informe todas as ocupações ao longo do tempo, tendo em mente que ele pode ter sido empregado ou empregador, que ele tinha um salário e deveria informar, que a ocupação tem uma data de início, que foi em algum lugar (empresa, cidade, país) e que possui uma descrição. Finalmente, permita o egresso cadastrar depoimentos (texto livre) a cerca de sua experiência no curso ou na vida profissional.

Já cada egresso fez um curso na UFMA, e este curso além de sua identificação possui um coordenador. Esse coordenador será o usuário responsável por informar todos os dados dos cursos e dos egressos. Assim o sistema terá vários coordenadores, vários cursos e vários egressos para cada curso.


O sistema desenvolvido deve ter as seguintes funcionalidades mínimas:

1. Permitir a criação de coordenadores por um administrador fixo no sistema
2. Permitir que o coordenador crie e gerencie um curso
3. O sistema deve realizar o login/logout dos usuários coordenador e administrador
4. O coordenador ainda poderá:
4a) Cadastrar e editar egresso e todas as informações ao redor deste
4b) Consultar egresso por nome e/ou cpf, e ainda listar qualquer informação cadastrada ao redor deste
4c) Gerar relatórios (impressos na tela):
    Listagem de egressos por ano, ordenado pelo nome do egresso: [Egressos (nome), Data conclusão do curso] -> permitir filtrar por ano. Ex: 2020
   Listagem de egresso e posições: [Egressos (nome) | Posição (descrição da posição) | Salário] -> permitir filtrar por intervalo de tempo. Ex: de 2020 à 2022.
  Listagem de posições e salários, ordenada por salário: [Posição (descrição da posição), salário] -> permitir filtrar por intervalo de tempo. Ex: de 2020 à 2022.
5. A qualquer momento, o coordenador por fazer logoff e outro coordenador pode fazer login. As informações apresentadas de curso e egressos devem ser restritas àquele coordenador (ou seja, um coordenador não pode mexer nos dados de outro coordenador)


Critérios de avaliação:

[2,5pt] Uso correto de orientação a objetos, herança e polimorfismo
[2,5pt] Emprego de arquivos para salvar e recuperar qualquer informação cadastrada no programa
[4,0pt] Implementação das funcionalidades descritas
[1,0pt] Código documentado, bem estruturado e escrito
Extra:
[+1,0pt] Se implementar interface gráfica
[+1,0pt] Se gerar os relatórios como PDF

Qualquer erro gerará redução de 0,5pt, no mínimo.

Equipes:
1 ou 2 pessoas

Entrega:
02/01/2023 até 06h

Defesa e apresentação:
02/01/2023 14h