package tests;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import helpers.Console;
import models.*;

import java.io.IOException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        teste3();
    }

    public static void teste1() {
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();

        int opcao;

        do {
            System.out.println("1. Criar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Deletar Usuário");
            System.out.println("5. Sair");
            opcao = Console.lerInt("Escolha uma opção: ");


            switch (opcao) {
                case 1:
                    String nomeCompleto = Console.lerString("Digite o nome completo do usuário: ");
                    String nomeUsuario = Console.lerString("Digite o nome de usuario: ");
                    while (gerenciarUsuario.nomeUsuarioExiste(nomeUsuario)) {
                        nomeUsuario = Console.lerString("Usuário inválido. Digite novamente o nome de usuario: ");
                    };
                    String senha = Console.lerString("Digite a senha: ");

                    gerenciarUsuario.criarUsuario(nomeCompleto, nomeUsuario, senha);
                    break;

                case 2:
                    for (Usuario usuario : gerenciarUsuario.listarTodos()) {
                        System.out.println(usuario);
                    }
                    break;

                case 3:
                    String idUsuarioAtualizar = Console.lerString("Digite o ID do usuário: ");
                    String novoNomeCompleto = Console.lerString("Digite o novo nome completo do usuário: ");
                    String novoNomeUsuario = Console.lerString("Digite o novo nome de usuario: ");
                    String novaSenha = Console.lerString("Digite a nova senha: ");

                    gerenciarUsuario.atualizarUsuario(idUsuarioAtualizar, novoNomeCompleto, novoNomeUsuario, novaSenha);
                    break;

                case 4:
                    String idUsuarioDeletar = Console.lerString("Digite o ID do usuário: ");

                    gerenciarUsuario.deletarUsuario(idUsuarioDeletar);
                    break;

                case 5:
                    System.out.println("Finalizando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    //teste gravação de ativos
    public static void teste2() {
        Acao a1 = new Acao("CMIG4", "Acao", 35.5f, 10, "Ordinaria", true);

        Criptomoeda c1 = new Criptomoeda("DOG", "Cripto", 0.0060f, 20000, "Memecoin", "BTC");

        FundoImobiliario f1 = new FundoImobiliario("RBRF11", "Fundo Imobiliario", 7.88f, 11, "Papel");

        Nft n1 = new Nft("Macaco", "NFT", 20000.00f, 1, "Moeda do macaco", "Antonio");

        RendaFixa r1 = new RendaFixa("CDB", "Renda Fixa", 100, 1, "CDB", LocalDate.of(2030, 12, 31), 11.5f);

        AtivosController.ativosConta.add(a1);
        AtivosController.ativosConta.add(c1);
        AtivosController.ativosConta.add(f1);
        AtivosController.ativosConta.add(n1);
        AtivosController.ativosConta.add(r1);

        try {
            AtivosController.salvarAtivos();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //teste leitura de ativos
    public static void teste3(){
        try {
            AtivosController.lerAtivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (Ativo temp: AtivosController.ativosConta) {
            System.out.println(temp + "\n");
        }
    }
}
