package ru.job4j.collection.question;


import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);

        Map<Integer, String> userPrev = new TreeMap<>();

        for (User user : previous) {
            userPrev.put(user.getId(), user.getName());
        }

        for (User userCurr : current) {
            if (!userPrev.containsKey(userCurr.getId())
                    && previous.size() == current.size()) {
                info.setDeleted(+1);
                info.setAdded(+1);
            } else if (userPrev.containsKey(userCurr.getId())
                    && previous.size() == current.size()
                    && !userPrev.containsValue(userCurr.getName())) {
                info.setChanged(+1);
            } else if (previous.size() < current.size()) {
                info.setAdded(+1);
            } else if (previous.size() > current.size()) {
                info.setDeleted(+1);
            }
        }

        return info;
    }
}
