import controllers.GerenciarUsuario;
import helpers.Console;
import models.Usuario;
public class Main {
    public static void main(String[] args) {
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        
        int opcao;

        do {
            System.out.println("1. Criar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Deletar Usuário");
            System.out.println("5. Sair");
            opcao = Console.lerInt("Escolha uma opção: ");
            

            switch (opcao) {
                case 1:
                    String nomeCompleto = Console.lerString("Digite o nome completo do usuário: ");
                    String nomeUsuario = Console.lerString("Digite o nome de usuario: ");
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

                case 5:
                    System.out.println("Finalizando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
}
