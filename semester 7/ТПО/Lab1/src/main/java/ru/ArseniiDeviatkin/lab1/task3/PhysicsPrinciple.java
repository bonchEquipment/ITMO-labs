package ru.ArseniiDeviatkin.lab1.task3;

public class PhysicsPrinciple {
    private String principle;

    public PhysicsPrinciple(String principle) {
        if (principle == null || principle.isEmpty()) {
            throw new IllegalArgumentException("PhysicsPrinciple cannot be null or empty");
        }
        this.principle = principle;
    }

    public String getPrinciple() {
        return principle;
    }
}
