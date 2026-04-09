package io.github.darkartsbm.bottelegram.domain;

/**
 * Porta de saída responsável pelo envio de mensagens para a plataforma de chat.
 * <p>
 * Isola a regra de negócio do SDK específico da plataforma (Telegram, WhatsApp, Discord, etc.).
 * </p>
 */
public interface ProvedorMensageria {

    /**
     * Envia uma mensagem de texto simples para um destinatário específico.
     *
     * @param destinatarioId O ID do chat ou usuário na plataforma correspondente.
     * @param conteudo       O texto da mensagem a ser enviada.
     */
    void enviarMensagem(String destinatarioId, String conteudo);
}