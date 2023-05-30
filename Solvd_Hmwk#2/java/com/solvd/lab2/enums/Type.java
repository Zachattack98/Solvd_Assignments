package com.solvd.lab2.enums;

public enum Type {
    HOME("Home Computer"),
    LAPTOP("Laptop");

    private String variety = null;

    private Type(String variety) {
        this.variety = variety;
    }

    public String getType() {
        return this.variety;
    }
}