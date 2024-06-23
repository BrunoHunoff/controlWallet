package views;

import controllers.AtivosController;
import helpers.Console;
import models.Acao;

public class MenuAcao {
    
    static void exibirMenu() {

        System.out.printf("\n--- AÇÃO ---\n");
        System.out.println("1 - Adicionar nova ação");
        System.out.println("2 - Remover ação");
        System.out.println("3 - Editar ação");
        System.out.println("4 - Buscar ação");
        System.out.println("5 - Adicionar transação");
        System.out.println("0 - Voltar");

        int op = Console.lerInt("Informe sua opção: ");

        switch (op) {
            case 1:
                // adicionarAcao();
                break;
            
            case 2:
                removerAcao();
                break;

            case 3:
                // editarAcao();
                break;
            
            case 4:
                // buscarAcao();
                break;
            
            case 5:
                // método transação
                break;

            default:
                System.out.println("Opção inválida! Tente Novamente...");
                break;
        }
    }


        private static void removerAcao() {
        System.out.println("\nRemover Ação\n");

        String nome = Console.lerString("Nome da Ação: ");

        while (true) {
            String confirma = Console.lerString("Deseja remover a ação " + nome + "?[S/N] ").toLowerCase();

            if (confirma.equals("n")) {
                return;
            }

            if (confirma.equals("s")) {
                try {
                    Acao temp = AtivosController.buscarAcao(nome);
                    AtivosController.deletarAtivo(temp);
                    System.out.println("Ação deletada com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
            }

            System.out.println("Opção inválida. Tente novamente...");
        }
    }

}
