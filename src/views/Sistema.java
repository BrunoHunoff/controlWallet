package views;

import controllers.GerenciarUsuario;
import helpers.Console;
import models.*;

public class Sistema {

    private static void exibirMenuAdmin() {
        
        System.out.println("\nControl Wallet");
        System.out.println("1) Criar usuário");
        System.out.println("2) Listar usuários");
        System.out.println("3) Atualizar usuários");
        System.out.println("4) Deletar usuário");
        System.out.println("0) Sair");
    }

    private static void verificarOpcaoAdmin(int op) {

        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        
        switch (op) {
            case 1:
                String nomeCompleto = Console.lerString("Digite o nome completo do usuário: ");
                String nomeUsuario = Console.lerString("Digite o nome de usuário: ");
                String senha = Console.lerString("Digite a senha: ");

                gerenciarUsuario.criarUsuario(nomeCompleto, nomeUsuario, senha);
                break;

            case 2:
                for (Usuario usuario : gerenciarUsuario.listarTodos()) {
                    System.out.println(usuario);
                }
                break;

            case 3:
                String idUsuarioAtualizar = Console.lerString("Digite o ID do usuário: ");
                String novoNomeCompleto = Console.lerString("Digite o novo nome completo do usuário: ");
                String novoNomeUsuario = Console.lerString("Digite o novo nome de usuario: ");
                String novaSenha = Console.lerString("Digite a nova senha: ");

                gerenciarUsuario.atualizarUsuario(idUsuarioAtualizar, novoNomeCompleto, novoNomeUsuario, novaSenha);
                break;

            case 4:
                String idUsuarioDeletar = Console.lerString("Digite o ID do usuário: ");

                gerenciarUsuario.deletarUsuario(idUsuarioDeletar);
                break;

            case 0:
                System.out.println("Sistema finalizado...");
                System.exit(0);
                break;
            
            default:
                System.out.println("\nOpção inválida, digite novamente");
            }
        }

    public static void executarMenuAdmin() {

        while(true) {

            exibirMenuAdmin();
            int op = Console.lerInt("Informe sua opção: ");
            verificarOpcaoAdmin(op);
        }
    }
}
