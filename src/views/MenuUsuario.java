package views;

import controllers.AtivosController;
import helpers.Console;

import java.io.IOException;
import java.util.UUID;

import static views.MenuAtivoInterface.finalizar;

public class MenuUsuario{

    private static void exibirMenuUsuario() {
        
        System.out.println("\nControl Wallet");
        System.out.println("1) Gerenciar ativos");
        System.out.println("2) Emitir relatório");
        System.out.println("0) Sair");
    }

    private static boolean verificarOpcaoUsuario(int op, String idUsuario) {
        boolean finalizarPrograma = false;

        switch (op) {
            case 1:
                menuSelecionarAtivo();
                int opcao = Console.lerInt("Selecione o tipo de ativo: ");

                switch (opcao) {

                    case 1:
                        finalizarPrograma = MenuAcao.executarMenuAcao(idUsuario);
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

            case 0:
                finalizarPrograma = true;
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

    public static void executarMenuUsuario(String idUsuario) {

        try {
            AtivosController.lerArquivo();
            AtivosController.lerCarteira(idUsuario);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {}

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
