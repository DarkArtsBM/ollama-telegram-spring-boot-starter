[ Português 🇧🇷 ](README.pt.md) | [ English 🇺🇸 ](README.md)

---

# Ollama Telegram Spring Boot Starter 🤖

---
A Spring Boot Starter library to create intelligent Telegram bots with context memory, using local Ollama AI in a simple and fast way.

## 📦 Installation
Add the dependency to your pom.xml:
```xml
<dependency>
<groupId>io.github.darkartsbm</groupId>
<artifactId>ollama-telegram-spring-boot-starter</artifactId>
<version>1.0.2</version>
</dependency>
```
## ⚠️ Lifecycle Note

```xml
Since this Starter uses Long Polling, Spring Boot might shut down the application immediately after startup if it doesn't detect an active web server. To keep the bot running, you have two options:

Web Mode (Recommended for Beginners): Add the following dependency. This will start an embedded Tomcat server to keep the application alive.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

Light Mode (Recommended for Performance): Do not add the web starter. Instead, set spring.main.keep-alive=true in your application.properties. This keeps the bot active while consuming much less RAM.

```
## 📖 How to Use
Configure your Telegram credentials and Ollama URL in your application.properties file.

How to get your Telegram credentials:
Talk to @BotFather and type /start.

In the options menu, type /newbot.

Enter the name you want for your bot (this will be your USERNAME).

Once the Username is set, it will send you an API key in this format: 123146612:HUHSAv...

Your TOKEN is exactly that generated key.

Configuration
Add the settings below to your project:

```xml
application.properties

# --- Telegram Settings ---
meubot.telegram.token=YOUR_TOKEN_HERE
meubot.telegram.username=YOUR_BOT_USERNAME

# --- AI Settings (Ollama) ---
meubot.ollama.url=http://localhost:11434/api/chat
meubot.ollama.model=llama3.2:1b

Note: I chose llama3.2:1b as the default because it is lightweight and functional, but feel free to use any model you have downloaded by simply changing the parameter in your config file.

Done! The library automatically configures the Telegram connection, manages per-user conversation memory, and responds using the chosen AI model. Just run your Spring Boot application!

Persistent Memory (Optional):
The bot is pre-configured to accept an interface that supports your database, should you wish to implement long-term persistent memory instead of the default RAM storage.

```
Requirements
Java 17 or higher

Spring Boot 3.x

Ollama running locally or via Docker

## 🖥️ Architecture
The library uses Hexagonal Architecture, decoupling AI logic and Memory Management from external platforms (Telegram/Ollama). This allows you to replace RAM storage with a Database by simply implementing an interface, without touching the Starter's core code.

## 📚 Data Flow

![FluxoDeDados.png](images/FluxoDeDados.png)

## 📝 License
Distributed under The Apache License, Version 2.0.




