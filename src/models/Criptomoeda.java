package models;

import java.util.UUID;

public class Criptomoeda extends Ativo {

    private String tipoMoeda;
    private String rede;

    public Criptomoeda(){}

    public Criptomoeda(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String tipoMoeda, String rede) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
        this.tipoMoeda = tipoMoeda;
        this.rede = rede;
    }

    public Criptomoeda(String nome, String tipoAtivo, float precoMedio, int quantidade, String tipoMoeda, String rede) {
        super(nome, tipoAtivo, precoMedio, quantidade);
        this.tipoMoeda = tipoMoeda;
        this.rede = rede;
    }

    public String getRede() {
        return rede;
    }

    public Criptomoeda setRede(String rede) {
        this.rede = rede;
        return this;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public static Criptomoeda fromString(String linha) {
        String[] atributos = linha.split(", ");

        return new Criptomoeda(UUID.fromString(atributos[0]), atributos[1],
                atributos[2], Float.parseFloat(atributos[3]),
                Integer.parseInt(atributos[4]), atributos[5], atributos[6]);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + tipoMoeda + ", " + rede;
    }

}
