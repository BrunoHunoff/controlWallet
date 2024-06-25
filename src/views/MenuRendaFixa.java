package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.RendaFixa;
import java.util.ArrayList;

public class MenuRendaFixa {

    public static boolean executarMenuRendaFixa(String idUsuario) {
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
        System.out.println("\n--- RENDA FIXA ---\n");

        exibirListaRendaFixa();

        System.out.println("\n");
        System.out.println("1 - Adicionar nova renda fixa ");
        System.out.println("2 - Remover renda fixa");
        System.out.println("3 - Editar renda fixa");
        System.out.println("4 - Buscar renda fixa");
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
                adicionarRendaFixa();
                break;

            case 2:
                removerRendaFixa();
                break;

            case 3:
                editarRendaFixa();
                break;

            case 4:
                buscarRendaFixa();
                break;

            case 5:
                try {
                    transacao();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 6:
                visaoGeral();
                break;

            case 7:
                MenuAtivoInterface.salvarArquivo(idUsuario);
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

    private static void exibirRendaFixa(RendaFixa ativo) {
        System.out.println(ativo.getNome());
        System.out.println(" - Categoria: " + ativo.getCategoria());
        System.out.println(" - Data de vencimento: " + ativo.getDataVencimento());
        System.out.println(" - Taxa de juros:" + ativo.getTxJuros() + "%");
        System.out.println(" - Preço Médio: " + ativo.getPreco());
        System.out.println(" - Quantidade: " + ativo.getQuantidade());
        System.out.println(" - Saldo: " + ativo.getSaldo());
    }

    public static float getSaldoGeral() {
        float saldo = 0;
        try {
            for (Ativo ativo: listarRendaFixa()) {
                saldo += ativo.getSaldo();
            }
        } catch (Exception e) {}

        return saldo;
    }

    public static void visaoGeral() {
        System.out.println("\nVisão Geral\n");
        float saldoGeral = 0;
        try {
            for (Ativo ativo : listarRendaFixa()) {
                exibirRendaFixa((RendaFixa) ativo);
                System.out.println("\n");
                saldoGeral += ativo.getSaldo();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Saldo geral Renda fixa: R$" + saldoGeral);
    }

    private static void transacao() throws Exception{
        System.out.println("\nNova Transacao\n");

        for (Ativo temp: AtivosController.getAtivosConta()) {
            if (temp instanceof RendaFixa) {
                System.out.println(temp.getNome() + "\n");
            }
        }

        RendaFixa tempRendaFixa = null;

        String nome = Console.lerString("Nome da Renda Fixa: ");

        try {
            tempRendaFixa = (RendaFixa)AtivosController.buscarRendaFixa(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        MenuAtivoInterface.transacao(tempRendaFixa);
    }

    private static void buscarRendaFixa() {
        System.out.println("\nBuscar Renda Fixa\n");

        String nome = Console.lerString("Nome: ");

        try {
            System.out.println(AtivosController.buscarRendaFixa(nome));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerRendaFixa() {
        System.out.println("\nRemover Renda Fixa\n");

        String nome = Console.lerString("Nome da renda fixa: ");

        while (true) {
            String confirma = Console.lerString("Deseja remover a renda fixa " + nome + "?(S/N)");

            if (confirma.equals("N") || confirma.equals("n")) {
                return;
            }
            if (confirma.equals("S") || confirma.equals("s")) {
                try {
                    RendaFixa temp = AtivosController.buscarRendaFixa(nome);

                    AtivosController.deletarAtivo(temp);
                    System.out.println("Renda Fixa deletada com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
            }

            System.out.println("Opção inválida. Tente novamente");
        }
    }

    private static void editarRendaFixa() {
        System.out.println("\nEditar Renda Fixa\n");

        String nome = Console.lerString("Nome atual: ");
        Ativo tempRendaFixa = null;
        try {
            tempRendaFixa = AtivosController.buscarRendaFixa(nome);

            System.out.println("\nRenda Fixa encontrada! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempRendaFixa = (RendaFixa) tempRendaFixa;

        String novoNome = Console.lerString("Nome: ");

        //verifica se nome vai mudar
        if (!(novoNome.equals(nome))) {
            try {
                nomeEmUso(novoNome);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        tempRendaFixa.setNome(novoNome);

        String dataVencimento = Console.lerString("Digite a data de vencimento no formato dd/MM/yyyy: ");

        ((RendaFixa) tempRendaFixa).setDataVencimento(dataVencimento);
        ((RendaFixa) tempRendaFixa).setCategoria(Console.lerString("Categoria: "));
        ((RendaFixa) tempRendaFixa).setTxJuros(Console.lerFloat("Taxa de juros: "));
    }

    private static void adicionarRendaFixa() {
        System.out.println("\nAdicionar nova Renda Fixa\n");

        String nome = Console.lerString("Nome: ");

        try {
            nomeEmUso(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        String categoria = Console.lerString("Categoria: ");
        
        String dataVencimento = Console.lerString("Digite a data de vencimento no formato dd/MM/yyyy: ");

        float txJuros = Console.lerFloat("Taxa de juros: ");

        AtivosController.cadastrarAtivo(nome, categoria, dataVencimento, txJuros);

    }

    private static void nomeEmUso(String nome) throws Exception{
        ArrayList<RendaFixa> temp = new ArrayList<>();

        try {
            for (RendaFixa rendaFixa : listarRendaFixa()) {
                temp.add(rendaFixa);
            }
        } catch (Exception e) {}

        for (RendaFixa rendaFixa: temp) {
            if (rendaFixa.getNome().equals(nome)) {
                throw new Exception("Nome já está em uso");
            }
        }
    }

    private static ArrayList<RendaFixa> listarRendaFixa() throws Exception{
        ArrayList<RendaFixa> lista = new ArrayList<>();
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                if (ativo instanceof RendaFixa) {
                    lista.add((RendaFixa) ativo);
                }
            }
        } catch (Exception e) {}

        if (lista.isEmpty()) {
            throw new Exception("Não há ativos de renda fixa cadastrados!");
        }
        return lista;
    }

    private static void exibirListaRendaFixa() {
        try {
            for (RendaFixa rendaFixa : listarRendaFixa()) {

                String txt = "Nome: " + rendaFixa.getNome() + " | Saldo: " + rendaFixa.getSaldo();
                System.out.println(txt);

            }
        } catch (Exception e) {}
    }
}