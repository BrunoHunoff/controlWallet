package models;

import java.util.UUID;

public abstract class Ativo {

    protected UUID uuid;
    protected String nome;
    protected float precoMedio;
    protected int quantidade;

    public Ativo(int Quantidade, String nome, float precoMedio) {
        this.uuid = UUID.randomUUID();
        this.quantidade = Quantidade;
        this.nome = nome;
        this.precoMedio = precoMedio;
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

    @Override
    public String toString() {
        return "Nome: " + nome + ", Preço Médio:" + precoMedio + ", Quantidade: " + quantidade;
    }

}
