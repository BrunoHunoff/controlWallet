package views;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import helpers.Console;

public class MenuUsuario {

    private static void exibirMenuUsuario() {
        
        System.out.println("\nControl Wallet");
        System.out.println("1) Gerenciar ativos");
        System.out.println("2) Emitir relatório");
    }

    private static boolean verificarOpcaoUsuario(int op, String idUsuario) {
        boolean finalizarPrograma = false;

        switch (op) {
            case 1:
                menuSelecionarAtivo();
                int opcao = Console.lerInt("Selecione o tipo de ativo: ");

                switch (opcao) {

                    case 1:

                        break;
                    case 2:
                        finalizarPrograma = MenuCripto.executarMenuCripto(idUsuario);
                        break;
                    
                    case 3:

                        break;
                    
                    case 4:

                        break;
                
                    case 5:

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
            return finalizarPrograma;
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


    public static void executarMenuUsuario(String idUsuario) {

        try {
            AtivosController.lerArquivo();
            AtivosController.lerCarteira(idUsuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while(true) {
            boolean finalizarPrograma = false;

            exibirMenuUsuario();

            int op = Console.lerInt("Informe sua opção: ");

            finalizarPrograma = verificarOpcaoUsuario(op, idUsuario);

            if (finalizarPrograma) {
                finalizar(idUsuario);
                return;
            }
        }
    }

    private static void finalizar(String idUsuario) {
        String salvar = Console.lerString("Deseja salvar as alterações antes de finalizar (S/N)? ");
        if (salvar.equals("S") || salvar.equals("s")) {
            salvarArquivo(idUsuario);
        }
        System.out.println("\nSistema finalizado...");
    }

    public static void salvarArquivo(String idUsuario) {
        try {
            AtivosController.salvarArquivo(idUsuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
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
