package io.github.darkartsbm.bottelegram.domain;

import java.util.List;

public interface ChatMemoryRepository {
    List<ChatMessage> findByChatId(String chatId);
    void save(String chatId, ChatMessage message);
    void clear(String chatId);
}
