package views;

import java.util.ArrayList;

import controllers.AtivosController;
import helpers.Console;
import models.Ativo;
import models.Acao;
public class MenuAcao {

    public static boolean executarMenuAcao(String idUsuario) {
        boolean finalizar = false;
        while (true) {
            exibirMenu();
            int opcao = selecionarOpcao(idUsuario);
            if (opcao == 8) {
                return false;
            }
            if (opcao == 0) {
                return true;
            }
        }
    }
    
    private static void exibirMenu() {

        System.out.printf("\n--- AÇÃO ---\n");
        
        exibirAcoes();
        
        System.out.println("\n");
        System.out.println("1 - Adicionar nova ação");
        System.out.println("2 - Remover ação");
        System.out.println("3 - Editar ação");
        System.out.println("4 - Buscar ação");
        System.out.println("5 - Adicionar transação");
        System.out.println("6 - Visão geral");
        System.out.println("7 - Salvar");
        System.out.println("8 - Voltar");
        System.out.println("0 - Sair");

    }

    private static int selecionarOpcao(String idUsuario) {
        int opcao = Console.lerInt("Opção: ");

        switch (opcao) {
            case 1:
                adicionarAcao();
                break;
                
            case 2:
                removerAcao();
                break;

            case 3:
                editarAcao();
                break;
            
            case 4:
                buscarAcao();
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
                System.out.println("Opção inválida! Tente Novamente...");
                break;
        }

        return opcao;    

    }

    private static void exibirAcao(Acao ativo) {
        System.out.println(ativo.getNome());
        System.out.println(" - Tipo: " + ativo.getTipoAcao());
        System.out.println(" - Preço Médio: " + ativo.getPreco());
        System.out.println(" - Quantidade: " + ativo.getQuantidade());
        System.out.println(" - Saldo: " + ativo.getSaldo());
    }

    public static float getSaldoGeral() {
        float saldo = 0;
        try {
            for (Ativo ativo: listarAcoes()) {
                saldo += ativo.getSaldo();
            }
        } catch (Exception e) {}

        return saldo;
    }

    public static void visaoGeral() {
        System.out.println("\nVisão Geral\n");
        float saldoGeral = 0;
        try {
            for (Ativo ativo : listarAcoes()) {
                exibirAcao((Acao) ativo);
                System.out.println("\n");
                saldoGeral += ativo.getSaldo();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Saldo geral em Ações: R$" + saldoGeral);
    }

    private static void transacao() throws Exception {
        System.out.println("\nNova transação\n");

        for (Ativo temp : AtivosController.getAtivosConta()) {
            if (temp instanceof Acao) {
                System.out.println(temp.getNome() + "\n");
            }
        }

        Acao tempAcao = null;

        String nome = Console.lerString("Ação: ");

        try {
            tempAcao = (Acao)AtivosController.buscarAcao(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        MenuAtivoInterface.transacao(tempAcao);
    }

    private static void buscarAcao() {
        System.out.println("\nBuscar Ação\n");

        String nome = Console.lerString("Nome: ");

        try {
            System.out.println(AtivosController.buscarAcao(nome));
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    private static void editarAcao() {
        System.out.println("\nEditar Ação\n");

        String nome = Console.lerString("Nome atual: ");
        Ativo tempAcao = null;
        try {
            tempAcao = AtivosController.buscarAcao(nome);
            System.out.println("\nAção encontrada! Preencha os dados para editar:\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        tempAcao = (Acao) tempAcao;

        String novoNome = Console.lerString("Nome: ");

        if (!(novoNome.equals(nome))) {
            try {
                nomeEmUso(novoNome);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        tempAcao.setNome(novoNome);

        ((Acao) tempAcao).setTipoAcao(Console.lerString("Tipo da Ação: "));

        while (true) {
            String pagaDividendos = Console.lerString("A ação " + novoNome + " paga dividendos? [S/N] ").toLowerCase();

            if (pagaDividendos.equals("n")) {
                ((Acao) tempAcao).setPagaDividendos(false);
                return;
            }

            if (pagaDividendos.equals("s")) {
                ((Acao) tempAcao).setPagaDividendos(true);
                return;
            }

            System.out.println("Opção inválida. Tente novamente...");
        }

    }

    private static void adicionarAcao() {
        System.out.println("\nAdicionar nova Ação\n");

        String nome = Console.lerString("Nome: ");

        try {
            nomeEmUso(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        String tipoAcao = Console.lerString("Tipo: ");

        while (true) {
            String pagaDividendos = Console.lerString("A ação " + nome + " paga dividendos? [S/N] ").toLowerCase();

            if (pagaDividendos.equals("n")) {
                AtivosController.cadastrarAtivo(nome, tipoAcao, false);
                return;
            }

            if (pagaDividendos.equals("s")) {
                AtivosController.cadastrarAtivo(nome, tipoAcao, true);
                return;
            }

            System.out.println("Opção inválida. Tente novamente...");
        }

    }

    private static void nomeEmUso(String nome) throws Exception{
        ArrayList<Acao> temp = new ArrayList<>();
        try {
            for (Acao acao : listarAcoes()) {
                temp.add(acao);
            }
        } catch (Exception e) {}

        for (Acao acao : temp) {
            if (acao.getNome().equals(nome)) {
                throw new Exception("Nome já está em uso!");
            }
        }
    }
  
    private static ArrayList<Acao> listarAcoes() throws Exception {
        ArrayList<Acao> lista = new ArrayList<>();
        try {
            for (Ativo ativo : AtivosController.getAtivosConta()) {
                if (ativo instanceof Acao) {
                    lista.add((Acao) ativo);
                }
            }
        } catch (Exception e) {}

        if (lista.isEmpty()) {
            throw new Exception("Não há ações cadastradas!");
        }
        return lista;
    }

    private static void exibirAcoes() {
        try {
            for (Acao acao : listarAcoes()) {
                String txt = "Nome: " + acao.getNome() + " | Saldo: " + acao.getSaldo();
                System.out.println(txt);
            }
        } catch (Exception e) {}
    }
}
