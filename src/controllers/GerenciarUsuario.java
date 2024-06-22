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

    public static void criarUsuario(String nomeCompleto, String nomeUsuario, String senha) throws Exception{
        if (nomeUsuarioExiste(nomeUsuario)) {
            throw new Exception("\nLogin em já está em uso.");
        }
        String hashSenha = criptografarSenha(senha);
        Usuario usuario = new Usuario(nomeCompleto, nomeUsuario.toLowerCase(), hashSenha);
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public static void senhaIgual(String senha, String confirmacao) throws Exception{
        if (!(senha.equals(confirmacao))) {
            throw new Exception("\nAs senhas não são iguais. Tente novamente!");
        }
    }

    public static List<Usuario> listarTodos() {
        return usuarios;
    }

    public static Usuario buscarUsuario(String login) throws Exception{
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return usuario;
            }
        }
        throw new Exception("Usuario não encontrado!");
    }

    public static void atualizarUsuario(String nomeAtual, String novoNomeCompleto, String novoNomeUsuario, String novaSenha) throws Exception{

        //se login for diferente do atual e já estiver em uso
        if (!(nomeAtual.equals(novoNomeUsuario)) && (nomeUsuarioExiste(novoNomeUsuario))) {
            throw new Exception("\nLogin já está em uso.");

        }

        Usuario usuario = buscarUsuario(nomeAtual);

        if (usuario != null) {
            usuario.setNomeCompleto(novoNomeCompleto);
            usuario.setLogin(novoNomeUsuario.toLowerCase());
            usuario.setSenha(criptografarSenha(novaSenha));
            salvarUsuarios();
        }
    }

    public static void deletarUsuario(String login) throws Exception{
        Usuario usuario = buscarUsuario(login);
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
