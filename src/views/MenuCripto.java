package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.Criptomoeda;

public class MenuCripto {

    public static boolean executarMenuCripto(String idUsuario) {
        boolean finalizar = false;
        while (true) {
            exibirMenu();
            int op = selecionarOp(idUsuario);


            if (op == 8) {
                return false;
            }

            if (op == 0) {

                return true;
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
        System.out.println("7 - Salvar");
        System.out.println("8 - Voltar");
        System.out.println("0 - Sair");

    }

    private static int selecionarOp(String idUsuario) {
        int op = Console.lerInt("Opção: ");

        switch (op) {
            case 1:
                adicionarCripto();
                break;

            case 2:
                removerCripto();
                break;

            case 3:
                editarCripto();
                break;

            case 4:
                buscarCripto();
                break;

            case 7:
                MenuUsuario.salvarArquivo(idUsuario);
                break;
            case 8:

                break;

            case 0:

                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }

        return op;
    }

    private static void buscarCripto() {
        System.out.println("\nBuscar Criptomoeda\n");

        String nome = Console.lerString("Nome: ");

        try {
            System.out.println(AtivosController.buscarAtivo(nome));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerCripto() {
        System.out.println("\nRemover Criptomoeda\n");

        String nome = Console.lerString("Nome da criptomoeda: ");

        while (true) {
            String confirma = Console.lerString("Deseja remover a criptomoeda " + nome + "?(S/N)");

            if (confirma.equals("N") || confirma.equals("n")) {
                return;
            }
            if (confirma.equals("S") || confirma.equals("s")) {
                try {
                    AtivosController.deletarAtivo(nome);
                    System.out.println("Criptomoeda deletada com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
            }

            System.out.println("Opção inválida. Tente novamente");
        }
    }

    private static void editarCripto() {
        System.out.println("\nEditar Criptomoeda\n");

        String nome = Console.lerString("Nome atual: ");
        Ativo tempCripto = null;
        try {
            tempCripto = AtivosController.buscarAtivo(nome);

            if (!(tempCripto instanceof Criptomoeda)) {
                throw new Exception("Não foi possível encontrar Criptomoeda cadastrada com esse nome!");
            }

            System.out.println("\nCripto encontrada! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempCripto = (Criptomoeda)tempCripto;

        tempCripto.setNome(Console.lerString("Nome: "));
        ((Criptomoeda) tempCripto).setTipoMoeda(Console.lerString("Tipo da moeda: "));
        ((Criptomoeda) tempCripto).setRede(Console.lerString("Rede: "));
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
                System.out.println(ativo.toString());
            }
        }
    }
}
