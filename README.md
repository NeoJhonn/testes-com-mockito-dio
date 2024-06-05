# Desenvolvendo Testes Utilizando Mockito

* Adicionando o mockito no seu projeto:
    - Pesquise por "mockito" no site do Maven.

* Adicione as duas dependências "Mockito Core" e "Mocokit JUnite Jupite" ao seu projeto:
  
- Mockito Core:
```
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.12.0</version>
    <scope>test</scope>
</dependency>
```
- Mocokit JUnite Jupite:

```
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.12.0</version>
    <scope>test</scope>
</dependency>
```

* Para adicionar o Mockito na sua classe de teste:

```
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) <---- sempre começar assim
public class ListaTeste {
}
```
* Mocks não são Stubs: diponível em https://www.infoq.com/br/articles/mocks-arent-stubs/

## Mockando Objetos

```
    // Mockar a classe ApiDosCorreios
    @Mock
    private ApiDosCorreios apiDosCorreios;

    // Injeta objeto CadastrarPessoa na classe ApiDosCorreios
    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro() {
        DadosLocalizacao endereco = new DadosLocalizacao("SC", "Blumenau", "Rua Amazonas", "Sala1", "Garcia");
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("1231232")).thenReturn(endereco);
        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Jhonny", "456456", LocalDate.now(), "1231232");

        assertEquals("Jhonny", pessoa.getNome());
        assertEquals("456456", pessoa.getDocumento());
        assertEquals("SC", pessoa.getEndereco().getUf());
        assertEquals("Sala1", pessoa.getEndereco().getComplemento());
    }
```

## Espiando Objetos(Spy)

- Com o Spy você observa o comportamento de um objeto usando o mockito.

```
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
```

## Capturando Argumentos(Captor)

- Com o Captor você captura um argumento de um método de teste.

```
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
```

## Manipulando Retornos

- Stub: constrir um objeto ou mock que vai retornar exatamente o que você quer, simular um comportamento.
- Usando Matchers.

```
 @Test
    void retornarTrueParaQualquerValorNaValidacaoDeSaldo() {
        Mockito.doNothing().when(conta).validaSaldo(ArgumentMatchers.anyDouble()); <---------Matcher
        conta.validaSaldo(3_500);
    }


@Test
    void lancarExceptionQuandoChamarApiDosCorreios() {
        //Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);
        Mockito.doThrow(IllegalArgumentException.class).when(apiDosCorreios).buscaDadosComBaseNoCep(ArgumentMatchers.anyString()); <---------Matcher

        assertThrows(IllegalArgumentException.class, () -> cadastrarPessoa.cadastrarPessoa("Jhonny", "456456", LocalDate.now(), "1231232"));

    }
```







