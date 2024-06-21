package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class GerenciarUsuario {

    private static final String ARQUIVO = "usuarios.txt";
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                usuarios.add(Usuario.fromString(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public static void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    // chat GPT
    private static String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes());
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void criarUsuario(String nomeCompleto, String nomeUsuario, String senha) {
        String hashSenha = criptografarSenha(senha);
        Usuario usuario = new Usuario(nomeCompleto, nomeUsuario.toLowerCase(), hashSenha);
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public static List<Usuario> listarTodos() {
        return usuarios;
    }

    public static Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNomeCompleto().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public static void atualizarUsuario(String nomeAtual, String novoNomeCompleto, String novoNomeUsuario, String novaSenha) {
        Usuario usuario = buscarUsuario(nomeAtual);
        if (usuario != null) {
            usuario.setNomeCompleto(novoNomeCompleto);
            usuario.setLogin(novoNomeUsuario.toLowerCase());
            usuario.setSenha(criptografarSenha(novaSenha));
            salvarUsuarios();
        }
    }

    public static void deletarUsuario(String nome) {
        Usuario usuario = buscarUsuario(nome);
        if (usuario != null) {
            usuarios.remove(usuario);
            salvarUsuarios();
        }
    }

    public static Boolean nomeUsuarioExiste(String nomeUsuario) {
        String nomeUsuarioMinusculo = nomeUsuario.toLowerCase();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().toLowerCase().equals(nomeUsuarioMinusculo)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario loginValido(String nomeUsuario, String senha) throws Exception{

        String nomeUsuarioMinusculo = nomeUsuario.toLowerCase();

        if (usuarios.isEmpty()) {
            throw new Exception("Não há usuários cadastrados");
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().toLowerCase().equals(nomeUsuarioMinusculo) &&
                usuario.getSenha().equals(criptografarSenha(senha))) {
                return usuario;
            }
        }
        return null;
    }

}
