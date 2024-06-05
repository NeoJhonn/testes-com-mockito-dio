package br.com.jhonny_azevedo_mockito;

import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

// classe utilitária
public final class GeradorDeNumeros {

    private static SplittableRandom random = new SplittableRandom();

    private GeradorDeNumeros() {
    }

    public static List<Integer> gerarNumerosAleatorios(int tamanhoLista) {
        // cria uma lista com números aleatória passando o tamanho da lista
        return random.ints().boxed().limit(tamanhoLista).collect(Collectors.toList());
    }

    public static List<Integer> gerarNumerosAleatorios() {
        // cria uma lista com números aleatória passando o tamanho da lista
        return random.ints().boxed().limit(random.nextInt(10)).collect(Collectors.toList());
    }
}
