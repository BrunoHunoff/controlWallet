package models;

import java.util.UUID;

public class FundoImobiliario extends Ativo {

    private String tipoFundo;

    public FundoImobiliario(){}

    public FundoImobiliario(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String tipoFundo) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
        this.tipoFundo = tipoFundo;
    }

    public FundoImobiliario(String nome, String tipoAtivo, float precoMedio, int quantidade, String tipoFundo) {
        super(nome, tipoAtivo, precoMedio, quantidade);
        this.tipoFundo = tipoFundo;
    }

    public String getTipoFundo() {
        return tipoFundo;
    }

    public void setTipoFundo(String tipoFundo) {
        this.tipoFundo = tipoFundo;
    }

    public static FundoImobiliario fromString(String linha) {
        String[] atributos = linha.split(", ");

        return new FundoImobiliario(UUID.fromString(atributos[0]), atributos[1],
                atributos[2], Float.parseFloat(atributos[3]),
                Integer.parseInt(atributos[4]), atributos[5]);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + tipoFundo;
    }

}
