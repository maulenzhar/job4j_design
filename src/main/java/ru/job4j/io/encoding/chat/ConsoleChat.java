package ru.job4j.io.encoding.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    private String userWord = "";
    private String state = "";
    private List<String> chat = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String state = "";
        String answerBot;
        List<String> phrases = readPhrases();
        int random = new Random().nextInt(phrases.size());

        while (!OUT.equals(state)) {
            String enterWord = "Введите слово-фразу: ";
            System.out.print(enterWord);
            userWord = input.nextLine();
            state = getState(userWord);

            if (STOP.equals(state)) {
                chat.add(enterWord + userWord);
            } else if (CONTINUE.equals(state) || "".equals(state)) {
                answerBot = "Бот: " + phrases.get(random);
                System.out.print(answerBot);
                chat.add(answerBot);
            }
        }

        answerBot = "Бот: Пока";
        System.out.print(answerBot);
        chat.add(answerBot);
        saveLog(chat);
    }

    private String getState(String usersWord) {
        if (STOP.equals(usersWord)) {
            state = STOP;
        } else if (CONTINUE.equals(usersWord)) {
            state = CONTINUE;
        } else if (OUT.equals(usersWord)) {
            state = OUT;
        }
        return state;
    }

    private List<String> readPhrases() {
        List<String> res = new ArrayList<>();
        System.out.println("Производится чтение файла");
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(res::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.txt", "./data/answer.txt");
        cc.run();
    }
}
