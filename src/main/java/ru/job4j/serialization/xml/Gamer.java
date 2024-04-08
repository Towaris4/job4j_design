package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "gamer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gamer {
    @XmlAttribute
    boolean online;
    int numberOfHours;
    String favoriteGame;
    Computer computer;
    String[] games = new String[10];

    public Gamer() {

    }

    public Gamer(boolean online, int numberOfHours,
                 String favoriteGame, String processor, String videoCard,
                 String[] games) {
        this.online = online;
        this.numberOfHours = numberOfHours;
        this.favoriteGame = favoriteGame;
        this.computer = new Computer(processor, videoCard);
        this.games = games;
    }

    @Override
    public String toString() {
        return "Gamer{"
                + "online=" + online
                + ", favoriteGame=" + favoriteGame
                + ", computer=" + computer
                + ", games=" + Arrays.toString(games)
                + "}";
    }

    public static void main(String[] args) throws Exception {
        Gamer gamer = new Gamer(true, 243322, "Dota 2",
                "Ryzen 7", "Geforce 4080ti",
                new String[]{"dota 2", "CS 1.6", "Minecraft"});
        JAXBContext context = JAXBContext.newInstance(Gamer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(gamer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Gamer result = (Gamer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getFavoriteGame() {
        return favoriteGame;
    }

    public void setFavoriteGame(String favoriteGame) {
        this.favoriteGame = favoriteGame;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public String[] getGames() {
        return games;
    }

    public void setGames(String[] games) {
        this.games = games;
    }
}
