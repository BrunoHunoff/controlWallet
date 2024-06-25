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

    public static void transacao(Ativo temp ) throws Exception{

        float saldoAtual = temp.getSaldo();

        System.out.println("1 - Compra");
        System.out.println("2 - Venda");

        int operacao = Console.lerInt("Operação: ");

        int quantidade = Console.lerInt("Quantidade: ");
        float preco = Console.lerFloat("Preço médio: ");

        if (quantidade <= 0) {
            throw new Exception("A quantidade deve ser maior que 0");
        }

        if (preco <= 0) {
            throw new Exception("O preço deve ser maior que 0");
        }

        switch (operacao) {
            case 1:
                comprar(temp, quantidade, preco, saldoAtual);
                break;

            case 2:
                vender(temp, quantidade, preco, saldoAtual);
                break;

            default:
                System.out.println("Operação inválida!");
                break;
        }

    }

    private static void comprar(Ativo temp, int quantidade, float preco, float saldoAtual) {
        int quantidadeFinal = temp.getQuantidade() + quantidade;

        float saldoFinal = saldoAtual + (quantidade * preco);

        float precoMedio = saldoFinal / quantidadeFinal;

        temp.setQuantidade(quantidadeFinal);
        temp.setSaldo(saldoFinal);
        temp.setPreco(precoMedio);

    }

    private static void vender(Ativo temp, int quantidade, float preco, float saldoAtual) throws Exception {
        int quantidadeFinal = temp.getQuantidade() - quantidade;

        float saldoFinal = saldoAtual + (quantidade * preco);

        float precoMedio = saldoFinal / quantidadeFinal;

        if (quantidadeFinal < 0) {
            throw new Exception("Quantidade indisponível para saque");
        }

        if (saldoFinal < 0) {
            throw new Exception("Valor indisponível para saque!\nSaldo: R$" + saldoAtual);
        }

        temp.setQuantidade(quantidadeFinal);

        temp.setPreco(precoMedio);

        if (quantidadeFinal == 0) {
            temp.setSaldo(0);
            return;
        }
        temp.setSaldo(saldoFinal);

    }
}

