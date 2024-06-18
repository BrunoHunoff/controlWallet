package models;

import java.util.UUID;

public class Nft extends Ativo {

    private String descricao;
    private String autor;

    public Nft(){}

    public Nft(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String descricao, String autor) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
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
