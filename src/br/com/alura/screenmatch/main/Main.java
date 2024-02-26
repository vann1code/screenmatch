package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.calculos.CalculadoraDeTempo;
import br.com.alura.screenmatch.calculos.Classificavel;
import br.com.alura.screenmatch.calculos.FiltroRecomendacao;
import br.com.alura.screenmatch.modelos.Episodio;
import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("O poderoso chefão", 1970);
        meuFilme.setDuracaoEmMinutos(180);

        System.out.println("One");
        System.out.println("Duração do filme: " + meuFilme.getDuracaoEmMinutos());

        System.out.println("Two");
        meuFilme.exibeFichaTecnica();

        meuFilme.avalia(10);
        meuFilme.avalia(7);
        meuFilme.avalia(10);

        System.out.println("Three");
        System.out.println("Total de avaliações: " + meuFilme.getTotalDeAvaliacoes());

        System.out.println("Quatro");
        System.out.println(meuFilme.pegaMedia());


//        meuFilme.setSomaDasAvaliacoes(10);
//        meuFilme.setTotalDeAvaliacoes(1);                     Consigo usar este trecho de código apenas implementando os getters e setters necessário. Caso contrário, não é possível alterar o valor da variavel pela linha de comando.
//        System.out.println(meuFilme.pegaMedia());

        Serie lost = new Serie("Lost", 2000);
        lost.exibeFichaTecnica();
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpisodio(50);
        System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());

        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.setDuracaoEmMinutos(200);

        CalculadoraDeTempo calculadoraDeTempo = new CalculadoraDeTempo();

        calculadoraDeTempo.inclui(meuFilme);
        calculadoraDeTempo.inclui(outroFilme);

        System.out.println(calculadoraDeTempo.getTempoTotal());

        System.out.println(meuFilme.getClassificacao());

        outroFilme.avalia(10);

        System.out.println(outroFilme.getClassificacao());

        Episodio episodio = new Episodio();
        episodio.setTotalVisualizacoes(101);
        System.out.println(episodio.getClassificacao());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(episodio);

        System.out.println(meuFilme); //Exemplo do método toString() refatorado.

        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(meuFilme);
        listaDeFilmes.add(outroFilme);
        System.out.println("Tamanho da lista " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.get(0).getNome());
        System.out.println(listaDeFilmes);
        System.out.println("toString do filme " + listaDeFilmes.get(0).toString());


    }
}