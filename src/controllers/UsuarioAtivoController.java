package controllers;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class UsuarioAtivoController {

    private static final String ATIVOS_USUARIOS = "ativos_usuarios.txt";
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void carregarUsuariosEAtivos() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ATIVOS_USUARIOS))) {
            String linhaAtual;

            while ((linhaAtual = bufferedReader.readLine()) != null) {
                String[] partes = linhaAtual.split(", ");
                String idUsuario = partes[0];
                Usuario usuario = getUsuarioId(idUsuario);

                if (usuario == null) {
                    usuario = new Usuario(partes[1], partes[2], partes[3]);
                    usuarios.add(usuario);
                }

                UUID idAtivo = UUID.fromString(partes[4]);
                usuario.adicionarAtivo(idAtivo);
            }
        }
    }

    public void salvarUsuariosEAtivos() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ATIVOS_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                for (UUID idAtivo : Usuario.getCarteira()) {
                    bufferedWriter.write(usuario.getIdUsuario() + ", " + idAtivo.toString() + "\n");
                }
            }
        }
    }



    public ArrayList<UUID> listarAtivosDoUsuario(String idUsuario) {
        Usuario usuario = getUsuarioId(idUsuario);
        if (usuario != null) {
            return usuario.getCarteira();
        }
        return new ArrayList<>();
    }

    public void excluirAtivoDoUsuario(String idUsuario, UUID idAtivo) {
        Usuario usuario = getUsuarioId(idUsuario);
        if (usuario != null) {
            usuario.removerAtivo(idAtivo);
        }
    }
    
    private Usuario getUsuarioId(String idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }
}

