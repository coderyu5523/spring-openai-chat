package org.example.chatai.chat;

import lombok.Data;

public class ChatResponse {

    @Data
    public static class ChatMessageDTO {
        private String content;
        private String sender;

        public ChatMessageDTO(String aiResponse) {
            this.content = aiResponse;
            this.sender = "AI";
        }
    }
}
