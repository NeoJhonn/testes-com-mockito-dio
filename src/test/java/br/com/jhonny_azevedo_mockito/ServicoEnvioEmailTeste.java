package br.com.jhonny_azevedo_mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTeste {

    @Mock
    private PlataformaDeEnvio plataforma;

    @InjectMocks
    private ServicoEnvioEmail servico;

    @Captor
    private ArgumentCaptor<Email> captor;

    @Test
    void validarDadosEnviadosParaPlataforma() {

        String enderecoEmail = "jonny.azevedo@teste.com.br";
        String mesagem = "Olá mundo teste mensagem";
        boolean ehFormatoHtml = false;

        servico.enviaEmail(enderecoEmail, mesagem, ehFormatoHtml);

        // Mockito verifica no mock(plataforma) o email que foi enviado
        Mockito.verify(plataforma).enviaEmail(captor.capture());

        // Se der tudo certo pegar dados do email
        Email emailCapturado = captor.getValue();

        // Verificar com o JUnit os dados do email
        Assertions.assertEquals(enderecoEmail, emailCapturado.getEnderecoEmail());
        Assertions.assertEquals(mesagem, emailCapturado.getMensagem());
        Assertions.assertEquals(Formato.TEXTO, emailCapturado.getFormato());
    }
}
