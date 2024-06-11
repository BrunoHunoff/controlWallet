package models;

public class Usuario extends Pessoa {
    
    private String usuario;
    private String senha;

    public Usuario(String nome, int idade, String usuario, String senha) {
        super(nome, idade);
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nUsuário: " + usuario;
    }    
    
}
