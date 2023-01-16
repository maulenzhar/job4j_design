package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class EchoServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    Pattern patternExit = Pattern.compile("^.*msg=Exit.*$");
                    Pattern patternHello = Pattern.compile("^.*msg=Hello.*$");
                    Pattern patternAny = Pattern.compile("^.*msg=.*$");

                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
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
        } catch (Exception e) {
            LOGGER.error("Exception in EchoServer:", e);
        }
    }
}
