package controllers;

import models.Acao;
import models.Ativo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AtivosController{

    private static final String ATIVOS_USUARIOS = "ativos_usuarios.txt";

    public static ArrayList<Ativo> ativosConta = new ArrayList<Ativo>();

    public static void lerAtivos() throws IOException{
        try (FileReader fileReader = new FileReader(ATIVOS_USUARIOS);
            BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            String linhaAtual;

            while ((linhaAtual = bufferedReader.readLine()) != null) {
                String[] splitTipo = linhaAtual.split(",");
                String tipoAtivo = splitTipo[0];

                switch (tipoAtivo) {
                    case "Acao":
                        Acao temp = new Acao();
                        temp = Acao.fromString(linhaAtual);

                        ativosConta.add(temp);
                }
            }
        }
    }

}
