package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
    @XmlAttribute
    String processor;
    String videoCard;


    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public Computer() {
    }

    public Computer(String processor, String videoCard) {
        this.processor = processor;
        this.videoCard = videoCard;
    }

    @Override
    public String toString() {
        return "Computer{"
                + "processor=" + processor
                + ", videoCard=" + videoCard
                + "}";
    }
}