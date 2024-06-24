package views;

import controllers.AtivosController;
import controllers.GerenciarUsuario;
import helpers.Console;

import java.util.UUID;

import static views.MenuAtivoInterface.finalizar;

public class MenuUsuario{

    private static void exibirMenuUsuario() {
        
        System.out.println("\nControl Wallet");
        System.out.println("1) Gerenciar ativos");
        System.out.println("2) Emitir relatório");
    }

    private static boolean verificarOpcaoUsuario(int op, String idUsuario) {
        boolean finalizarPrograma = false;

        switch (op) {
            case 1:
                menuSelecionarAtivo();
                int opcao = Console.lerInt("Selecione o tipo de ativo: ");

                switch (opcao) {

                    case 1:
                        //MenuAcao.exibirMenu();
                        break;
                    case 2:
                        finalizarPrograma = MenuCripto.executarMenuCripto(idUsuario);
                        break;
                    
                    case 3:
                        finalizarPrograma = MenuFundoImobiliario.executarMenuFundo(idUsuario);
                        break;
                    
                    case 4:
                        finalizarPrograma = MenuNft.executarMenuNft(idUsuario);

                        break;
                
                    case 5:
                        finalizarPrograma = MenuRendaFixa.executarMenuRendaFixa(idUsuario);

                        break;
                    
                    default:
                        break;
                }

            case 2:
                MenuRelatorio.gerarRelatorio(UUID.fromString(idUsuario));
                break;
            
            default:
                System.out.println("\nOpção inválida, digite novamente");
            }
            return finalizarPrograma;
        }

    private static void menuSelecionarAtivo() {

        System.out.println("\nSELEÇÃO DE ATIVO");
        System.out.println("1) Ação");
        System.out.println("2) Criptomoeda");
        System.out.println("3) Fundo imobiliário");
        System.out.println("4) NFT");
        System.out.println("5) Renda fixa");
    }
    
    private static void exibirMenuAtivos() {

        System.out.println("\n1) Cadastrar");
        System.out.println("2) Listar");
        System.out.println("3) Atualizar");
        System.out.println("4) Excluir");
    }


    public static void executarMenuUsuario(String idUsuario) {

        try {
            AtivosController.lerArquivo();
            AtivosController.lerCarteira(idUsuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while(true) {
            boolean finalizarPrograma = false;

            exibirMenuUsuario();

            int op = Console.lerInt("Informe sua opção: ");

            finalizarPrograma = verificarOpcaoUsuario(op, idUsuario);

            if (finalizarPrograma) {
                finalizar(idUsuario);
                return;
            }
        }
    }
}
