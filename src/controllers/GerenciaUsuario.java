package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class GerenciaUsuario {

    private static final String ARQUIVO = "usuario.txt";
    private List<Usuario> usuarios;

    public GerenciaUsuario() {
        usuarios = new ArrayList<>();
        carregaUsuarios();
    }

    private void carregaUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                usuarios.add(Usuario.fromString(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private void salvaUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    private void criarUsuario(String usuario, String senha, String nome, int idade) {
        String hashSenha = DigestUtils.sha256Hex(senha);
        Usuario usuario2 = new Usuario(usuario, hashSenha, nome, idade);
        usuarios.add(usuario2);
        salvaUsuarios();
    }



    
}
