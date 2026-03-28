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

    // Configurações dinâmicas (Maven Central Ready)
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
        // 1. Recupera o histórico da persistência (RAM ou Banco)
        List<ChatMessage> historico = new ArrayList<>(repository.findByChatId(chatId));

        // 2. Adiciona a nova mensagem do usuário e salva
        ChatMessage userMsg = new ChatMessage("user", textoDoUsuario);
        historico.add(userMsg);
        repository.save(chatId, userMsg);

        // 3. Limita o tamanho do histórico para não estourar o contexto
        if (historico.size() > maxMessages) {
            historico = historico.subList(historico.size() - maxMessages, historico.size());
        }

        // 4. Monta a requisição para o endpoint /api/chat
        Map<String, Object> request = Map.of(
                "model", modelName,
                "messages", historico,
                "stream", false
        );

        try {
            // 5. Chamada ao Ollama
            Map<String, Object> response = restTemplate.postForObject(urlOllama, request, Map.class);

            // 6. Extrai a resposta da IA (formato do endpoint /chat)
            Map<String, Object> messageResp = (Map<String, Object>) response.get("message");
            String aiContent = (String) messageResp.get("content");

            // 7. Salva a resposta da IA no histórico para a próxima volta
            ChatMessage aiMsg = new ChatMessage("assistant", aiContent);
            repository.save(chatId, aiMsg);

            return aiContent;

        } catch (Exception e) {
            return "Erro ao processar IA: " + e.getMessage();
        }
    }

    // Método antigo para manter compatibilidade, se necessário

    public String perguntar(String textoDoUsuario) {
        return perguntar("default-user", textoDoUsuario);
    }
}