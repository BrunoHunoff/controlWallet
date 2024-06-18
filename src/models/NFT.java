package models;

public class NFT extends Investimentos {

    private String TipoNFT;

    public NFT(String TipoNFT, int Quantidade, String nome, float precoMedio) {
        super(Quantidade, nome, precoMedio);
        this.TipoNFT = TipoNFT;
    }

    public String getTipoNFT() {
        return TipoNFT;
    }

    public void setTipoNFT(String TipoNFT) {
        this.TipoNFT = TipoNFT;
    }

    @Override
    public String toString() {
        return super.toString() + "Tipo da models.NFT:" + TipoNFT;
    }

}
