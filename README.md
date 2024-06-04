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

