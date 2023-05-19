package com.solvd.lab2.enums;

import java.util.stream.Stream;

public enum Name {
    SCREEN("LCD Screen"),
    HDD("Hard Drive"),
    USB ("USB Adapters"),
    POWERUNIT ("Power Unit"),
    COOLINGFAN ("Cooling Fan");

    private String nameCompnt = null;

    Name(String nameCompnt) { this.nameCompnt = nameCompnt;}

    public String getName() {
        return this.nameCompnt;
    }

    public static Stream<Name> stream() {
        return Stream.of(Name.values());
    }
}