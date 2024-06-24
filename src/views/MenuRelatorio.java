package views;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import models.Ativo;
import models.Usuario;

import java.util.UUID;

public class MenuRelatorio {
    public static void gerarRelatorio(UUID idUsuario){
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
        System.out.println("\nTotal investido: R$" + totalInvestido);

        System.out.println("\nAtivos em carteira");
        System.out.println("Saldo Cripto: R$" + MenuCripto.getSaldoGeral());
        System.out.println("Saldo NFT: R$" + MenuNft.getSaldoGeral());
        System.out.println("Saldo Ações: R$" + MenuAcao.getSaldoGeral());
        System.out.println("Saldo FII: R$" + MenuFundoImobiliario.getSaldoGeral());
        System.out.println("Saldo Renda Fixa: R$" + MenuRendaFixa.getSaldoGeral());


    }
}
