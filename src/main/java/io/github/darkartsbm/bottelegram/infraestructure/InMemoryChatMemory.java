package io.github.darkartsbm.bottelegram.infraestructure;

import io.github.darkartsbm.bottelegram.domain.ChatMemoryRepository;
import io.github.darkartsbm.bottelegram.domain.ChatMessage;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryChatMemory implements ChatMemoryRepository {

    private final Map<String, List<ChatMessage>> storage = new ConcurrentHashMap<>();
    private final int maxMessages;


    public InMemoryChatMemory(int maxMessages) {
        this.maxMessages = maxMessages;
    }

    @Override
    public List<ChatMessage> findByChatId(String chatId) {
        return new ArrayList<>(storage.getOrDefault(chatId, new ArrayList<>()));
    }

    @Override
    public void save(String chatId, ChatMessage message) {
        List<ChatMessage> history = storage.computeIfAbsent(chatId, k -> new ArrayList<>());

        history.add(message);

        while (history.size() > maxMessages) {
            history.remove(0);
        }
    }

    @Override
    public void clear(String chatId) {
        storage.remove(chatId);
    }
}