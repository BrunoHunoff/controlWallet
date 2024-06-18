package models;

public class Acao extends Investimentos {

    private String tipoAcao;

    public Acao(String tipoAcao, int Quantidade, String nome, float precoMedio) {
        super(Quantidade, nome, precoMedio);
        this.tipoAcao = tipoAcao;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    @Override
    public String toString() {
        return super.toString() + "Tipo da Ação: " + tipoAcao;
    }

}
