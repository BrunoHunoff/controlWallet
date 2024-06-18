import controllers.GerenciarUsuario;
import helpers.Console;

public class Login {

    public static void main(String[] args) {
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        
        System.out.println("Gerenciamento de ativos");
        String nomeUsuario = Console.lerString("Digite o nome de usuário: ");
        String senha = Console.lerString("Digite a senha: ");

        if (gerenciarUsuario.loginValido(nomeUsuario, senha)) {
            // chamar menu
            System.out.println("Login efetuado com sucesso!");
        } else {
            System.out.println("Nome de usuário ou senha incorretos.");
        }

    }
    
}
