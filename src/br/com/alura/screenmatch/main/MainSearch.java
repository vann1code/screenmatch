package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainSearch {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();
            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=274d07f7";

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            try {

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                TituloOmdb novoTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(novoTituloOmdb);

                Titulo novoTitulo = new Titulo(novoTituloOmdb);
                System.out.println("Título Convertido");
                System.out.println(novoTitulo);

                titulos.add(novoTitulo);

            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArgumentException: " + e.getMessage());
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println("ErroDeConversaoDeAno: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("NullPointerException: " + e.getMessage());
            }
        }
        System.out.println(titulos);

        FileWriter fileWriter = new FileWriter("filmes.json");
        fileWriter.write(gson.toJson(titulos));
        fileWriter.close();

        System.out.println("Fim da aplicação.");
        for (int i = 1; i <= 80; i++) {
            System.out.print("-");
        }

    }

}