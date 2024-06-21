package models;

import java.util.UUID;

public abstract class Ativo {

    private UUID uuid;
    protected String nome;
    protected String tipoAtivo;
    protected float preco;
    protected int quantidade;
    protected float saldo;

    public Ativo(){}

    public Ativo(String nome, String tipoAtivo, float preco, int quantidade, float saldo) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
        this.tipoAtivo = tipoAtivo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.saldo = saldo;
    }

    public Ativo(UUID uuid, String nome, String tipoAtivo, float preco, int quantidade, float saldo) {
        this.uuid = uuid;
        this.nome = nome;
        this.tipoAtivo = tipoAtivo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.saldo = saldo;
    }

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public Ativo setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
        return this;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.quantidade = Quantidade;
    }

    public float calcularValorTotal() {
        return preco * quantidade;
    }

    @Override
    public String toString() {
        return uuid + ", " + nome + ", " + tipoAtivo + ", " + preco + ", " + quantidade + ", " + saldo;
    }

}
