package io.github.darkartsbm.bottelegram.domain;


public interface ProvedorMensageria {
    void enviarMensagem(String destinatarioId, String conteudo);
}