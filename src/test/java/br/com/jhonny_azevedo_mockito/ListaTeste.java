package br.com.jhonny_azevedo_mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListaTeste {

    // mokando a lista
    @Mock
    private List<String> letras;

    @Test
    void adicionarItemNaLista() {
        // Mockito quando eu chamar letras.get(0) mocka e retorna "A"
        Mockito.when(letras.get(0)).thenReturn("A");

        Assertions.assertEquals("A", letras.get(0));
    }
}
