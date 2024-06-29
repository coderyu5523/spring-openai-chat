package org.example.chatai.chat;
import lombok.RequiredArgsConstructor;
import org.example.chatai.OpenAI.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final OpenAIService openAIService;


    public void processMessage(ChatRequest.ChatMessageDTO message) {
        String userMessage = message.getContent();
        String aiResponse = openAIService.askOpenAI(userMessage);

        ChatRequest.ChatMessageDTO aiMessage = new ChatRequest.ChatMessageDTO();
        aiMessage.setContent(aiResponse);
        aiMessage.setSender("AI");

        messagingTemplate.convertAndSend("/topic/messages", aiMessage);  // 일반 구독 경로로 변경
    }
}
