package com.dgu_csc.akomanager_back;

import java.io.*;
import java.util.Scanner;

public class OpenChatbot {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            String pythonScriptPath = System.getProperty("user.dir") + "/ako-manager_back/src/main/Python/ChatbotCode.py";
            System.out.println(pythonScriptPath);

            String [] command = {"python3", pythonScriptPath};

            System.out.println("Start chat");
            ProcessBuilder pb = new ProcessBuilder(command);

            while (true) {
                Process process = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));

                Scanner scanner = new Scanner(System.in);
                String UserInput ;

                System.out.print("질문할 내용을 입력 : ");
                UserInput = scanner.nextLine();

                writer.println(UserInput);
                writer.flush();

                if (UserInput.equals("대화종료")) {break;}

                String line ;
                System.out.println("아코 멘토 : " );
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            }


        } catch (IOException e) {e.printStackTrace();}
    }
}