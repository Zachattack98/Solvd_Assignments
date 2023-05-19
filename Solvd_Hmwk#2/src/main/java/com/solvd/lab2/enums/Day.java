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

    public void halfOffDay(Function<Integer, Float> factor, int price) {
        factor.apply(price);
    }
}