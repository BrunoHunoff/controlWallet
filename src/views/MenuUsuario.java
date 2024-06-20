package views;

import controllers.GerenciarUsuario;
import helpers.Console;

public class MenuUsuario {

    private static void exibirMenuUsuario() {
        
        System.out.println("\nControl Wallet");
        System.out.println("1) Gerenciar ativos");
        System.out.println("2) Emitir relatório");
    }

    private static void verificarOpcaoUsuario(int op) {
        
        switch (op) {
            case 1:
                menuSelecionarAtivo();
                int opcao = Console.lerInt("Selecione o tipo de ativo: ");

                switch (opcao) {

                    case 1:
                        menuAcao();
                        break;
                    case 2:
                        menuCriptomoeda();
                        break;
                    
                    case 3:
                        menuFundoImobiliario();
                        break;
                    
                    case 4:
                        menuNft();
                        break;
                
                    case 5:
                        menuRendaFixa();
                        break;
                    
                    default:
                        break;
                }

            case 2:
                // método emitir relatório
                break;
            
            default:
                System.out.println("\nOpção inválida, digite novamente");
            }
        }

    private static void menuSelecionarAtivo() {

        System.out.println("\nSELEÇÃO DE ATIVO");
        System.out.println("1) Ação");
        System.out.println("2) Criptomoeda");
        System.out.println("3) Fundo imobiliário");
        System.out.println("4) NFT");
        System.out.println("5) Renda fixa");
    }
    
    private static void exibirMenuAtivos() {

        System.out.println("\n1) Cadastrar");
        System.out.println("2) Listar");
        System.out.println("3) Atualizar");
        System.out.println("4) Excluir");
    }

    private static void menuAcao() {
        exibirMenuAtivos();
        int op = Console.lerInt("Informe sua opção: ");
        switch (op) {
            case 1:
                // método cadastrar
                break;
            
            case 2:
                // método listar
                break;

            case 3:
                // método atualizar
                break;
            
            case 4:
                // método excluir 
                break;

            default:
                break;
        }
    }

    private static void menuCriptomoeda() {
        exibirMenuAtivos();
        int op = Console.lerInt("Informe sua opção: ");
        switch (op) {
            case 1:
                // método cadastrar
                break;
            
            case 2:
                // método listar
                break;

            case 3:
                // método atualizar
                break;
            
            case 4:
                // método excluir 
                break;
                
            default:
                break;
        }
    }

    private static void menuFundoImobiliario() {
        exibirMenuAtivos();
        int op = Console.lerInt("Informe sua opção: ");
        switch (op) {
            case 1:
                // método cadastrar
                break;
            
            case 2:
                // método listar
                break;

            case 3:
                // método atualizar
                break;
            
            case 4:
                // método excluir 
                break;
                
            default:
                break;
        }
    }

    private static void menuNft() {
        exibirMenuAtivos();
        int op = Console.lerInt("Informe sua opção: ");
        switch (op) {
            case 1:
                // método cadastrar
                break;
            
            case 2:
                // método listar
                break;

            case 3:
                // método atualizar
                break;
            
            case 4:
                // método excluir 
                break;
                
            default:
                break;
        }
    }

    private static void menuRendaFixa() {
        exibirMenuAtivos();
        int op = Console.lerInt("Informe sua opção: ");
        switch (op) {
            case 1:
                // método cadastrar
                break;
            
            case 2:
                // método listar
                break;

            case 3:
                // método atualizar
                break;
            
            case 4:
                // método excluir 
                break;
                
            default:
                break;
        }
    }

    private static void executarMenuUsuario() {

        while(true) {

            exibirMenuUsuario();
            int op = Console.lerInt("Informe sua opção: ");
            verificarOpcaoUsuario(op);

        }
    }
    
    // private static void login() {

    //     GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();

    //     String nomeUsuario = Console.lerString("Digite o nome de usuário: ");
    //     String senha = Console.lerString("Digite a senha: ");

    //     if (gerenciarUsuario.loginValido(nomeUsuario, senha)) {
            
    //         System.out.println("Login efetuado com sucesso!");
    //         executarMenuUsuario();
    //     } else {
    //         System.out.println("Nome de usuário ou senha incorretos.");
    //     }
    // }

}
