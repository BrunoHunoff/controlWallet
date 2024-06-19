package controllers;

import models.*;

import java.io.*;
import java.util.ArrayList;

public class AtivosController{

    private static final String ATIVOS_USUARIOS = "ativos_usuarios.txt";

    public static ArrayList<Ativo> ativosConta = new ArrayList<Ativo>();

    public static void lerAtivos() throws IOException, Exception{
        try (FileReader fileReader = new FileReader(ATIVOS_USUARIOS);
            BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            String linhaAtual;

            while ((linhaAtual = bufferedReader.readLine()) != null) {
                String[] splitTipo = linhaAtual.split(", ");
                String tipoAtivo = splitTipo[2];

                Ativo temp = null;
                switch (tipoAtivo) {
                    case "Acao":
                        temp = Acao.fromString(linhaAtual);

                        break;

                    case "Fundo Imobiliario":
                        temp = FundoImobiliario.fromString(linhaAtual);

                        break;

                    case "NFT":
                        temp = Nft.fromString(linhaAtual);
                        break;

                    case "Cripto":
                        temp = Criptomoeda.fromString(linhaAtual);
                        break;

                    case "Renda Fixa":
                        temp = RendaFixa.fromString(linhaAtual);
                        break;

                    default:
                        throw new Exception("Tipo de ativo não reconhecido: " + tipoAtivo);
                }

                ativosConta.add(temp);

            }
        }
    }

    public static void salvarAtivos() throws IOException {
        try (FileWriter fileWriter = new FileWriter(ATIVOS_USUARIOS);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Ativo temp: ativosConta) {
                bufferedWriter.write(temp.toString() + "\n");
            }
        }
    }
}
