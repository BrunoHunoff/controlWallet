package views;

import controllers.GerenciarUsuario;
import helpers.Console;

public class MenuLogin {

    public static String menuLogin() throws Exception{
        System.out.println("\n--- Control Wallet ---\n");

        String login = Console.lerString("Login: ");
        String senha = Console.lerString("Senha: ");


        String idUsuario = GerenciarUsuario.loginValido(login, senha);


        if (idUsuario == null) {
            throw new Exception("\nUsuario ou Senha inválidos. Tente novamente!");
        }

        return idUsuario;
    }
}
