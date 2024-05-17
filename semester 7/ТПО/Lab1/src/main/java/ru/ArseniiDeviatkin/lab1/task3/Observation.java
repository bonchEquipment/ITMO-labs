package ru.ArseniiDeviatkin.lab1.task3;

public class Observation {
    private SpaceShip spaceShip;
    private Engine engine;
    private Crew crew;
    private Emotion emotion;
    private PhysicsPrinciple principle;

    public Observation(SpaceShip spaceShip, Engine engine, Crew crew, Emotion emotion, PhysicsPrinciple principle) {
        if (spaceShip == null || engine == null || crew == null || emotion == null || principle == null) {
            throw new IllegalArgumentException("None of the Observation parameters can be null");
        }
        this.spaceShip = spaceShip;
        this.engine = engine;
        this.crew = crew;
        this.emotion = emotion;
        this.principle = principle;
    }

    public String getFullStory() {
        StringBuilder story = new StringBuilder();
        story.append(spaceShip.getName())
                .append(" плыл через космическую ночь, теперь уже на обычном ")
                .append(engine.getType())
                .append(". Четыре человека, составлявшие его экипаж, чувствовали себя ")
                .append(emotion.getDescription())
                .append(", зная, что они вместе не по собственной воле и не по простому совпадению, а по странному физическому принципу -- ")
                .append(principle.getPrinciple())
                .append(".");
        return story.toString();
    }
}
