# Control Wallet

## Informações Gerais sobre o Projeto

-  O Sistema ‘ControlWallet’ foi desenvolvido com o intuito de o usuário ter um melhor controle de seus investimentos, como ações, criptomoedas, fundos imobiliários NFTs e renda fixa.
-  O sistema oferece para o usuário as funcionalidades para criar, listar, atualizar e além de gerar relatórios sobre sua carteira. Para o administrador as funcionalidades são para criar, listar, atualizar e deletar usuários.

## Informações sobre as Classes e suas Relações

-  Models:
*  A classe 'Ativo' é a superclasse de 'Açao', 'Criptomoeda', 'FundoImobiliário', 'Nft' e 'RendaFixa'.
*  A classe 'Usuario' possui como atributos os dados para cadastro de um cliente.

-  Controllers:
*  A classe 'AtivosController' possui um ArrayList de 'Ativo', caracterizando assim uma agregação, além disso, há métodos para leitura e gravação em arquivos e métodos para realização do CRUD nos tipos de 'Ativo'.
*  A classe 'GerenciarUsuario' possui um uma lista de 'Usuario', caracterizando assim uma agregação, além de métodos para tratamento de exceções, carregar e salvar o usuário e realizar o CRUD do mesmo. Também há a parte de criptografia das senhas.

-  Views
*  Cada tipo de Ativo possui um menu expecífico com métodos para realização do CRUD.
*  A classe 'MenuUsuario é usada para chamar os menus de cada tipo de Ativo.
*  A classe 'MenuAdmin' é utilizada para o CRUD de 'Usuario'.
*  A classe 'MenuAtivoInferface' é utilizada para salvar as alterações feitas antes do sistema ser encerrado.
*  A classe 'MenuRelatorio' possui um atributo do tipo 'Usuario', sendo assim uma associação, além de métodos para saber o total investido em cada tipo de Ativo.
*  A classe 'MenuLogin' é usada para realizar o login do usuário


## Como Executar o Projeto

-  Ao executar a classe principal, é chamada a classe Sistema que por sua vez chama a menuLogin
-  A classe menuLogin utiliza o método usuarioValido para validar se o usuário existe ou não
-  Será necessário iniciar com o login e senha já cadastrado de um administrador. Login: admin; Senha: 12345
-  A partir do menu de administrador será possível criar um novo Usuário 
-  O usuário admin pode realizar as operações do CRUD de usuários do sistema
-  Agora com Usuário criado pode reiniciar o sistema e entrar com o login e senha criado
-  O usuário comum pode acessar sua carteira, gerar relatório e manipular os 5 tipos de ativos disponíveis

## Uso do ChatGPT

-   Utilizado introdução ao uso de UUID para identificação única de usuário no sistema
-   Utilizado na criação da lógica para criptografia das senhas no txt
