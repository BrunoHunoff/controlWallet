package views;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import models.Ativo;
import models.Usuario;

public class MenuRelatorio {
    public static void gerarRelatorio(String idUsuario){
        Usuario usuario;
        try{
            usuario = GerenciarUsuario.buscarUsuario(idUsuario);
        }catch(Exception e){
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            return;
        }

        float totalInvestido = 0;
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                totalInvestido += ativo.getPreco() * ativo.getQuantidade();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nRELATÓRIO DE INVESTIMENTOS");
        System.out.println("\nUsuário: " + usuario.getNomeCompleto());
        System.out.println("\nTotal investido: " + totalInvestido);

        System.out.println("\nAtivos em carteira");
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                System.out.println("\n" + ativo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
