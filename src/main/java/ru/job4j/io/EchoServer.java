package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String head = input.readLine();
                    System.out.println(head);
                    Matcher matcher = Pattern.compile("msg=.+ ").matcher(head);
                    if (matcher.find()) {
                        switch (matcher.group()) {
                            case "msg=Hello ":
                                System.out.println("Hello");
                                break;
                            case "msg=Any ":
                                System.out.println("What");
                                break;
                            case "msg=Exit ":
                                System.out.println("Завершить работу сервера");
                                break;
                            default:
                                System.out.println("Команда неизвестна");
                                break;
                        }
                    }
                    output.flush();
                }
            }
        }
    }
}