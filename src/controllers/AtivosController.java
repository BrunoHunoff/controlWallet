package controllers;

import models.*;

import java.io.*;
import java.util.ArrayList;

public class AtivosController{

    private static final String ATIVOS_USUARIOS = "ativos_usuarios.txt";

    private static ArrayList<Ativo> ativosConta = new ArrayList<>();

    private static ArrayList<String> linhasTxt = new ArrayList<>();

    public static void lerCarteira(String idUsuario) throws IOException, Exception{

        String linha = linhasTxt.get(encontrarCarteira(idUsuario));

        String[] carteira = (linha.split("carteira: ")[1]).split(", ");

        for (String ativo: carteira) {
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

    private static String[] separaIdCarteira(String linha) {
        return linha.split("carteira: ");
    }

    private static int encontrarCarteira(String idUsuario) throws Exception {

        String idAtual = null;

        for (String linhaAtual : linhasTxt) {
            String[] linhaSplit = separaIdCarteira(linhaAtual);
            idAtual = linhaSplit[0];

            if (idAtual.equals(idUsuario)) {

                return linhasTxt.indexOf(linhaAtual);
            }
        }

        throw new Exception("Carteira não encontrada");
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
            for (String linha: linhasTxt) {
                //se id da linha for diferente de id usuario -> sobrescreve linha
                if (!(separaIdCarteira(linha)[0].equals(idUsuario))) {
                    bufferedWriter.write(linha);
                } else {
                    String linhaUsuario = idUsuario + "carteira: ";
                    for (Ativo ativo: ativosConta) {
                        linhaUsuario += ativo.toString();
                    }
                    bufferedWriter.write(linhaUsuario);
                }
            }
        }
    }
}
