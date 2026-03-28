package io.github.darkartsbm.bottelegram.infraestructure;

import io.github.darkartsbm.bottelegram.application.ResponderComIAUseCase;
import io.github.darkartsbm.bottelegram.domain.ProvedorMensageria;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramAdapter extends TelegramLongPollingBot implements ProvedorMensageria {

    private final String botToken;
    private final String botUsername;
    private final ResponderComIAUseCase responderComIAUseCase;

    // Construtor sem anotações de Spring (a Configuração cuidará disso)
    public TelegramAdapter(String botToken, String botUsername, ResponderComIAUseCase responderComIAUseCase) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.responderComIAUseCase = responderComIAUseCase;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String texto = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());
            responderComIAUseCase.executar(chatId, texto);
        }
    }

    @Override
    public String getBotUsername() { return botUsername; }

    @Override
    public String getBotToken() { return botToken; }

    @Override
    public void enviarMensagem(String chatId, String texto) {
        try {
            execute(new SendMessage(chatId, texto));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}