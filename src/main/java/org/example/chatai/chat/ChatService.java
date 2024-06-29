package org.example.chatai.chat;

import lombok.RequiredArgsConstructor;
import org.example.chatai.OpenAI.OpenAIService;
import org.example.chatai.user.User;
import org.example.chatai.user.UserRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final OpenAIService openAIService;
    private final UserRepository userRepository;

    public void processMessage(ChatRequest.ChatMessageDTO message) {
        String userMessage = message.getContent();

        // DB에서 모든 사용자 조회
        List<User> users = userRepository.findAll();
        String userInfo = users.stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));

        // OpenAI API 요청
        String prompt = userMessage + "\nCurrent users in DB: " + userInfo;
        String aiResponse = openAIService.askOpenAI(prompt);

        // 응답 메시지 생성 및 전송
        ChatRequest.ChatMessageDTO aiMessage = new ChatRequest.ChatMessageDTO();
        aiMessage.setContent(aiResponse);
        aiMessage.setSender("AI");

        messagingTemplate.convertAndSend("/topic/messages", aiMessage);
    }
}
