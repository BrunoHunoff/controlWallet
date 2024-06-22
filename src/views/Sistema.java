package views;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import helpers.Console;
import models.Usuario;

import java.util.PrimitiveIterator;

public class Sistema {
    private static Usuario usuario = null;

    public static void exe() {

        GerenciarUsuario.carregarUsuarios();

        do {
            try {
                usuario = MenuLogin.menuLogin();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (usuario == null);

        //se nome == nome do Admin
        if (usuario.getNomeCompleto().equals("admin")) {
            MenuAdmin.executarMenuAdmin();
        } else {
            MenuUsuario.executarMenuUsuario(usuario.getIdUsuario());
        }
    }

}
