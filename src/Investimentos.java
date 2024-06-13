
public abstract class Investimentos {

    protected String nome;
    protected float precoMedio;
    protected int Quantidade;

    public Investimentos(int Quantidade, String nome, float precoMedio) {
        this.Quantidade = Quantidade;
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
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public float calcularValorTotal() {
        return precoMedio * Quantidade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Preço Médio:" + precoMedio + ", Quantidade: " + Quantidade;
    }

}
