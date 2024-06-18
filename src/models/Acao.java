package models;

import java.util.UUID;

public class Acao extends Ativo {

    private String tipoAcao;
    private boolean pagaDividendos;

    public Acao(){}

    public Acao(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String tipoAcao, boolean pagaDividendos) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
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
