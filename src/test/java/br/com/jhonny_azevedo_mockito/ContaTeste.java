package br.com.jhonny_azevedo_mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContaTeste {

    @Spy
    private Conta conta = new Conta(1000);

    @Test
    void validarOrdemDeChamadas() {
        conta.pagaBoleto(300);

        // vefifica a ordem das chamadas da classe
        InOrder inOrder = Mockito.inOrder(conta);
        inOrder.verify(conta).pagaBoleto(ArgumentMatchers.anyDouble());// ao invez de passar um valor pode usar ArgumentMatchers
        inOrder.verify(conta).validaSaldo(300);
        inOrder.verify(conta).debita(300);
        inOrder.verify(conta).enviaCreditoParaEmisso(300);
    }

    @Test
    void validarQuantidadeDeChamadas() {
        conta.validaSaldo(300);
        conta.validaSaldo(500);
        conta.validaSaldo(600);

        // Mockito verifica se em conta o validaSaldo foi chamado 3 vezes.
        Mockito.verify(conta, Mockito.times(3)).validaSaldo(ArgumentMatchers.anyDouble());
    }
}
