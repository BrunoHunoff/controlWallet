
import java.time.LocalDate;

public class RendaFixa extends Investimentos {

    private String TipoRenda;
    private LocalDate dataVencimento;
    private float txJuros;

    public RendaFixa(String TipoRenda, LocalDate dataVencimento, float txJuros, int Quantidade, String nome, float precoMedio) {
        super(Quantidade, nome, precoMedio);
        this.TipoRenda = TipoRenda;
        this.dataVencimento = dataVencimento;
        this.txJuros = txJuros;
    }

    public String getTipoRenda() {
        return TipoRenda;
    }

    public void setTipoRenda(String TipoRenda) {
        this.TipoRenda = TipoRenda;
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
        return super.toString() + ", Tipo de Renda: " + TipoRenda + ", Data de Vencimento: " + dataVencimento + ", Taxa de Juros: " + txJuros;
    }

}
