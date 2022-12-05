package ru.job4j.collection.question;


import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        int changed = 0;
        int deleted = 0;
        int added = 0;

        Map<Integer, String> userPrev = new TreeMap<>();
        Map<Integer, String> userCurr = new TreeMap<>();

        for (User user : previous) {
            userPrev.put(user.getId(), user.getName());
        }
        for (User user : current) {
            userCurr.put(user.getId(), user.getName());
        }

        for (int i = 1; i <= userPrev.size() + 1; i++) {
            String p = userPrev.get(i);
            String c = userCurr.get(i);
            if (p != null && c != null && !p.equals(c)) {
                info.setChanged(++changed);
                continue;
            } else if (p != null && c != null && p.equals(c)) {
                continue;
            } else if (p != null && c == null) {
                info.setDeleted(++deleted);
                continue;
            } else if (c != null && p == null) {
                info.setAdded(++added);
                continue;
            }
        }

        return info;
    }
}
