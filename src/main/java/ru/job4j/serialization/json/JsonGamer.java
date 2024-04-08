package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.xml.Gamer;

import java.util.ArrayList;
import java.util.List;

public class JsonGamer {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonComputer = new JSONObject("{\"processor\":\"Ryzen 7\", \"videoCard\":\"AMD RX9\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("dota 2");
        list.add("CS 1.6");
        list.add("Minecraft");
        JSONArray jsonGames = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Gamer gamer = new Gamer(true, 243322, "Dota 2",
                "Ryzen 7", "Geforce 4080ti",
                new String[]{"dota 2", "CS 1.6", "Minecraft"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("online", gamer.isOnline());
        jsonObject.put("numberOfHours", gamer.getNumberOfHours());
        jsonObject.put("computer", jsonComputer);
        jsonObject.put("favoriteGame", gamer.getFavoriteGame());
        jsonObject.put("games", jsonGames);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект gamer в json-строку */
        System.out.println(new JSONObject(gamer).toString());
    }
}
