package io.github.darkartsbm.bottelegram.domain;

/**
 * Porta de saída responsável pela comunicação com o provedor de Inteligência Artificial.
 * <p>
 * Qualquer integração com LLMs (Large Language Models) deve implementar este contrato.
 * O Starter fornece o OllamaAdapter nativamente.
 * </p>
 */
public interface GeradorIA {

    /**
     * Envia o texto do usuário para a IA, processa o histórico de contexto
     * e retorna a resposta final gerada pelo modelo.
     *
     * @param chatId         O identificador do chat (usado para buscar o contexto/histórico).
     * @param textoDoUsuario A mensagem atual digitada pelo usuário.
     * @return A resposta em formato de texto gerada pela Inteligência Artificial.
     */
    String perguntar(String chatId, String textoDoUsuario);
}