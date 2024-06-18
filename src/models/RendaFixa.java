package models;

import java.time.LocalDate;
import java.util.UUID;

public class RendaFixa extends Ativo {

    private String categoria;
    private LocalDate dataVencimento;
    private float txJuros;

    public RendaFixa(){}

    public RendaFixa(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String categoria, LocalDate dataVencimento, float txJuros) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public float getTxJuros() {
        return txJuros;
    }

    public void setTxJuros(float txJuros) {
        this.txJuros = txJuros;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo de Renda: " + categoria + ", Data de Vencimento: " + dataVencimento + ", Taxa de Juros: " + txJuros;
    }

}
