package WORETO.dtos;

import WORETO.documents.TimeRegistry;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeRegistryMinimumDto {

    private LocalDate assignedLocalDate;
    private String clientId;
    private String matterId;
    private String description;
    private String minutesWorked;

    public TimeRegistryMinimumDto() {
    }

    public TimeRegistryMinimumDto(LocalDate assignedLocalDate,
                                  String clientId, String matterId,
                                  String description, String minutesWorked) {
        this.assignedLocalDate = assignedLocalDate;
        this.clientId = clientId;
        this.matterId = matterId;
        this.description = description;
        this.minutesWorked = minutesWorked;
    }

    public TimeRegistryMinimumDto(TimeRegistry timeRegistry) {
        this.assignedLocalDate = timeRegistry.getAssignedLocalDate();
        this.clientId = timeRegistry.getAssignedProject().getClientId();
        this.matterId = timeRegistry.getAssignedProject().getMatterId();
        this.description = timeRegistry.getDescription();
        this.minutesWorked = "" + timeRegistry.getMinutesWorked();
    }

    public LocalDate getAssignedLocalDate() {
        return assignedLocalDate;
    }

    public void setAssignedLocalDate(LocalDate assignedLocalDate) {
        this.assignedLocalDate = assignedLocalDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMatterId() {
        return matterId;
    }

    public void setMatterId(String matterId) {
        this.matterId = matterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinutesWorked() {
        return minutesWorked;
    }

    public void setMinutesWorked(String minutesWorked) {
        this.minutesWorked = minutesWorked;
    }

    @Override
    public String toString() {
        return "TimeRegistryMinimumDto{" +
                "assignedLocalDate=" + assignedLocalDate +
                ", clientId='" + clientId + '\'' +
                ", matterId='" + matterId + '\'' +
                ", description='" + description + '\'' +
                ", minutesWorked='" + minutesWorked + '\'' +
                '}';
    }
}
