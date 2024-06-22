package models;

import java.util.ArrayList;
import java.util.UUID;
public class Usuario {
    
    private String idUsuario;
    private String nomeCompleto;
    private String login;
    private String senha;
    private ArrayList<Ativo> carteira;

    public Usuario(String nomeCompleto, String login, String senha) {
        this.idUsuario = UUID.randomUUID().toString();
        this.nomeCompleto = nomeCompleto;
        this.login = login;
        this.carteira = new ArrayList<Ativo>();
        this.senha = senha;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public UUID getUuid(UUID uuid) {
        return UUID.fromString(idUsuario); 
    }

    @Override
    public String toString() {
        // retorna nome e idade da superclasse
        return idUsuario + "," + nomeCompleto + "," + login + "," + senha;
    }

    public static Usuario fromString(String usuarioString) {
        String[] partes = usuarioString.split(",");
        Usuario usuario = new Usuario(partes[1], partes[2], partes[3]);
        usuario.idUsuario = partes[0];
        return usuario;
    }
    
}
