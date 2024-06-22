package models;

import java.util.UUID;

public class Nft extends Ativo {

    private String descricao;
    private String autor;

    public Nft(String parte, String tipoAtivo1, float parseFloat, int parseInt){}

    public Nft(String nome, String descricao, String autor) {
        super(nome);
        super.setTipoAtivo("NFT");
        this.descricao = descricao;
        this.autor = autor;
    }

    public Nft(String nome, String tipoAtivo, float preco, int quantidade, float saldo, String descricao, String autor) {
        super(nome, tipoAtivo, preco, quantidade, saldo);
        this.descricao = descricao;
        this.autor = autor;
    }

    public Nft(UUID uuid, String nome, String tipoAtivo, float preco, int quantidade, float saldo, String descricao, String autor) {
        super(uuid, nome, tipoAtivo, preco, quantidade, saldo);
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

    public static Nft fromString(String linha) {
        String[] atributos = linha.split(", ");

        return new Nft(UUID.fromString(atributos[0]), atributos[1],
                atributos[2], Float.parseFloat(atributos[3]),
                Integer.parseInt(atributos[4]), Float.parseFloat(atributos[5]), atributos[6], atributos[7]);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + descricao + ", " + autor;
    }

}
