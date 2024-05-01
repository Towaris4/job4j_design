package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Gamer {
    boolean online;
    int numberOfHours;
    String favoriteGame;
    Computer computer;
    String[] games = new String[10];
    private class Computer {
        String processor;
        String videoCard;

        public Computer(String processor, String videoCard) {
            this.processor = processor;
            this.videoCard = videoCard;
        }
    };
    public Gamer(boolean online, int numberOfHours,
                 String favoriteGame, String processor, String videoCard,
                 String[] games) {
        this.online = online;
        this.numberOfHours = numberOfHours;
        this.favoriteGame = favoriteGame;
        this.computer = new Computer(processor, videoCard);
        this.games = games;
    }

    public static void main(String[] args) {
       final Gamer gamer = new Gamer(true, 243322, "Dota 2",
                "Ryzen 7", "Geforce 4080ti",
                new String[]{"dota 2", "CS 1.6", "Minecraft"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(gamer));
        final String gamerJson =
                "{"
                        + "\"online\":false,"
                        + "\"numberOfHours\":38,"
                        + "\"favoriteGame\":\"CS 1.6\","
                        + "\"computer\":"
                        + "{"
                        + "\"processor\":\"Intel Core i99\","
                        + "\"videoCard\":\"Geforce 4080ti\""
                        + "},"
                        + "\"games\":"
                        + "[\"dota 2\",\"CS 1.6\",\"Minecraft\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Gamer gamerMod = gson.fromJson(gamerJson, Gamer.class);
        System.out.println(gson.toJson(gamer));
    }
}
