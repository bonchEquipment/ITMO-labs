package ru.ArseniiDeviatkin.lab1.task3;

public class Story {
    private Observation observation;

    public Story(Observation observation) {
        if (observation == null) {
            throw new IllegalArgumentException("Observation cannot be null");
        }
        this.observation = observation;
    }

    public String getFullStory() {
        return observation.getFullStory();
    }
}
