package ru.ArseniiDeviatkin.lab1.task3;

public class Crew {
    private CrewMember[] members;

    public Crew(CrewMember[] members) {
        if (members == null || members.length != 4) {
            throw new IllegalArgumentException("Crew must consist of exactly 4 members");
        }
        for (CrewMember member : members) {
            if (member == null) {
                throw new IllegalArgumentException("CrewMember cannot be null");
            }
        }
        this.members = members;
    }

    public CrewMember[] getMembers() {
        return members;
    }
}
