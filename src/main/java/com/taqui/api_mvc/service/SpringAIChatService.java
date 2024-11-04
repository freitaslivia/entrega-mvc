package com.taqui.api_mvc.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;


@Service
public class SpringAIChatService {
    private final ChatClient.Builder chatClientBuilder;

    public SpringAIChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClientBuilder = chatClientBuilder;
    }

    public String run(String userPrompt) {
        try {
            var chatClient = chatClientBuilder.build();


            return chatClient
                    .prompt()
                    .user(userPrompt)
                    .call()
                    .content()
                    .replace("\n", "");
        } catch (Exception e) {

            System.out.println("Erro ao chamar o serviço de IA: " + e.getMessage());
            throw new RuntimeException("Erro inesperado ao gerar resposta de IA.");
        }
    }
}