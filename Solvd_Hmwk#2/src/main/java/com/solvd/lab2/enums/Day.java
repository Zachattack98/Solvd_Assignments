package com.solvd.lab2.enums;

import com.solvd.lab2.lambdas.Function;

public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public void halfOffDay(Function<Integer, Float> factor, Day day, int price) {
        factor = null;

        //half days are Tuesday and Thursday
        if(day==TUESDAY || day==THURSDAY) {
            factor = p -> p/2.0f;
        }
        else { //any other day is full price
            factor = p -> p/1.0f;
        }

        factor.apply(price);
    }
}