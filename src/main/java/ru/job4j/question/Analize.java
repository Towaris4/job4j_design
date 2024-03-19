package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int added = 0;
        int changed = 0;
        HashMap<Integer, String> prevMap = new HashMap<>();
        HashMap<Integer, String> curMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            curMap.put(user.getId(), user.getName());
        }
        for (Integer id : prevMap.keySet()) {
            if (!curMap.containsKey(id)) {
                deleted++;
            }
            if (curMap.containsKey(id) && !prevMap.get(id).equals(curMap.get(id))) {
                changed++;
            }
        }
        for (Integer id : curMap.keySet()) {
            if (!prevMap.containsKey(id)) {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}