package ru.ArseniiDeviatkin.lab1.task3;

public class Engine {
    private String type;

    public Engine(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Engine type cannot be null or empty");
        }
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
