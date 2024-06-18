package models;

public class FundoImobiliario extends Ativo {

    private String tipoFundo;

    public FundoImobiliario(String tipoFundo, int Quantidade, String nome, float precoMedio) {
        super(Quantidade, nome, precoMedio);
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
