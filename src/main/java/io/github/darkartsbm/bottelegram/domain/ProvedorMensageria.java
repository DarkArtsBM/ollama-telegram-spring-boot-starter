package io.github.darkartsbm.bottelegram.domain;

// O domínio diz: "Eu preciso de algo que envie mensagens. Não me importa se é pombo correio ou Telegram."
public interface ProvedorMensageria {
    void enviarMensagem(String destinatarioId, String conteudo);
}