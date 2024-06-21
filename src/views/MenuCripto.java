package views;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import helpers.Console;
import models.Ativo;
import models.Criptomoeda;

public class MenuCripto {

    public static void executarMenuCripto() {
        while (true) {
            exibirMenu();
            int op = selecionarOp();


            if (op == 7) {
                break;
            }

            if (op == 0) {
                System.out.println("\nSistema finalizado...");
                break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- CRIPTO ---\n");

        listarCripto();

        System.out.println("\n");
        System.out.println("1 - Adicionar nova moeda");
        System.out.println("2 - Remover moeda");
        System.out.println("3 - Editar moeda");
        System.out.println("4 - Buscar moeda");
        System.out.println("5 - Adicionar transação");
        System.out.println("6 - Visão geral");
        System.out.println("7 - Voltar");
        System.out.println("0 - Sair");

    }

    private static int selecionarOp() {
        int op = Console.lerInt("Opção: ");

        switch (op) {
            case 1:
                adicionarCripto();
                break;
        }

        return op;
    }

    private static void adicionarCripto() {
        System.out.println("\nAdicionar nova Criptomoeda\n");

        String nome = Console.lerString("Nome: ");
        String tipoMoeda = Console.lerString("Tipo: ");
        String rede = Console.lerString("Rede: ");

        AtivosController.cadastrarAtivo(nome, "Cripto", tipoMoeda, rede);
    }

    private static void listarCripto() {
        for (Ativo ativo: AtivosController.getAtivosConta()) {
            if (ativo instanceof Criptomoeda) {
                System.out.printf(ativo.toString());
            }
        }
    }
}
