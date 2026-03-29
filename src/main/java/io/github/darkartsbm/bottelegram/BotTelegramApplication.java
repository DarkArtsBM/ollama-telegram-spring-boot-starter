package io.github.darkartsbm.bottelegram;

import io.github.darkartsbm.bottelegram.infraestructure.TelegramAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class BotTelegramApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotTelegramApplication.class, args);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramAdapter bot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        System.out.println("BOT REGISTRADO COM SUCESSO!");
        return api;
    }
}
