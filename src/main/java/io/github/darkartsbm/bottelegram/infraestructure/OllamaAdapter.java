package io.github.darkartsbm.bottelegram.infraestructure;

import io.github.darkartsbm.bottelegram.domain.ChatMemoryRepository;
import io.github.darkartsbm.bottelegram.domain.ChatMessage;
import io.github.darkartsbm.bottelegram.domain.GeradorIA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OllamaAdapter implements GeradorIA {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ChatMemoryRepository repository;

    @Value("${meubot.ollama.model:llama3.2:1b}")
    private String modelName;

    @Value("${meubot.ollama.url:http://localhost:11434/api/chat}")
    private String urlOllama;

    @Value("${meubot.ollama.max-messages:10}")
    private int maxMessages;

    public OllamaAdapter(ChatMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public String perguntar(String chatId, String textoDoUsuario) {
        List<ChatMessage> historico = new ArrayList<>(repository.findByChatId(chatId));

        ChatMessage userMsg = new ChatMessage("user", textoDoUsuario);
        historico.add(userMsg);
        repository.save(chatId, userMsg);

        if (historico.size() > maxMessages) {
            historico = historico.subList(historico.size() - maxMessages, historico.size());
        }

        Map<String, Object> request = Map.of(
                "model", modelName,
                "messages", historico,
                "stream", false
        );

        try {
            Map<String, Object> response = restTemplate.postForObject(urlOllama, request, Map.class);

            Map<String, Object> messageResp = (Map<String, Object>) response.get("message");
            String aiContent = (String) messageResp.get("content");

            ChatMessage aiMsg = new ChatMessage("assistant", aiContent);
            repository.save(chatId, aiMsg);

            return aiContent;

        } catch (Exception e) {
            return "Erro ao processar IA: " + e.getMessage();
        }
    }

}