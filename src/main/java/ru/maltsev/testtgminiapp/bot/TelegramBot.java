package ru.maltsev.testtgminiapp.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.miniapp.url}")
    private String miniappUrl;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendStartMessage(chatId);
            }
        }
    }

    private void sendStartMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Привет! Нажмите кнопку ниже, чтобы открыть Mini App:");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Открыть Mini App");
        button.setWebApp(new WebAppInfo(miniappUrl));

        markup.setKeyboard(List.of(List.of(button)));
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
} 