package models;

public class Criptomoeda extends Ativo {

    private String tipoMoeda;
    private String rede;

    public Criptomoeda(int Quantidade, String nome, float precoMedio, String tipoMoeda, String rede) {
        super(Quantidade, nome, precoMedio);
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

    @Override
    public String toString() {
        return super.toString() + ", Tipo da moeda:" + tipoMoeda;
    }

}
