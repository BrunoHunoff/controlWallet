package controllers;

import models.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AtivosController{

    private static final String ATIVOS_USUARIOS = "ativos_usuarios.txt";

    private static ArrayList<Ativo> ativosConta = new ArrayList<>();

    private static ArrayList<String> linhasTxt = new ArrayList<>();

    //metodos auxiliares para leitura e gravação

    public static void lerCarteira(String idUsuario) throws Exception{

        String linha = linhasTxt.get(encontrarCarteira(idUsuario));

        String[] linhaSplit = linha.split(" carteira: ");

        String[] carteira = linhaSplit[1].split("; ");

        for (String ativo: carteira) {

            String tipoAtivo = ativo.split(", ")[2];

            Ativo temp = null;
            switch (tipoAtivo) {
                case "Acao":
                    temp = Acao.fromString(ativo);

                    break;

                case "FII":
                    temp = FundoImobiliario.fromString(ativo);

                    break;

                case "NFT":
                    temp = Nft.fromString(ativo);
                    break;

                case "Cripto":
                    temp = Criptomoeda.fromString(ativo);
                    break;

                case "Renda fixa":
                    temp = RendaFixa.fromString(ativo);
                    break;

                default:
                    throw new Exception("Tipo de ativo não reconhecido: " + tipoAtivo);
            }

            ativosConta.add(temp);
        }

    }

    private static String[] separaIdCarteira(String linha) {
        return linha.split(" carteira: ");
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

    //classes de leitura e gravação em arquivo

    public static void lerArquivo() throws Exception {
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

    public static void salvarArquivo(String idUsuario) throws IOException {

        try (FileWriter fileWriter = new FileWriter(ATIVOS_USUARIOS);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            if (linhasTxt.isEmpty()) {

                bufferedWriter.write(cadastrarNovaCarteira(idUsuario));
                return;

            }
            boolean encontrou = false;
            for (String linha: linhasTxt) {


                String[] linhaAtualSplit = linha.split(" carteira: ");

                if (linhaAtualSplit[0].equals(idUsuario)) {
                    encontrou = true;
                    String linhaUsuario = idUsuario + " carteira: ";
                    for (Ativo ativo: ativosConta) {
                        linhaUsuario += ativo.toString() + "; ";
                    }
                    bufferedWriter.write(linhaUsuario + "\n");
                } else {
                    bufferedWriter.write(linha + "\n");
                }
            }
            if (!encontrou) {
                bufferedWriter.write(cadastrarNovaCarteira(idUsuario));
            }
        }

    }

    private static String cadastrarNovaCarteira(String idUsuario) {
        String linhaUsuario = idUsuario + " carteira: ";

        for (Ativo ativo: ativosConta) {
            linhaUsuario += ativo.toString() + "; ";
        }

        return linhaUsuario;
    }

    //CRUD

        //cadastro Acao
    public static void cadastrarAtivo(String nome, String tipoAcao, boolean pagaDividendos){

        ativosConta.add(new Acao(nome, tipoAcao, pagaDividendos));
    }

        //cadastro Cripto e NFT
    public static void cadastrarAtivo(String nome, String tipoAtivo, String parametro1, String parametro2){
        switch (tipoAtivo) {
            case "Cripto":
                ativosConta.add(new Criptomoeda(nome, parametro1, parametro2));
                break;

            case "NFT":

                ativosConta.add(new Nft(nome, parametro1, parametro2));
                break;
        }
    }

        //cadastro FII
    public static void cadastrarAtivo(String nome, String tipoFundo){

        ativosConta.add(new FundoImobiliario(nome, tipoFundo));
    }

        //cadastro Renda Fixa
    public static void cadastrarAtivo(String nome, String categoria, String dataVencimento, float txJuros){


        ativosConta.add(new RendaFixa(nome, categoria, dataVencimento, txJuros));
    }

    public static Ativo buscarAtivo(String nome) throws Exception {
        for (Ativo ativo: ativosConta) {
            if (ativo.getNome().contains(nome)) {
                return ativo;
            }
        }
        throw new Exception("Ativo não encontrado");
    }

    //.equals pois é preciso que a busca seja precisa
    public static Nft buscarNft(String nome) throws Exception {
        for (Ativo ativo: ativosConta) {
            if (ativo instanceof Nft && ativo.getNome().equals(nome)) {
                return (Nft)ativo;
            }
        }
        throw new Exception("NFT não encontrada");
    }

    public static Acao buscarAcao(String nome) throws Exception {
        for (Ativo ativo: ativosConta) {
            if (ativo instanceof Acao && ativo.getNome().equals(nome)) {
                return (Acao)ativo;
            }
        }
        throw new Exception("Ação não encontrada");
    }

    public static FundoImobiliario buscarFii(String nome) throws Exception {
        for (Ativo ativo: ativosConta) {
            if (ativo instanceof FundoImobiliario && ativo.getNome().equals(nome)) {
                return (FundoImobiliario) ativo;
            }
        }
        throw new Exception("FII não encontrado");
    }

    public static Criptomoeda buscarCripto(String nome) throws Exception {
        for (Ativo ativo: ativosConta) {
            if (ativo instanceof Criptomoeda && ativo.getNome().equals(nome)) {
                return (Criptomoeda) ativo;
            }
        }
        throw new Exception("Criptomoeda não encontrada");
    }

    public static RendaFixa buscarRendaFixa(String nome) throws Exception {
        for (Ativo ativo: ativosConta) {
            if (ativo instanceof RendaFixa && ativo.getNome().equals(nome)) {
                return (RendaFixa) ativo;
            }
        }
        throw new Exception("Renda fixa não encontrada");
    }

     public static void deletarAtivo(Ativo temp) {

        ativosConta.remove(temp);
     }

     public static ArrayList<Ativo> getAtivosConta() throws Exception{
        if (ativosConta.isEmpty()) {
            throw new Exception("Não há ativos cadastrados");
        }

        return ativosConta;
     }

}
