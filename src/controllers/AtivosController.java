package controllers;

import models.*;

import java.io.*;
import java.util.ArrayList;

public class AtivosController{

    private static final String ATIVOS_USUARIOS = "ativos_usuarios.txt";

    private static ArrayList<Ativo> ativosConta = new ArrayList<>();

    private static ArrayList<String> linhasTxt = new ArrayList<>();

    public static void lerCarteira(String idUsuario) throws IOException, Exception{

        String[] linha = encontrarCarteira(idUsuario).split("; ");

        for (String ativo: linha) {
            String tipoAtivo = ativo.split(" ,")[2];

            Ativo temp = null;
            switch (tipoAtivo) {
                case "Acao":
                    temp = Acao.fromString(ativo);

                    break;

                case "Fundo Imobiliario":
                    temp = FundoImobiliario.fromString(ativo);

                    break;

                case "NFT":
                    temp = Nft.fromString(ativo);
                    break;

                case "Cripto":
                    temp = Criptomoeda.fromString(ativo);
                    break;

                case "Renda Fixa":
                    temp = RendaFixa.fromString(ativo);
                    break;

                default:
                    throw new Exception("Tipo de ativo não reconhecido: " + tipoAtivo);
            }

            ativosConta.add(temp);
        }

    }

    private static String encontrarCarteira(String idUsuario) throws Exception {

        String idAtual = null;
        String ativos = null;

        for (String linhaAtual : linhasTxt) {
            String[] linhaSplit = linhaAtual.split("carteira: ");
            idAtual = linhaSplit[0];

            if (idAtual.equals(idUsuario)) {
                ativos = linhaSplit[1];
                return ativos;
            }
        }

        throw new Exception("Não existem ativos cadatrados");
    }

    private static void lerArquivo() throws Exception {
        try (FileReader fileReader = new FileReader(ATIVOS_USUARIOS);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                linhasTxt.add(linha);
            }
        }

        if (linhasTxt.isEmpty()) {
            throw new Exception("Arquivo vazio");
        }
    }

    public static void salvarAtivos(String idUsuario) throws IOException {


        try (FileWriter fileWriter = new FileWriter(ATIVOS_USUARIOS);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Ativo temp: ativosConta) {
                bufferedWriter.write(temp.toString() + "\n");
            }
        }
    }
}
