package views;

import controllers.AtivosController;
import helpers.Console;

public class MenuFundoImobiliario {
    
    static void exibirMenu() {

        System.out.printf("\n--- FUNDO IMOBILIÁRIO ---\n");

        System.out.println("1 - Adicionar novo fundo imobiliário");
        System.out.println("2 - Remover fundo imobiliário");
        System.out.println("3 - Editar fundo imobiliário");
        System.out.println("4 - Buscar fundo imobiliário");
        System.out.println("5 - Adicionar transação");
        System.out.println("0 - Voltar");

        int op = Console.lerInt("Informe sua opção: ");

        switch (op) {
            case 1:
                
                String nome = Console.lerString("Nome: ");
                
                break;
            
            case 2:
                nome = Console.lerString("Digite o nome do fundo imobiliário que deseja excluir: ");
                try {
                    AtivosController.deletarAtivo(nome);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                // método editar
                break;
            
            case 4:
                nome = Console.lerString("Digite o nome do fundo imobiliário que deseja buscar: ");

                try {
                    AtivosController.buscarAtivo(nome);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            
            case 5:
                // método transação
                break;

            default:
                System.out.println("opção inválida! Digite Novamente");
                break;
        }

    }
}
