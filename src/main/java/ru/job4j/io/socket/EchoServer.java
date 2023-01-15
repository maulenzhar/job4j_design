package ru.job4j.io.socket;

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
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        Pattern patternExit = Pattern.compile("^.*msg=Exit.*$");
                        Pattern patternHello = Pattern.compile("^.*msg=Hello.*$");
                        Pattern patternAny = Pattern.compile("^.*msg=.*$");
                        if (patternExit.matcher(str).find()) {
                            server.close();
                        } else if (patternHello.matcher(str).find()) {
                            out.write("Hello, dear friend.".getBytes());
                        } else if (patternAny.matcher(str).find()) {
                            out.write("What".getBytes());
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
