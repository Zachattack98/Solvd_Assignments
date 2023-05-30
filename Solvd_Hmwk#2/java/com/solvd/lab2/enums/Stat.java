package com.solvd.lab2.enums;

public enum Stat {
    //unchangeable static variables for determing repair/replacement status
    REPAIR(1),
    REPLACE(2),
    WORKING(3);

    //variable and constructor required to initialize each constant with a value
    private final int statComponent;

    Stat(int statComponent) {
        this.statComponent = statComponent;
    }

    public int getStatComponent() {
        return this.statComponent;
    }
}