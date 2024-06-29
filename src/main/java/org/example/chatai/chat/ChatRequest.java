package org.example.chatai.chat;

import lombok.Data;

public class ChatRequest {

    @Data
    public static class ChatMessageDTO {
        private String content;
        private String sender;

    }

}
