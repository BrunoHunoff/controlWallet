package models;

import java.time.LocalDate;
import java.util.UUID;

public class RendaFixa extends Ativo {

    private String categoria;
    private LocalDate dataVencimento;
    private float txJuros;

    public RendaFixa(String parte, String tipoAtivo1, float parseFloat, int parseInt){}

    public RendaFixa(UUID uuid, String nome, String tipoAtivo, float precoMedio, int quantidade, String categoria, LocalDate dataVencimento, float txJuros) {
        super(uuid, nome, tipoAtivo, precoMedio, quantidade);
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.txJuros = txJuros;
    }

    public RendaFixa(String nome, String tipoAtivo, float precoMedio, int quantidade, String categoria, LocalDate dataVencimento, float txJuros) {
        super(nome, tipoAtivo, precoMedio, quantidade);
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

    public static RendaFixa fromString(String linha) {
        String[] atributos = linha.split(", ");

        return new RendaFixa(UUID.fromString(atributos[0]), atributos[1],
                atributos[2], Float.parseFloat(atributos[3]),
                Integer.parseInt(atributos[4]), atributos[5], LocalDate.parse(atributos[6]), Float.parseFloat(atributos[7]));
    }

    @Override
    public String toString() {
        return super.toString() + ", " + categoria + ", " + dataVencimento + ", " + txJuros;
    }

}
