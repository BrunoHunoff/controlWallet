package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.FundoImobiliario;

import java.time.chrono.IsoEra;
import java.util.ArrayList;

public class MenuFundoImobiliario {

    public static boolean executarMenuFundo(String idUsuario) {
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
        System.out.println("\n--- FUNDOS IMOBILIÁRIOS ---\n");

        exibirFundos();

        System.out.println("\n");
        System.out.println("1 - Adicionar novo Fundo imobiliário");
        System.out.println("2 - Remover Fundo imobiliário");
        System.out.println("3 - Editar Fundo imobiliário");
        System.out.println("4 - Buscar Fundo imobiliário");
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
                adicionarFundo();
                break;

            case 2:
                removerFundo();
                break;

            case 3:
                editarFundo();
                break;

            case 4:
                buscarFundo();
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

    private static void exibirFundo(FundoImobiliario ativo) {
        System.out.println(ativo.getNome());
        System.out.println(" - Tipo: " + ativo.getTipoFundo());
        System.out.println(" - Preço Médio: " + ativo.getPreco());
        System.out.println(" - Quantidade: " + ativo.getQuantidade());
        System.out.println(" - Saldo: " + ativo.getSaldo());
    }

    public static float getSaldoGeral() {
        float saldo = 0;
        try {
            for (Ativo ativo: listarFundos()) {
                saldo += ativo.getSaldo();
            }
        } catch (Exception e) {}

        return saldo;
    }

    public static void visaoGeral() {
        System.out.println("\nVisão Geral\n");
        float saldoGeral = 0;
        try {
            for (Ativo ativo : listarFundos()) {
                exibirFundo((FundoImobiliario) ativo);
                System.out.println("\n");
                saldoGeral += ativo.getSaldo();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Saldo geral em Fundo Imobiliário: R$" + saldoGeral);
    }

    private static void transacao() throws Exception{
        System.out.println("\nNova transação\n");

        for (Ativo temp: AtivosController.getAtivosConta()) {
            if (temp instanceof FundoImobiliario) {
                System.out.println(temp.getNome() + "\n");
            }
        }

        FundoImobiliario tempFundo = null;

        String nome = Console.lerString("Fundo Imobiliário: ");

        try {
            tempFundo = (FundoImobiliario)AtivosController.buscarFii(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        MenuAtivoInterface.transacao(tempFundo);
    }

    private static void buscarFundo() {
        System.out.println("\nBuscar Fundo Imobiliário\n");

        String nome = Console.lerString("Nome: ");

        try {
            System.out.println(AtivosController.buscarFii(nome));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerFundo() {
        System.out.println("\nRemover Fundo Imobiliário\n");

        String nome = Console.lerString("Nome do Fundo: ");

        while (true) {
            String confirma = Console.lerString("Deseja remover o fundo: " + nome + "?(S/N)");

            if (confirma.equals("N") || confirma.equals("n")) {
                return;
            }
            if (confirma.equals("S") || confirma.equals("s")) {
                try {
                    FundoImobiliario temp = AtivosController.buscarFii(nome);

                    AtivosController.deletarAtivo(temp);
                    System.out.println("Fundo Imobiliário deletado com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
            }

            System.out.println("Opção inválida. Tente novamente");
        }
    }

    private static void editarFundo() {
        System.out.println("\nEditar FII\n");

        String nome = Console.lerString("Nome atual: ");
        Ativo tempFundo = null;
        try {
            tempFundo = AtivosController.buscarFii(nome);

            System.out.println("\nFII encontrado! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempFundo = (FundoImobiliario) tempFundo;

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
        tempFundo.setNome(novoNome);
        ((FundoImobiliario) tempFundo).setTipoFundo(Console.lerString("Tipo do FII: "));

    }

    private static void adicionarFundo() {
        System.out.println("\nAdicionar novo FII\n");

        String nome = Console.lerString("Nome: ");

        try {
            nomeEmUso(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        String tipoFundo = Console.lerString("Tipo: ");


        AtivosController.cadastrarAtivo(nome, tipoFundo);

    }

    private static void nomeEmUso(String nome) throws Exception{
        ArrayList<FundoImobiliario> temp = new ArrayList<>();
        try {
            for (FundoImobiliario fundoImobiliario : listarFundos()) {
                temp.add(fundoImobiliario);
            }
        } catch (Exception e) {}

        for (FundoImobiliario fundoImobiliario: temp) {
            if (fundoImobiliario.getNome().equals(nome)) {
                throw new Exception("Nome já está em uso");
            }
        }
    }


    private static ArrayList<FundoImobiliario> listarFundos() throws Exception{
        ArrayList<FundoImobiliario> lista = new ArrayList<>();
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                if (ativo instanceof FundoImobiliario) {
                    lista.add((FundoImobiliario) ativo);
                }
            }
        } catch (Exception e) {
            //excessão tratada abaixo
        }

        if (lista.isEmpty()) {
            throw new Exception("Não há FIIs cadastrados!");
        }
        return lista;
    }

    private static void exibirFundos() {
        try {
            for (FundoImobiliario fundoImobiliario : listarFundos()) {

                String txt = "Nome: " + fundoImobiliario.getNome() + " | Saldo: " + fundoImobiliario.getSaldo();
                System.out.println(txt);

            }
        } catch (Exception e) {}

    }
}