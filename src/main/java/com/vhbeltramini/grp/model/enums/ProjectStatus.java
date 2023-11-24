package com.vhbeltramini.grp.model.enums;

public enum ProjectStatus {

    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    CONTESTADO("Contestado"),
    CANCELADO("Cancelado");

    private final String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static ProjectStatus fromString(String text) {
        for (ProjectStatus b : ProjectStatus.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
