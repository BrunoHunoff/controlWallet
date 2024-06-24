import controllers.GerenciarUsuario;
import models.Ativo;
import models.Usuario;

public static String gerarRelatorio(String idUsuario){
    Usuario usuario;
    try{
        usuario = GerenciarUsuario.buscarUsuario(idUsuario);
    }catch(Exception e){
        System.out.println("Erro ao buscar usuário: " + e.getMessage());
        return;
    }

    float totalInvestido = 0;
    for(Ativo ativo : usuario.getCarteira()){
        totalInvestido += ativo.getPreco() * ativo.getQuantidade();
    }

    System.out.println("\nRELATÓRIO DE INVESTIMENTOS");
    System.out.println("\nUsuário: " + usuario.getNomeCompleto());
    System.out.println("\nTotal investido: " + totalInvestido);

    System.out.println("\nAtivos em carteira");
    for(Ativo ativo : usuario.getCarteira()){
        System.out.println("\n" + ativo);
    }
    
}