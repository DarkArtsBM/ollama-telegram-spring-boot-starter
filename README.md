# Ollama Telegram Spring Boot Starter 🤖

Uma biblioteca Spring Boot (Starter) para criar bots de Telegram inteligentes e com memória de contexto, usando a IA local do Ollama de forma simples e rápida.

## 📦 Instalação

Adicione a dependência no seu `pom.xml`:

```xml
<dependency>
    <groupId>io.github.darkartsbm</groupId>
    <artifactId>ollama-telegram-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>

Como Usar
1. Configure suas credenciais e a URL do Ollama no seu arquivo application.properties:

Como pegar suas credenciais do Telegram:

Fale com o @BotFather e digite /start.

Na tela de opções, digite /newbot.

Digite o nome que você quer dar ao seu bot (esse nome será o seu USERNAME).

Logo após você decidir seu Username, ele lhe mandará uma chave de API neste formato: 123146612:HUHSAv...

O seu TOKEN é exatamente essa chave gerada.

Adicione as configurações abaixo no seu projeto:

Properties
# --- Configurações do Telegram ---
meubot.telegram.token=SEU_TOKEN_AQUI
meubot.telegram.username=NOME_DO_SEU_BOT

# --- Configurações da IA (Ollama) ---
meubot.ollama.url=http://localhost:11434/api/chat
meubot.ollama.model=llama3.2:1b

> **Obs:** Preferi deixar o `llama3.2:1b` como padrão por ser leve e funcional, mas sinta-se livre para usar qualquer modelo que você baixar, basta alterar o parâmetro no seu arquivo de configurações.

2. Pronto! A biblioteca já configura automaticamente a conexão com o Telegram, gerencia a memória das conversas por usuário e responde utilizando o modelo de IA escolhido. Basta rodar sua aplicação Spring Boot!

3. Memória Persistente (Opcional):
O bot já está configurado para aceitar uma interface que comporte o seu banco de dados, caso você queira implementar uma memória persistente a longo prazo em vez da memória em RAM padrão.

Requisitos
Java 17 ou superior

Spring Boot 3.x

Ollama rodando localmente ou via Docker


---