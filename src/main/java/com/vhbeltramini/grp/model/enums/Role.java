package com.vhbeltramini.grp.model.enums;

public enum Role {

    ADMIN("ADMIN"),
    COORDENADOR("COORDENADOR"),
    DIRETOR("DIRETOR");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Role fromString(String text) {
        for (Role b : Role.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
