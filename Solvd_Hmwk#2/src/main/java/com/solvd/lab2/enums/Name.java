package com.solvd.lab2.enums;

public enum Name {
    SCREEN("LCD Screen"),
    HDD("Hard Drive"),
    USB ("USB Adapters"),
    POWERUNIT ("Power Unit"),
    COOLINGFAN ("Cooling Fan");

    private String nameCompnt = null;

    private Name() { }

    public String getName() {
        return this.nameCompnt;
    }
}