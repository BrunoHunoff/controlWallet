package models;

import java.util.UUID;

public abstract class Ativo {

    protected UUID uuid;
    protected String nome;
    protected String tipoAtivo;
    protected float precoMedio;
    protected int quantidade;

    public Ativo(){}

    public Ativo(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade) {
        this.uuid = uuid;
        this.nome = nome;
        this.tipoAtivo = tipoAtivo;
        this.precoMedio = precoMedio;
        this.quantidade = quantidade;
    }

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public Ativo setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(float precoMedio) {
        this.precoMedio = precoMedio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.quantidade = Quantidade;
    }

    public float calcularValorTotal() {
        return precoMedio * quantidade;
    }

    public abstract Ativo fromString();

    @Override
    public String toString() {
        return "Nome: " + nome + ", Preço Médio:" + precoMedio + ", Quantidade: " + quantidade;
    }

}
