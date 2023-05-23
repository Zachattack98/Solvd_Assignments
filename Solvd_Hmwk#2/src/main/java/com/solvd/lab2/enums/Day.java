package com.solvd.lab2.enums;

import com.solvd.lab2.lambdas.Function;

public enum Day {
    MONDAY ("Monday"),
    TUESDAY ("Tuesday"),
    WEDNESDAY ("Wednesday"),
    THURSDAY ("Thursday"),
    FRIDAY ("Friday"),
    SATURDAY ("Saturday"),
    SUNDAY ("Sunday");

    private final String dayName;

    Day(String dayName) {
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }

    public float halfOffDay(Function<Integer, Float> factor, int price) {
        return factor.apply(price);
    }

}