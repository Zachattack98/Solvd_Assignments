package com.solvd.lab2.enums;

import java.util.stream.Stream;

public enum Time {
    FULLDAY,
    HALFDAY;

    double time;

    Time(double time) {
        this.time = time;
    }

    Time() { }

    public double getTime() {
        switch (this) {
            case FULLDAY:
                return 1.0; //time for replacing any component; one full day
            case HALFDAY:
                return 0.5; //time for replacing any component; one full day
            default:
                return 0.0;
        }
    }

}