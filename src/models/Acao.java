package models;

public class Acao extends Ativo {

    private String tipoAcao;
    private boolean pagaDividendos;

    public Acao(int Quantidade, String nome, float precoMedio, String tipoAcao, boolean pagaDividendos) {
        super(Quantidade, nome, precoMedio);
        this.tipoAcao = tipoAcao;
        this.pagaDividendos = pagaDividendos;
    }

    public boolean isPagaDividendos() {
        return pagaDividendos;
    }

    public Acao setPagaDividendos(boolean pagaDividendos) {
        this.pagaDividendos = pagaDividendos;
        return this;
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
