package org.tub.vsp.data.type;

public enum Severity {
    VB("Vordringlicher Bedarf (VB)"),
    ;

    public final String description;

    Severity(String description) {
        this.description = description;
    }
}
