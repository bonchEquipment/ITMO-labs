package ru.ArseniiDeviatkin.lab1.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    @ParameterizedTest
    @CsvSource({
            "Золотое Сердце, фотонов ом двигателе, неуютно, как будто отношения между людьми подчиняются тем же законам, что отношения между атомами и молекулами",
            "Серебряная Комета, ионном двигателе, беспокойно, как будто они связаны невидимыми силами"
    })
    void testGetFullStory(String shipName, String engineType, String emotionDescription, String principleDescription) {
        SpaceShip spaceShip = new SpaceShip(shipName);
        Engine engine = new Engine(engineType);
        CrewMember[] crewMembers = {
                new CrewMember("Член 1"),
                new CrewMember("Член 2"),
                new CrewMember("Член 3"),
                new CrewMember("Член 4")
        };
        Crew crew = new Crew(crewMembers);
        Emotion emotion = new Emotion(emotionDescription);
        PhysicsPrinciple principle = new PhysicsPrinciple(principleDescription);

        Observation observation = new Observation(spaceShip, engine, crew, emotion, principle);
        Story story = new Story(observation);

        String expectedStory = shipName + " плыл через космическую ночь, теперь уже на обычном " + engineType + ". Четыре человека, составлявшие его экипаж, чувствовали себя " + emotionDescription + ", зная, что они вместе не по собственной воле и не по простому совпадению, а по странному физическому принципу -- " + principleDescription + ".";
        assertEquals(expectedStory, story.getFullStory());
    }

    @Test
    void testSpaceShipName() {
        SpaceShip spaceShip = new SpaceShip("Золотое Сердце");
        assertEquals("Золотое Сердце", spaceShip.getName());
    }

    @Test
    void testEngineType() {
        Engine engine = new Engine("фотоновом двигателе");
        assertEquals("фотоновом двигателе", engine.getType());
    }

    @Test
    void testCrewMemberName() {
        CrewMember crewMember = new CrewMember("Член 1");
        assertEquals("Член 1", crewMember.getName());
    }

    @Test
    void testEmotionDescription() {
        Emotion emotion = new Emotion("неуютно");
        assertEquals("неуютно", emotion.getDescription());
    }

    @Test
    void testPhysicsPrinciple() {
        PhysicsPrinciple principle = new PhysicsPrinciple("как будто отношения между людьми подчиняются тем же законам, что отношения между атомами и молекулами");
        assertEquals("как будто отношения между людьми подчиняются тем же законам, что отношения между атомами и молекулами", principle.getPrinciple());
    }

    @Test
    void testCrewMembers() {
        CrewMember[] crewMembers = {
                new CrewMember("Член 1"),
                new CrewMember("Член 2"),
                new CrewMember("Член 3"),
                new CrewMember("Член 4")
        };
        Crew crew = new Crew(crewMembers);
        assertEquals(4, crew.getMembers().length);
    }

    @Test
    void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SpaceShip(""));
        assertThrows(IllegalArgumentException.class, () -> new SpaceShip(null));
        assertThrows(IllegalArgumentException.class, () -> new Engine(""));
        assertThrows(IllegalArgumentException.class, () -> new Engine(null));
    }

    @Test
    void testInvalidCrew() {
        assertThrows(IllegalArgumentException.class, () -> new Crew(null));
        assertThrows(IllegalArgumentException.class, () -> new Crew(new CrewMember[]{}));
        assertThrows(IllegalArgumentException.class, () -> new Crew(new CrewMember[]{ new CrewMember("Член 1") }));
        assertThrows(IllegalArgumentException.class, () -> new Crew(new CrewMember[]{ new CrewMember("Член 1"), new CrewMember("Член 2"), new CrewMember("Член 3"), null }));
    }

    @Test
    void testFullStoryIntegration() {
        SpaceShip spaceShip = new SpaceShip("Золотое Сердце");
        Engine engine = new Engine("фотоновом двигателе");
        CrewMember[] crewMembers = {
                new CrewMember("Член 1"),
                new CrewMember("Член 2"),
                new CrewMember("Член 3"),
                new CrewMember("Член 4")
        };
        Crew crew = new Crew(crewMembers);
        Emotion emotion = new Emotion("неуютно");
        PhysicsPrinciple principle = new PhysicsPrinciple("как будто отношения между людьми подчиняются тем же законам, что отношения между атомами и молекулами");

        Observation observation = new Observation(spaceShip, engine, crew, emotion, principle);
        Story story = new Story(observation);

        String expectedStory = "Золотое Сердце плыл через космическую ночь, теперь уже на обычном фотоновом двигателе. Четыре человека, составлявшие его экипаж, чувствовали себя неуютно, зная, что они вместе не по собственной воле и не по простому совпадению, а по странному физическому принципу -- как будто отношения между людьми подчиняются тем же законам, что отношения между атомами и молекулами.";
        assertEquals(expectedStory, story.getFullStory());
    }
}
