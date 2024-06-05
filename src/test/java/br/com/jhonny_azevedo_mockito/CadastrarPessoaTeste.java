package br.com.jhonny_azevedo_mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {

    // Mockar a classe ApiDosCorreios
    @Mock
    private ApiDosCorreios apiDosCorreios;

    // Injeta objeto CadastrarPessoa na classe ApiDosCorreios
    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro() {
        DadosLocalizacao endereco = new DadosLocalizacao("SC", "Blumenau", "Rua Amazonas", "Sala1", "Garcia");
        // Quando chamar piDosCorreios.buscaDadosComBaseNoCep() retorne endereco
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(endereco);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Jhonny", "456456", LocalDate.now(), "1231232");

        assertEquals("Jhonny", pessoa.getNome());
        assertEquals("456456", pessoa.getDocumento());
        assertEquals("SC", pessoa.getEndereco().getUf());
        assertEquals("Sala1", pessoa.getEndereco().getComplemento());

        DadosLocalizacao enderecoPessoa = pessoa.getEndereco();
        assertEquals(endereco.getBairro(), enderecoPessoa.getBairro());
        assertEquals(endereco.getCidade(), enderecoPessoa.getCidade());
        assertEquals(endereco.getUf(), enderecoPessoa.getUf());
    }

    @Test
    void lancarExceptionQuandoChamarApiDosCorreios() {
        //Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);
        Mockito.doThrow(IllegalArgumentException.class).when(apiDosCorreios).buscaDadosComBaseNoCep(ArgumentMatchers.anyString());

        assertThrows(IllegalArgumentException.class, () -> cadastrarPessoa.cadastrarPessoa("Jhonny", "456456", LocalDate.now(), "1231232"));

    }
}
