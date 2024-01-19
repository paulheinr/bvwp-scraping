package org.tub.vsp.data.container;

import org.tub.vsp.data.type.Severity;

public class ProjectInformationDataContainer {
    private String projectNumber;
    private String street;
    private Severity severity;

    public String getProjectNumber() {
        return projectNumber;
    }

    public ProjectInformationDataContainer setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ProjectInformationDataContainer setStreet(String street) {
        this.street = street;
        return this;
    }

    public Severity getSeverity() {
        return severity;
    }

    public ProjectInformationDataContainer setSeverity(Severity severity) {
        this.severity = severity;
        return this;
    }
}
