package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.Nft;

import java.util.ArrayList;

public class MenuNft {

    public static boolean executarMenuNft (String idUsuario) {
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
        System.out.println("\n--- NFT ---\n");

        exibirListaNft();

        System.out.println("\n");
        System.out.println("1 - Adicionar novo NFT");
        System.out.println("2 - Remover NFT");
        System.out.println("3 - Editar NFT");
        System.out.println("4 - Buscar NFT");
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
                adicionarNFT();
                break;

            case 2:
                removerNFT();
                break;

            case 3:
                editarNFT();
                break;

            case 4:
                buscarNFT();
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

    private static void exibirNFT(Nft ativo) {
        System.out.println(ativo.getNome());
        System.out.println(" - Descrição: " + ativo.getDescricao());
        System.out.println(" - Autor: " + ativo.getAutor());
        System.out.println(" - Preço Médio: " + ativo.getPreco());
        System.out.println(" - Quantidade: " + ativo.getQuantidade());
        System.out.println(" - Saldo: " + ativo.getSaldo());
    }

    public static float getSaldoGeral() {
        float saldo = 0;
        try {
            for (Ativo ativo: listarNft()) {
                saldo += ativo.getSaldo();
            }
        } catch (Exception e) {}

        return saldo;
    }

    public static void visaoGeral() {
        System.out.println("\nVisão Geral\n");
        float saldoGeral = 0;
        try {
            for (Ativo ativo : listarNft()) {
                exibirNFT((Nft) ativo);
                System.out.println("\n");
                saldoGeral += ativo.getSaldo();
            }
        } catch (Exception e) {
            System.out.println("Não há NFTs cadastrados!");
            return;
        }

        System.out.println("Saldo geral NFT: R$" + saldoGeral);
    }

    private static void transacao() throws Exception {
        System.out.println("\nNova Transacao\n");

        for (Ativo temp : AtivosController.getAtivosConta()) {
            if (temp instanceof Nft) {
                System.out.println(temp.getNome() + "\n");
            }
        }

        Nft tempNft = null;

        String nome = Console.lerString("NFT: ");

        try {
            tempNft = (Nft) AtivosController.buscarNft(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        MenuAtivoInterface.transacao(tempNft);
    }

    private static void buscarNFT() {
        System.out.println("\nBuscar NFT\n");

        String nome = Console.lerString("Nome: ");

        try {
            System.out.println(AtivosController.buscarNft(nome));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerNFT() {
        System.out.println("\nRemover NFT\n");

        String nome = Console.lerString("Nome da NFT: ");

        while (true) {
            String confirma = Console.lerString("Deseja remover a  " + nome + "?(S/N)");

            if (confirma.equals("N") || confirma.equals("n")) {
                return;
            }
            if (confirma.equals("S") || confirma.equals("s")) {
                try {
                    Nft temp = AtivosController.buscarNft(nome);

                    AtivosController.deletarAtivo(temp);
                    System.out.println("NFT deletada com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
            }

            System.out.println("Opção inválida. Tente novamente");
        }
    }

    private static void editarNFT() {
        System.out.println("\nEditar NFT\n");

        String nome = Console.lerString("Nome atual: ");
        Ativo tempNft = null;
        try {
            tempNft = AtivosController.buscarNft(nome);

            System.out.println("\nNFT encontrada! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempNft = (Nft) tempNft;

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
        tempNft.setNome(novoNome);
        ((Nft) tempNft).setDescricao(Console.lerString("Descrição: "));
        ((Nft) tempNft).setAutor(Console.lerString("Autor: "));
    }

    private static void adicionarNFT() {
        System.out.println("\nAdicionar nova NFT\n");

        String nome = Console.lerString("Nome: ");

        try {
            nomeEmUso(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        String descricao = Console.lerString("Descrição: ");
        String autor = Console.lerString("Autor: ");

        AtivosController.cadastrarAtivo(nome, "NFT", descricao, autor);

    }

    private static void nomeEmUso(String nome) throws Exception{
        ArrayList<Nft> temp = new ArrayList<>();

        try {
            for (Nft nft : listarNft()) {
                temp.add(nft);
            }
        } catch (Exception e) {}

        for (Nft nft: temp) {
            if (nft.getNome().equals(nome)) {
                throw new Exception("Nome já está em uso");
            }
        }
    }


    private static ArrayList<Nft> listarNft() throws Exception{
        ArrayList<Nft> lista = new ArrayList<>();
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                if (ativo instanceof Nft) {
                    lista.add((Nft) ativo);
                }
            }
        } catch (Exception e) {
            //excessão tratada abaixo
        }

        if (lista.isEmpty()) {
            throw new Exception("Não há NFTs cadastrados!");
        }
        return lista;
    }

    private static void exibirListaNft() {
        try {
            for (Nft nft : listarNft()) {

                String txt = "Nome: " + nft.getNome() + " | Saldo: " + nft.getSaldo();
                System.out.println(txt);

            }
        } catch (Exception e) {}
    }
}