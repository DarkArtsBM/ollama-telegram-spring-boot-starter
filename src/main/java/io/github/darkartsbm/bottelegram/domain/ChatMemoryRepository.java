package io.github.darkartsbm.bottelegram.domain;

import java.util.List;

/**
 * Contrato principal para o armazenamento do histórico de conversas do bot.
 * <p>
 * O desenvolvedor deve implementar esta interface caso deseje persistir
 * o contexto das mensagens em um banco de dados real (SQL, NoSQL, Redis, etc.).
 * A implementação padrão em memória é volátil e recomendada apenas para testes.
 * </p>
 *
 * @author Robson Cunha Filho(DarkArtsBM)
 * @since 1.0.0
 */
public interface ChatMemoryRepository {

    /**
     * Recupera o histórico cronológico de um chat específico.
     * Este histórico será enviado como contexto para o modelo de IA.
     *
     * @param chatId O identificador único do chat ou usuário.
     * @return Uma lista contendo as mensagens anteriores, ou uma lista vazia se o chat for novo.
     */
    List<ChatMessage> findByChatId(String chatId);

    /**
     * Salva uma nova mensagem (do usuário ou da IA) no histórico do chat.
     *
     * @param chatId  O identificador único do chat ou usuário.
     * @param message O objeto contendo o papel (role) e o texto da mensagem.
     */
    void save(String chatId, ChatMessage message);

    /**
     * Limpa todo o histórico de um chat específico.
     * Útil para comandos como "/reset" ou "/limpar".
     *
     * @param chatId O identificador único do chat a ser limpo.
     */
    void clear(String chatId);
}