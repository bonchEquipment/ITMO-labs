package ru.ArseniiDeviatkin.lab1.task3;

public class CrewMember {
    private String name;

    public CrewMember(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("CrewMember name cannot be null or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}