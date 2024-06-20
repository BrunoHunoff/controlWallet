import controllers.GerenciarUsuario;
import helpers.Console;
import models.Usuario;

public class Login {

    public static void main(String[] args) {
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        
        System.out.println("Gerenciamento de ativos");
        String nomeUsuario = Console.lerString("Digite o nome de usuário: ");
        String senha = Console.lerString("Digite a senha: ");

        Usuario usuario = gerenciarUsuario.loginValido(nomeUsuario, senha);

        if (usuario != null) {
            // chamar menu
            System.out.println(usuario.toString());
            System.out.println("Login efetuado com sucesso!");
        } else {
            System.out.println("Nome de usuário ou senha incorretos.");
        }

    }
    
}
