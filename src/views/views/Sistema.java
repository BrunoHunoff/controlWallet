package views;

import controllers.GerenciarUsuario;
import models.Usuario;

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

        //se id == id do Admin
        if (usuario.getNomeCompleto().equals("admin")) {
            MenuAdmin.executarMenuAdmin();
        } else {
            MenuUsuario.executarMenuUsuario();
        }
    }


}
