package ru.ArseniiDeviatkin.lab1.task3;

public class SpaceShip {
    private String name;

    public SpaceShip(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("SpaceShip name cannot be null or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
