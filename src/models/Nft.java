package models;

public class Nft extends Ativo {

    private String descricao;
    private String autor;

    public Nft(int Quantidade, String nome, float precoMedio, String descricao, String autor) {
        super(Quantidade, nome, precoMedio);
        this.descricao = descricao;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public Nft setAutor(String autor) {
        this.autor = autor;
        return this;
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
