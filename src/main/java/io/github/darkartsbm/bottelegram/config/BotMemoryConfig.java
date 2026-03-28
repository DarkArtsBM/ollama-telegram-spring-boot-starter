package io.github.darkartsbm.bottelegram.config;

import io.github.darkartsbm.bottelegram.domain.ChatMemoryRepository;
import io.github.darkartsbm.bottelegram.infraestructure.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BotMemoryConfig {

    @Value("${meubot.memory.max-messages:10}")
    private int maxMessages;

    @Bean
    @ConditionalOnMissingBean(ChatMemoryRepository.class)
    public ChatMemoryRepository chatMemoryRepository() {
        // Passa o limite para o repositório em memória
        return new InMemoryChatMemory(maxMessages);
    }
}