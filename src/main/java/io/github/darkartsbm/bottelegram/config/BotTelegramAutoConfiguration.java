package io.github.darkartsbm.bottelegram.config;

import io.github.darkartsbm.bottelegram.application.ResponderComIAUseCase;
import io.github.darkartsbm.bottelegram.infraestructure.TelegramAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@AutoConfiguration
public class BotTelegramAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "meubot.telegram.token")
    public TelegramAdapter telegramAdapter(
            @Value("${meubot.telegram.token}") String token,
            @Value("${meubot.telegram.username}") String username,
            @Lazy ResponderComIAUseCase useCase) {
        return new TelegramAdapter(token, username, useCase);
    }
}