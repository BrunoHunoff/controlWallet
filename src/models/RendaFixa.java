package models;

import controllers.AtivosController;

import java.time.LocalDate;
import java.util.UUID;

public class RendaFixa extends Ativo {

    private String categoria;
    private String dataVencimento;
    private float txJuros;

    public RendaFixa(String parte, String tipoAtivo1, float parseFloat, int parseInt){}

    public RendaFixa(String nome, String categoria, String dataVencimento, float txJuros) {
        super(nome);
        super.setTipoAtivo("Renda fixa");
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.txJuros = txJuros;
    }

    public RendaFixa(String nome, String tipoAtivo, float preco, int quantidade, float saldo, String categoria, String dataVencimento, float txJuros) {
        super(nome, tipoAtivo, preco, quantidade, saldo);
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.txJuros = txJuros;
    }

    public RendaFixa(UUID uuid, String nome, String tipoAtivo, float preco, int quantidade, float saldo, String categoria, String dataVencimento, float txJuros) {
        super(uuid, nome, tipoAtivo, preco, quantidade, saldo);
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.txJuros = txJuros;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String TipoRenda) {
        this.categoria = TipoRenda;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public float getTxJuros() {
        return txJuros;
    }

    public void setTxJuros(float txJuros) {
        this.txJuros = txJuros;
    }

    @Override
    public boolean nomeExiste(String nome) throws Exception {
        if (AtivosController.buscarRendaFixa(nome) != null) {
            return true;
        }
        return false;
    }

    public static RendaFixa fromString(String linha) {
        String[] atributos = linha.split(", ");

        return new RendaFixa(atributos[1],
                atributos[2], atributos[6], Float.parseFloat(atributos[8]));
    }

    @Override
    public String toString() {
        return super.toString() + ", " + categoria + ", " + dataVencimento + ", " + txJuros;
    }

}
