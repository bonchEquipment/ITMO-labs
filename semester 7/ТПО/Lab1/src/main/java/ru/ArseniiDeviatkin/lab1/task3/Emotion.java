package ru.ArseniiDeviatkin.lab1.task3;

public class Emotion {
    private String description;

    public Emotion(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Emotion description cannot be null or empty");
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
