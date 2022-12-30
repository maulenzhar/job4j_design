package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");
        /*Pattern pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);  регистр букв учитываться не будет*/

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        /*проверить шаблон на его присутствие в тексте*/
        Pattern pattern2 = Pattern.compile("Job4j");
        String text = "Я учусь на курсе Job4j wqwq Job4j";
        Matcher matcher3 = pattern2.matcher(text);
        boolean isPresent = matcher3.find();
        System.out.println(isPresent);

        /*многократные вхождения шаблона в коде*/
        Pattern pattern3 = Pattern.compile("Job4j");
        String text3 = "Job4j и Job4j и Job4j";
        Matcher matcher4 = pattern3.matcher(text3);
        while (matcher4.find()) {
            System.out.println("Найдено совпадение");
        }

        /*Получить найденное совпадение в виде строки можно с помощью метода group(). Этот метод выводит ту часть текста,
        которая совпадает с шаблоном регулярного выражения. В данном случае это "Job4j".*/
        Pattern pattern4 = Pattern.compile("Job4j");
        String text4 = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher5 = pattern4.matcher(text4);
        while (matcher5.find()) {
            System.out.println("Найдено совпадение: " + matcher5.group());
        }

        /*Также существуют методы получения начального и конечного индексов найденного совпадения.
        Метод start() - получает индекс, в котором находится первый символ искомой последовательности символов.
        Метод end() - получает индекс, следующий за последним символов искомой последовательности символов.*/
        Pattern pattern5 = Pattern.compile("Job4j");
        String text5 = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher6 = pattern5.matcher(text5);
        while (matcher6.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher6.start()
                    + " iEnd: " + matcher6.end());
        }

        /*Найденные совпадения можно заменить другой строкой с помощью метода replaceAll(), который применяется к сопоставителю matcher.*/
        Pattern pattern6 = Pattern.compile("123");
        String text6 = "1231 и 1232 и 1233";
        Matcher matcher7 = pattern6.matcher(text6);
        String rsl = matcher7.replaceAll("Job4j");
        System.out.println(rsl);
    }
}