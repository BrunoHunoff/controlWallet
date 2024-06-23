package views;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.Nft;

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

        listarNft();

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

    private static void visaoGeral() {
        System.out.println("\nVisão Geral\n");
        float saldoGeral = 0;
        for (Ativo ativo: AtivosController.getAtivosConta()) {
            if (ativo instanceof Nft) {
                exibirNFT((Nft)ativo);
                System.out.println("\n");
                saldoGeral += ativo.getSaldo();
            }
        }

        System.out.println("Saldo geral NFT: R$" + saldoGeral);
    }

    private static void transacao() throws Exception{
        System.out.println("\nNova Transacao\n");

        for (Ativo temp: AtivosController.getAtivosConta()) {
            if (temp instanceof Nft) {
                System.out.println(temp.getNome() + "\n");
            }
        }

        Nft tempNft = null;

        String nome = Console.lerString("NFT: ");

        try {
            tempNft = (Nft)AtivosController.buscarNft(nome);
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

        float saldoAtual = tempNft.getSaldo();

        switch (operacao) {
            case 1:
                comprar(tempNft, quantidade, preco, saldoAtual);
                break;

            case 2:
                vender(tempNft, quantidade, preco, saldoAtual);
                break;

            default:
                System.out.println("Operação inválida!");
                break;
        }
    }

    private static void comprar(Nft temp, int quantidade, float preco, float saldoAtual) {

        int quantidadeAtual = temp.getQuantidade();

        int quantidadeFinal = quantidadeAtual + quantidade;
        float saldoFinal = saldoAtual + (quantidade * preco);

        //calcula preço médio de compra
        float precoMedio = saldoFinal/quantidadeFinal;

        temp.setSaldo(saldoFinal);
        temp.setPreco(precoMedio);
        temp.setQuantidade(quantidadeFinal);

    }

    private static void vender(Nft temp, int quantidade, float preco, float saldoAtual) throws Exception{

        int quantidadeFinal = temp.getQuantidade() - quantidade;

        float saldoFinal = saldoAtual - (quantidade * preco);

        float precoMedio = saldoFinal/quantidadeFinal;

        if (saldoFinal < 0) {
            throw new Exception("Valor indisponível para saque!\nSaldo em NFT: " + saldoAtual);
        }

        temp.setQuantidade(quantidadeFinal);
        temp.setPreco(precoMedio);
        temp.setSaldo(saldoFinal);
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
                    AtivosController.deletarAtivo(nome);
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

            if (!(tempNft instanceof Nft)) {
                throw new Exception("Não foi possível encontrar NFT cadastrada com esse nome!");
            }

            System.out.println("\nNFT encontrada! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempNft = (Nft)tempNft;

        tempNft.setNome(Console.lerString("Nome: "));
        ((Nft) tempNft).setDescricao(Console.lerString("Descrição: "));
        ((Nft) tempNft).setAutor(Console.lerString("Autor: "));
    }

    private static void adicionarNFT() {
        System.out.println("\nAdicionar nova NFT\n");

        String nome = Console.lerString("Nome: ");
        String descricao = Console.lerString("Descrição: ");
        String autor = Console.lerString("Autor: ");

        try {
            AtivosController.cadastrarAtivo(nome, "NFT", autor, descricao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarNft() {
        for (Ativo ativo: AtivosController.getAtivosConta()) {
            if (ativo instanceof Nft) {
                String txt = "Nome: " + ativo.getNome() + " | Saldo: " + ativo.getSaldo();
                System.out.println(txt);
            }
        }
    }
}