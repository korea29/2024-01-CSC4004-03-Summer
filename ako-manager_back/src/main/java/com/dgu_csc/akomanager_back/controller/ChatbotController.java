package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.dto.ChatRequest;
import com.dgu_csc.akomanager_back.dto.ChatRespone;
import com.dgu_csc.akomanager_back.jwt.JWTUtil;
import com.dgu_csc.akomanager_back.model.ChatBot;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.ChatBotService;
import com.dgu_csc.akomanager_back.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Time;
import java.time.Instant;
//import java.time.*;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/chat")
public class ChatbotController {

    private final String DefaultPath = System.getProperty("user.dir") + "/ako-manager_back/src/main/";
//    private final String ChatbotLogFormat = LocalDate.now() + " " + LocalTime.now() + "\t";
//    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ChatBotService chatBotService;

    @Autowired
    private UserService userService;

    // POST : (/chat/ask) (POSTMAN FORMAT : Username, UserInput)
    @PostMapping("/ask")
    public String askChatbot(@RequestBody ChatRequest chatRequest) throws Exception {
        String username = chatRequest.getUsername();
        String userInput = chatRequest.getUserInput();
        String response;

        try {
            String pythonScriptPath = DefaultPath + "Python/ChatbotCode.py";
            String[] command = {"python3", pythonScriptPath};

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));

            writer.write(username + "\n");
            writer.write(userInput + "\n");
            writer.flush();

            StringBuilder responseBuilder = new StringBuilder();

            if (userInput.equals("대화종료")) {
                response = "또 만날 기회를 기다리고 있을게요! ❤️";
            } else {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line).append("\n");

                }
                response = responseBuilder.toString().trim();
                System.out.println(response);
            }
            System.out.println(response);
            return response;
        } catch (IOException e) {
            response = "Error processing chat: " + e.getMessage();
        }

        return response;
    }

    @PostMapping("/ask2")
    public ResponseEntity<ChatRespone> ask2Bot(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        String username = jwtUtil.getUsername(jwtUtil.getToken(request));
        String userInput = chatRequest.getUserInput();
        String response;

        Instant time = Instant.now();

        ChatBot chatBot = new ChatBot();
        chatBot.setCstudentId(userService.findByStudentId(username).get());
        chatBot.setUserinput(userInput);
        chatBot.setTimelog(time);

        ChatRespone chatRespone = new ChatRespone();
        chatRespone.setStudentId(username);
        chatRespone.setUserinput(userInput);
        chatRespone.setTimelog(time);

        try {
            String pythonScriptPath = DefaultPath + "Python/ChatbotCode.py";
            String[] command = {"python3", pythonScriptPath};

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));

            writer.write(username + "\n");
            writer.write(userInput + "\n");
            writer.flush();

            StringBuilder responseBuilder = new StringBuilder();

            if (userInput.equals("대화종료")) {
                response = "또 만날 기회를 기다리고 있을게요! ❤️";
            } else {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line).append("\n");
                }
                response = responseBuilder.toString().trim();
            }

            chatBot.setBotinput(response);
            chatRespone.setBotoutput(response);
            chatBotService.saveLog(chatBot);

            return ResponseEntity.ok(chatRespone);
        } catch (IOException e) {
            response = "Error processing chat: " + e.getMessage();
            chatBot.setBotinput(response);
            chatRespone.setBotoutput(response);
            chatBotService.saveLog(chatBot);
            return ResponseEntity.status(500).body(chatRespone);
        }
    }
}







////     로그에 3개의 대화 내용만 보이게 하는 기능
//    private void updateLogs() {
//        try {
//            for (int i = 2; i < 4; i++) {
//                File upper = new File(DefaultPath + "resources/MyLog" + i + ".txt");
//                File lower = new File(DefaultPath + "resources/MyLog" + (i - 1) + ".txt");
//                if (upper.exists()) {
//                    boolean success = upper.renameTo(lower);
//                    if (!success) {System.out.println("Cannot move data to previous log"); }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("error message : ", e);
//        }
//    }
//}


