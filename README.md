# Login

## Funcionamento

-   Criado classe de usuário com identificação unica utilizando UUID
-   Dentro dos métodos da classe, temos as opções de criação, busca, atualização, exclusão (CRUD)
-   Criado um método para validação se o usuário existe ou não para evitar duplicidade
-   Criado um método para validar o login do usuário
-   Criado um método para criptografar a senha antes de salvar no arquivo txt

## Uso do chat gpt

-   Utilizado introdução ao uso de UUID para identificação única de usuário no sistema
-   Utilizado na criação da lógica para criptografia das senhas no txt

## Utilização

-   Ao executar a classe principal, é chamada a classe Sistema que por sua vez chama a menuLogin
-   A classe menuLogin utiliza o método usuarioValido para validar se o usuário existe ou não
-   Caso o usuário exista e seja o admin, é chamado o menu de admin, que gerencia usuários
-   Caso o usuário existe mas não seja o admin, é chamado o menu de usuário para gerenciamento da carteira
-   O usuário admin pode realizar as operações do CRUD de usuários do sistema
-   O usuário comum pode acessar sua carteira, gerar relatório e manipular os 5 tipos de ativos disponíveis
