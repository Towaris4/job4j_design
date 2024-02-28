package ru.job4j.generics;

public class Role extends Base {

    private final String model;

    public Role(String id, String name) {
        super(id);
        this.model = name;
    }

    public String getModel() {
        return model;
    }
}