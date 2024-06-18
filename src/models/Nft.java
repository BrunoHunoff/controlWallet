package models;

public class Nft extends Ativo {

    private String descricao;

    public Nft(String descricao, int Quantidade, String nome, float precoMedio) {
        super(Quantidade, nome, precoMedio);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String TipoNFT) {
        this.descricao = TipoNFT;
    }

    @Override
    public String toString() {
        return super.toString() + "Tipo da models.NFT:" + descricao;
    }

}
