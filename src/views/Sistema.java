package views;

public class Sistema {
    private static String idUsuario = null;

    public static void exe() {

        do {
            try {
                idUsuario = MenuLogin.menuLogin();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (idUsuario == null);
    }


}
