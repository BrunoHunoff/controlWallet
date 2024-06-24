package views;

import java.util.ArrayList;

import controllers.AtivosController;
import helpers.Console;
import models.Acao;
import models.Ativo;
import models.FundoImobiliario;
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

        int opcao = Console.lerInt("Informe sua opção: ");

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
                // método transação
                break;

            default:
                System.out.println("Opção inválida! Tente Novamente...");
                break;
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

            AtivosController.cadastrarAtivo(nome, tipoAcao);           
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

                if (!(tempAcao instanceof Acao)) {
                    throw new Exception("Não foi possível encontrar ação cadastrada com esse nome!");
                }

                System.out.println("\nAção encontrada! Preencha os dados para editar:\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            tempAcao = (Acao)tempAcao;

            tempAcao.setNome(Console.lerString("Nome: "));
            ((Acao) tempAcao).setTipoAcao(Console.lerString("Tipo da Ação: "));
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

        private static void nomeEmUso(String nome) throws Exception{
            for (Acao acao : listarAcoes()) {
                if (acao.getNome().equals(nome)) {
                    throw new Exception("Nome já está em uso!");
                }
            }
        }

        private static ArrayList<Acao> listarAcoes() {
            ArrayList<Acao> lista = new ArrayList<>();
            for (Ativo ativo: AtivosController.getAtivosConta()) {
                if (ativo instanceof Acao) {
                    lista.add((Acao) ativo);
                }
            }
            return lista;
        }

        private static void exibirAcoes() {
            for (Acao acao : listarAcoes()) {
                String txt = "Nome: " + acao.getNome() + " | Saldo: " + acao.getSaldo();
                System.out.println(txt);
            }
        }

}
