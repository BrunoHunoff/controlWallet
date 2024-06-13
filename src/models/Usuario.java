package models;

import java.util.UUID;
public class Usuario {
    
    private String idUsuario;
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;

    public Usuario(String nomeCompleto, String nomeUsuario, String senha) {
        this.idUsuario = UUID.randomUUID().toString();
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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
        return idUsuario + "," + nomeCompleto + "," + nomeUsuario + "," + senha;
    }

    public static Usuario fromString(String usuarioString) {
        String[] partes = usuarioString.split(",");
        Usuario usuario = new Usuario(partes[1], partes[2], partes[3]);
        usuario.idUsuario = partes[0];
        return usuario;
    }
    
}
