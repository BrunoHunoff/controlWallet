package models;

import java.util.UUID;

public class FundoImobiliario extends Ativo {

    private String tipoFundo;

    public FundoImobiliario(String parte, String tipoAtivo1, float parseFloat, int parseInt){}

    public FundoImobiliario(String nome, String tipoAtivo, float preco, int quantidade, float saldo, String tipoFundo) {
        super(nome, tipoAtivo, preco, quantidade, saldo);
        this.tipoFundo = tipoFundo;
    }

    public FundoImobiliario(String nome, String tipoFundo) {
        super(nome);
        super.setTipoAtivo("FII");
        this.tipoFundo = tipoFundo;
    }

    public FundoImobiliario(UUID uuid, String nome, String tipoAtivo, float preco, int quantidade, float saldo, String tipoFundo) {
        super(uuid, nome, tipoAtivo, preco, quantidade, saldo);
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
                Integer.parseInt(atributos[4]),Float.parseFloat(atributos[5]), atributos[6]);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + tipoFundo;
    }

}
