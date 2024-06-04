package br.com.jhonny_azevedo_mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnviarMensagem {

    private List<Mensagem> mensagens;

    public EnviarMensagem() {
        this.mensagens = new ArrayList<>();
    }

    public void adicionarMensagem(Mensagem mensagem) {
        this.mensagens.add(mensagem);
    }

    public List<Mensagem> getMensagens() {
        return Collections.unmodifiableList(this.mensagens);
    }
}
