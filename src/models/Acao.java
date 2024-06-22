package models;

import java.util.UUID;

public class Acao extends Ativo {

    private String tipoAcao;
    private boolean pagaDividendos;

    public Acao(String parte, String tipoAtivo1, float parseFloat, int parseInt){}

    public Acao(String nome, String tipoAcao, boolean pagaDividendos) {
        super(nome);
        super.setTipoAtivo("Acao");
        this.tipoAcao = tipoAcao;
        this.pagaDividendos = pagaDividendos;
    }

    public Acao(String nome, String tipoAtivo, float preco, int quantidade, float saldo, String tipoAcao, boolean pagaDividendos) {
        super(nome, tipoAtivo, preco, quantidade, saldo);
        this.tipoAcao = tipoAcao;
        this.pagaDividendos = pagaDividendos;
    }

    public Acao(UUID uuid, String nome, String tipoAtivo, float preco, int quantidade, float saldo, String tipoAcao, boolean pagaDividendos) {
        super(uuid, nome, tipoAtivo, preco, quantidade, saldo);
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

    public static Acao fromString(String linha) {
        String[] atributos = linha.split(", ");

        return new Acao(UUID.fromString(atributos[0]), atributos[1],
                atributos[2], Float.parseFloat(atributos[3]),
                Integer.parseInt(atributos[4]), Float.parseFloat(atributos[5]), atributos[6], Boolean.parseBoolean(atributos[7]));
    }

    @Override
    public String toString() {
        return super.toString() + ", " + tipoAcao + ", " + pagaDividendos;
    }


}
