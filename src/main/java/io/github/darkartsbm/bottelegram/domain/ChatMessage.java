package io.github.darkartsbm.bottelegram.domain;

/**
 * Representa uma mensagem individual dentro do histórico de uma conversa.
 * Utilizado para manter a estrutura exigida pelas APIs de IA (ex: Ollama, OpenAI).
 *
 * @param role    O papel de quem enviou a mensagem. Geralmente "user" (para o usuário humano),
 * "assistant" (para a resposta da IA) ou "system" (para instruções globais).
 * @param content O conteúdo em texto da mensagem.
 */
public record ChatMessage(String role, String content) {}