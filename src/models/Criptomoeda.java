package models;

public class Criptomoeda extends Ativo {

    private String tipoMoeda;

    public Criptomoeda(String tipoMoeda, int Quantidade, String nome, float precoMedio) {
        super(Quantidade, nome, precoMedio);
        this.tipoMoeda = tipoMoeda;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo da moeda:" + tipoMoeda;
    }

}
