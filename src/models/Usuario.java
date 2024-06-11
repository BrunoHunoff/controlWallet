package models;

import java.util.UUID;

public class Usuario extends Pessoa {
    
    private String idUsuario;
    private String usuario;
    private String senha;

    public Usuario(String usuario, String senha, String nome, int idade) {
        super(nome, idade);
        this.idUsuario = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.senha = senha;
        
    }

    public String getIdUsuario() {
        return idUsuario;
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
        // retorna nome e idade da superclasse
        return idUsuario + "," + usuario + "," + senha + "," + super.toString();
    }

    public static Usuario fromString(String usuarioString) {
        String[] partes = usuarioString.split(",");
        Usuario usuario = new Usuario(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]));
        usuario.idUsuario = partes[0];
        return usuario;
    }
    
}
