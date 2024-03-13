package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        Map<User, Object> map = new HashMap<>(16);
        User userOne = new User("Ivan", 0, birthday);
        int hashcode1 = userOne.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 16);
        int bucked1 = hash1 & 15;
        User userTwo = new User("Ivan", 0, birthday);
        int hashcode2 = userTwo.hashCode();
        int hash2 = hashcode2 ^ (hashcode2 >>> 16);
        int bucked2 = hash2 & 15;
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        for (User user : map.keySet()) {
            System.out.println(map.get(user).toString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return (this.birthday == user.birthday)
                && (this.name == user.name)
                && (this.children == user.children);
    }

   @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}