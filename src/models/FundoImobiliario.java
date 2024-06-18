package models;

import java.util.UUID;

public class FundoImobiliario extends Ativo {

    private String tipoFundo;

    public FundoImobiliario(){}

    public FundoImobiliario(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String tipoFundo) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
        this.tipoFundo = tipoFundo;
    }

    public String getTipoFundo() {
        return tipoFundo;
    }

    public void setTipoFundo(String tipoFundo) {
        this.tipoFundo = tipoFundo;
    }

    @Override
    public String toString() {
        return super.toString() + "Tipo do Fundo Imobiliário: " + tipoFundo;
    }

}
