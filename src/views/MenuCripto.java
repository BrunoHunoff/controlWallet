package views;

import controllers.AtivosController;
import models.Ativo;
import models.Criptomoeda;

public class MenuCripto {

    public static void executarMenuCripto() {
        exibirMenu();
    }

    private static void exibirMenu() {
        System.out.printf("\n--- CRIPTO ---\n");

        listarCripto();


    }

    private static void listarCripto() {
        for (Ativo ativo: AtivosController.getAtivosConta()) {
            if (ativo instanceof Criptomoeda) {
                System.out.printf(ativo.toString());
            }
        }
    }
}
