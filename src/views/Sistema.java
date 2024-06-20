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

        //se id == id do Admin
        if (idUsuario == "95fe9295-44c7-45dc-82dd-025a984b65ba") {
            MenuAdmin.exibirMenuAdmin();
        } else {
            MenuUsuario.executarMenuUsuario();
        }
    }


}
