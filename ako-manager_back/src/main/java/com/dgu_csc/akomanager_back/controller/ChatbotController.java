package com.dgu_csc.akomanager_back.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.*;


@RestController
@RequestMapping("/chat")
public class ChatbotController {

    private final String DefaultPath = System.getProperty("user.dir") + "/ako-manager_back/src/main/";
    private final String ChatbotLogFormat = LocalDate.now() + " " + LocalTime.now() + "\t";
    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    public static void main(String[] args) {
        SpringApplication.run(ChatbotController.class, args);
    }

    @PostMapping("/ask")
    public String askChatbot(@RequestParam String username, @RequestParam String userInput) {
        String response;
        try {
            updateLogs();
            String pythonScriptPath = DefaultPath + "Python/ChatbotCode.py";
            String[] command = {"python3", pythonScriptPath};

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));

            File chatLog = new File(DefaultPath + "resources/MyLog3.txt");
            FileWriter chatLogWriter = new FileWriter(chatLog, true);  // 챗봇 로그 기록을 위하여 추가한 FileWriter

            chatLogWriter.write(ChatbotLogFormat + username + " : " + userInput + "\n");
            chatLogWriter.flush();
            writer.println(username);
            writer.println(userInput);
            writer.flush();

            StringBuilder responseBuilder = new StringBuilder();

            if (userInput.equals("대화종료")) {
                response = "또 만날 기회를 기다리고 있을게요! ❤️";
                chatLogWriter.write(ChatbotLogFormat + "아코 멘토 : " + response + "\n");
            } else {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line).append("\n");
                }
                response = responseBuilder.toString().trim();
                chatLogWriter.write(ChatbotLogFormat + "아코 멘토 : " + response + "\n");
            }

            chatLogWriter.close();
        } catch (IOException e) {
            response = "Error processing chat: " + e.getMessage();
            e.printStackTrace();
        }

        return response;
    }

    private void updateLogs() {
        try {
            for (int i = 2; i < 4; i++) {
                File upper = new File(DefaultPath + "resources/MyLog" + i + ".txt");
                File lower = new File(DefaultPath + "resources/MyLog" + (i - 1) + ".txt");
                if (upper.exists()) {
                    upper.renameTo(lower);
                }
            }
        } catch (Exception e) {
            logger.error("error message : ", e);
        }
    }
}