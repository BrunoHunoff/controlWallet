package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;

public interface MenuAtivoInterface {

    public static void finalizar(String idUsuario) {
        String salvar = Console.lerString("Deseja salvar as alterações antes de finalizar (S/N)? ");
        if (salvar.equals("S") || salvar.equals("s")) {
            salvarArquivo(idUsuario);
        }
        System.out.println("\nSistema finalizado...");
    }

    public static void salvarArquivo(String idUsuario) {
        try {
            AtivosController.salvarArquivo(idUsuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}

