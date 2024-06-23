package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;

public interface MenuAtivoInterface {
    public static String setNovoNome(Ativo temp) throws Exception{
        String novoNome = Console.lerString("Nome: ");

        if (temp.nomeExiste(novoNome)) {
            throw new Exception("Nome já esté em uso");
        }

        return novoNome;
    }

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
