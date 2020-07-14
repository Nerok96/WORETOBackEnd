package WORETO.dtos;

import WORETO.documents.Status;

import java.time.LocalDateTime;

public class TimeRegistryCommonDto {

    private String assignedUserEmail;
    private String assignedProjectId;
    private LocalDateTime assignedLocalDateTime;
    private Integer minutesWorked;
    private Status status;
    private String description;

    public TimeRegistryCommonDto() {
    }

    public TimeRegistryCommonDto(String assignedUserEmail,
                                 String assignedProjectId,
                                 LocalDateTime assignedLocalDateTime,
                                 Integer minutesWorked, Status status,
                                 String description) {
        this.assignedUserEmail = assignedUserEmail;
        this.assignedProjectId = assignedProjectId;
        this.assignedLocalDateTime = assignedLocalDateTime;
        this.minutesWorked = minutesWorked;
        this.status = status;
        this.description = description;
    }

    public String getAssignedUserEmail() {
        return assignedUserEmail;
    }

    public void setAssignedUserEmail(String assignedUserEmail) {
        this.assignedUserEmail = assignedUserEmail;
    }

    public String getAssignedProjectId() {
        return assignedProjectId;
    }

    public void setAssignedProjectId(String assignedProjectId) {
        this.assignedProjectId = assignedProjectId;
    }

    public LocalDateTime getAssignedLocalDateTime() {
        return assignedLocalDateTime;
    }

    public void setAssignedLocalDateTime(LocalDateTime assignedLocalDateTime) {
        this.assignedLocalDateTime = assignedLocalDateTime;
    }

    public Integer getMinutesWorked() {
        return minutesWorked;
    }

    public void setMinutesWorked(Integer minutesWorked) {
        this.minutesWorked = minutesWorked;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ", assignedUserEmail=" + assignedUserEmail +
                ", assignedProjectProjectId=" + assignedProjectId +
                ", assignedLocalDateTime=" + assignedLocalDateTime +
                ", minutesWorked=" + minutesWorked +
                ", status=" + status +
                ", description='" + description + '\'';
    }
}
