package br.com.jhonny_azevedo_mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GeradorDeNumerosTeste {

    @Test
    void testaGeracaoComTamanhoDefinido() {
        List<Integer> numerosAleatorios = GeradorDeNumeros.gerarNumerosAleatorios(10);

        MockedStatic<GeradorDeNumeros> mockedStatic = Mockito.mockStatic(GeradorDeNumeros.class);
        // Usar uma lambda no wheh()
        mockedStatic.when(() -> GeradorDeNumeros.gerarNumerosAleatorios(10)).thenReturn(numerosAleatorios);

        Assertions.assertEquals(GeradorDeNumeros.gerarNumerosAleatorios(10), numerosAleatorios);

        // Usar uma lambda no verify()
        mockedStatic.verify(() -> GeradorDeNumeros.gerarNumerosAleatorios(10));
    }
}
