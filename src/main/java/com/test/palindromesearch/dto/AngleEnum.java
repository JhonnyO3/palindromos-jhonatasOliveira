package com.test.palindromesearch.dto;

public enum AngleEnum {
    ANGLE_HORIZONTAL("HORIZONTAL"),

    ANGLE_VERTICAL("VERTICAL"),

    ANGLE_DIAGONAL("DIAGONAL");

    private String stringValue;

    AngleEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
