package WORETO.dtos;

import java.time.LocalDateTime;

public class TimeRegistryMinimumDto {

    private LocalDateTime assignedLocalDateTime;
    private String clientId;
    private String matterId;
    private String description;
    private String minutesWorked;

    public TimeRegistryMinimumDto() {
    }

    public TimeRegistryMinimumDto(LocalDateTime assignedLocalDateTime,
                                  String clientId, String matterId,
                                  String description, String minutesWorked) {
        this.assignedLocalDateTime = assignedLocalDateTime;
        this.clientId = clientId;
        this.matterId = matterId;
        this.description = description;
        this.minutesWorked = minutesWorked;
    }

    public LocalDateTime getAssignedLocalDateTime() {
        return assignedLocalDateTime;
    }

    public void setAssignedLocalDateTime(LocalDateTime assignedLocalDateTime) {
        this.assignedLocalDateTime = assignedLocalDateTime;
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
                "assignedLocalDateTime=" + assignedLocalDateTime +
                ", clientId='" + clientId + '\'' +
                ", matterId='" + matterId + '\'' +
                ", description='" + description + '\'' +
                ", minutesWorked='" + minutesWorked + '\'' +
                '}';
    }
}
