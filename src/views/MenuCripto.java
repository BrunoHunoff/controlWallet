package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.Criptomoeda;

import java.util.ArrayList;

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

        exibirListaCripto();

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

    private static void exibirCripto(Criptomoeda ativo) {
        System.out.println(ativo.getNome());
        System.out.println(" - Tipo: " + ativo.getTipoMoeda());
        System.out.println(" - Rede: " + ativo.getRede());
        System.out.println(" - Preço Médio: " + ativo.getPreco());
        System.out.println(" - Quantidade: " + ativo.getQuantidade());
        System.out.println(" - Saldo: " + ativo.getSaldo());
    }

    public static float getSaldoGeral() {
        float saldo = 0;
        try {
            for (Ativo ativo: listarCripto()) {
                saldo += ativo.getSaldo();
            }
        } catch (Exception e) {}

        return saldo;
    }

    private static void visaoGeral() {
        System.out.println("\nVisão Geral\n");
        float saldoGeral = 0;
        try {
            for (Ativo ativo : listarCripto()) {
                exibirCripto((Criptomoeda) ativo);
                System.out.println("\n");
                saldoGeral += ativo.getSaldo();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Saldo geral Criptomoedas: R$" + saldoGeral);
    }

    private static void transacao() throws Exception{
        System.out.println("\nNova Transacao\n");

        for (Ativo temp: AtivosController.getAtivosConta()) {
            if (temp instanceof Criptomoeda) {
                System.out.println(temp.getNome() + "\n");
            }
        }

        Criptomoeda tempCripto = null;

        String nome = Console.lerString("Criptomoeda: ");

        try {
            tempCripto = (Criptomoeda)AtivosController.buscarCripto(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Operação: ");
        System.out.println("1 - Compra");
        System.out.println("2 - Venda");
        int operacao = Console.lerInt("Operação: ");
        int quantidade = Console.lerInt("Quantidade: ");
        float preco = Console.lerFloat("Preço médio: ");

        float saldoAtual = tempCripto.getSaldo();

        switch (operacao) {
            case 1:
                comprar(tempCripto, quantidade, preco, saldoAtual);
                break;

            case 2:
                vender(tempCripto, quantidade, preco, saldoAtual);
                break;

            default:
                System.out.println("Operação inválida!");
                break;
        }
    }

    private static void comprar(Criptomoeda temp, int quantidade, float preco, float saldoAtual) {

        int quantidadeAtual = temp.getQuantidade();

        int quantidadeFinal = quantidadeAtual + quantidade;
        float saldoFinal = saldoAtual + (quantidade * preco);

        //calcula preço médio de compra
        float precoMedio = saldoFinal/quantidadeFinal;

        temp.setSaldo(saldoFinal);
        temp.setPreco(precoMedio);
        temp.setQuantidade(quantidadeFinal);

    }

    private static void vender(Criptomoeda temp, int quantidade, float preco, float saldoAtual) throws Exception{

        int quantidadeFinal = temp.getQuantidade() - quantidade;

        float saldoFinal = saldoAtual - (quantidade * preco);

        float precoMedio = saldoFinal/quantidadeFinal;

        if (saldoFinal < 0) {
            throw new Exception("Valor indisponível para saque!\nSaldo da moeda: " + saldoAtual);
        }

        temp.setQuantidade(quantidadeFinal);
        temp.setPreco(precoMedio);
        temp.setSaldo(saldoFinal);
    }

    private static void buscarCripto() {
        System.out.println("\nBuscar Criptomoeda\n");

        String nome = Console.lerString("Nome: ");

        try {
            System.out.println(AtivosController.buscarCripto(nome));
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
                    Criptomoeda temp = AtivosController.buscarCripto(nome);

                    AtivosController.deletarAtivo(temp);
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
            tempCripto = AtivosController.buscarCripto(nome);

            System.out.println("\nCripto encontrada! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempCripto = (Criptomoeda) tempCripto;

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
        tempCripto.setNome(novoNome);
        ((Criptomoeda) tempCripto).setTipoMoeda(Console.lerString("Tipo da moeda: "));
        ((Criptomoeda) tempCripto).setRede(Console.lerString("Rede: "));
    }

    private static void adicionarCripto() {
        System.out.println("\nAdicionar nova Criptomoeda\n");

        String nome = Console.lerString("Nome: ");

        try {
            nomeEmUso(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        String tipoMoeda = Console.lerString("Tipo: ");
        String rede = Console.lerString("Rede: ");

        AtivosController.cadastrarAtivo(nome, "Cripto", tipoMoeda, rede);

    }

    private static void nomeEmUso(String nome) throws Exception{
        //arraylist temp para poder capturar excessão e lançar nova
        ArrayList<Criptomoeda> temp = new ArrayList<>();
        try {
            for (Criptomoeda criptomoeda : listarCripto()) {
                temp.add(criptomoeda);
            }
        } catch (Exception e) {}

        for (Criptomoeda criptomoeda: temp) {
            if (criptomoeda.getNome().equals(nome)) {
                throw new Exception("Nome já está em uso!");
            }
        }
    }


    private static ArrayList<Criptomoeda> listarCripto() throws Exception{
        ArrayList<Criptomoeda> lista = new ArrayList<>();
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                if (ativo instanceof Criptomoeda) {
                    lista.add((Criptomoeda) ativo);
                }
            }
        } catch (Exception e) {
            //excessão tratada abaixo
        }

        if (lista.isEmpty()) {
            throw new Exception("Não há criptomoedas cadastradas!");
        }
        return lista;
    }

    private static void exibirListaCripto() {
        try {
            for (Criptomoeda criptomoeda : listarCripto()) {
                String txt = "Nome: " + criptomoeda.getNome() + " | Saldo: " + criptomoeda.getSaldo();
                System.out.println(txt);

            }
        } catch (Exception e) {}
    }
}
