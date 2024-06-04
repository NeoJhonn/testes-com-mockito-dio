package br.com.jhonny_azevedo_mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EnviarMensagemTeste {

    // Ao invez de @Mock, usa @Spy
    @Spy
    EnviarMensagem enviarMensagem;

    @Test
    void verificarComportamentoDaClasse() {
        // verificar se teve alguma interação
        Mockito.verifyNoInteractions(enviarMensagem);

        // fazer uma interação
        Mensagem mensagem = new Mensagem("Hello World");
        enviarMensagem.adicionarMensagem(mensagem);

        // verifica se o enviarMensagem adicionarMensagem(mensagem)
        Mockito.verify(enviarMensagem).adicionarMensagem(mensagem);

        // verifica se a mensagem foi inserida na lista
        assertFalse(enviarMensagem.getMensagens().isEmpty());
    }
}
