package views;

import controllers.GerenciarUsuario;
import helpers.Console;
import models.Usuario;

public class MenuAdmin {
 
    private static void exibirMenuAdmin() {
        
        System.out.println("\nControl Wallet");
        System.out.println("1) Criar usuário");
        System.out.println("2) Listar usuários");
        System.out.println("3) Atualizar usuários");
        System.out.println("4) Deletar usuário");
        System.out.println("0) Sair");
    }

    private static void verificarOpcaoAdmin(int op) {
    
    switch (op) {

        case 1:
            criarUsuario();
            break;

        case 2:
            listarUsuarios();
            break;
        case 3:
            atualizarUsuario();
            break;

        case 4:
            deletarUsuario();
            break;

        case 0:
            System.out.println("Sistema finalizado...");
            System.exit(0);
            break;
        
        default:
            System.out.println("\nOpção inválida, digite novamente");
        }
    }

    private static void criarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---\n");

        String nomeCompleto = Console.lerString("Digite o nome completo do usuário: ");
        String nomeUsuario = Console.lerString("Digite login: ");
        while (true) {
            String senha = Console.lerString("Digite a senha: ");
            String senhaConfirmacao = Console.lerString("Repita a senha: ");

            try{
                GerenciarUsuario.senhaIgual(senha, senhaConfirmacao);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                GerenciarUsuario.criarUsuario(nomeCompleto, nomeUsuario, senha);
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private static void listarUsuarios() {
        for (Usuario usuario : GerenciarUsuario.listarTodos()) {
            System.out.println(usuario);
        }

    }

    private static void atualizarUsuario() {
        String nomeAtual = Console.lerString("Digite o login atual: ");

        Usuario temp = GerenciarUsuario.buscarUsuario(nomeAtual);
        while (true) {
            String novoNomeCompleto = Console.lerString("Digite o novo nome completo do usuário: ");
            String novoLogin = Console.lerString("Digite o novo login: ");
            String novaSenha = Console.lerString("Digite a nova senha: ");
            String novaSenhaConfirmacao = Console.lerString("Repita a senha: ");


            try{
                GerenciarUsuario.senhaIgual(novaSenha, novaSenhaConfirmacao);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                GerenciarUsuario.atualizarUsuario(nomeAtual, novoNomeCompleto, novoLogin, novaSenha);
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "Tente novamente!");
            }
        }
    }

    private static void deletarUsuario() {
        String nomeCompleto = Console.lerString("Digite o nome completo do usuário: ");

        GerenciarUsuario.deletarUsuario(nomeCompleto);
    }

    public static void executarMenuAdmin() {

        while(true) {

            exibirMenuAdmin();
            int op = Console.lerInt("Informe sua opção: ");
            verificarOpcaoAdmin(op);
        }
    }
}
