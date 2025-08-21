package org.example.animalcare.controller;

import org.example.animalcare.enums.ChatMessage;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatController {
    private final MessageSourceAutoConfiguration messageSourceAutoConfiguration;

    public ChatController(MessageSourceAutoConfiguration messageSourceAutoConfiguration) {
        this.messageSourceAutoConfiguration = messageSourceAutoConfiguration;
    }

    @MessageMapping("/sendMessage") // client sends messages to /app/sendMessage
    @SendTo("/topic/public")        // server broadcasts messages to /topic/public
    public ChatMessage sendMessage(ChatMessage message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        message.setTimestamp(time);
        return message;
    }
}
