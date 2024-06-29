package org.example.chatai.chat;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;



    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatRequest.ChatMessageDTO chatMessage) {
        chatService.processMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatRequest.ChatMessageDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    }
}