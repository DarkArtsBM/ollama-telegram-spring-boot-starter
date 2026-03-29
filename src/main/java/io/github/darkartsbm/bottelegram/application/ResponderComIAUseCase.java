package io.github.darkartsbm.bottelegram.application;

import io.github.darkartsbm.bottelegram.domain.GeradorIA;
import io.github.darkartsbm.bottelegram.domain.ProvedorMensageria;
import org.springframework.stereotype.Service;

@Service
public class ResponderComIAUseCase {

    private final GeradorIA ia;
    private final ProvedorMensageria mensageria;


    public ResponderComIAUseCase(GeradorIA ia, ProvedorMensageria mensageria) {
        this.ia = ia;
        this.mensageria = mensageria;
    }

    public void executar(String chatId, String mensagemDoUsuario) {
        mensageria.enviarMensagem(chatId, "Pensando... 🧠");


        String respostaInteligente = ia.perguntar(chatId, mensagemDoUsuario);

        mensageria.enviarMensagem(chatId, respostaInteligente);
    }
}
