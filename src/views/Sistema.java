package views;

import controllers.GerenciarUsuario;

public class Sistema {
    private static String idUsuario = null;

    public static void exe() {

        GerenciarUsuario.carregarUsuarios();

        do {
            try {
                idUsuario = MenuLogin.menuLogin();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (idUsuario == null);

    }


}
