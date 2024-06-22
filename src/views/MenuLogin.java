package views;

import controllers.GerenciarUsuario;
import helpers.Console;
import models.Usuario;

public class MenuLogin {

    public static Usuario menuLogin() throws Exception{
        System.out.println("\n--- Control Wallet ---\n");

        String login = Console.lerString("Login: ");
        String senha = Console.lerString("Senha: ");


        Usuario usuario = GerenciarUsuario.loginValido(login, senha);


        if (usuario == null) {
            throw new Exception("\nUsuario ou Senha inválidos. Tente novamente!");
        }

        return usuario;
    }
}
