package WORETO.dtos;

import WORETO.documents.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeRegistryCommonDto {

    private String assignedUserEmail;
    private String assignedProjectId;
    private LocalDate assignedLocalDate;
    private Integer minutesWorked;
    private Status status;
    private String description;

    public TimeRegistryCommonDto() {
    }

    public TimeRegistryCommonDto(String assignedUserEmail,
                                 String assignedProjectId,
                                 LocalDate assignedLocalDate,
                                 Integer minutesWorked, Status status,
                                 String description) {
        this.assignedUserEmail = assignedUserEmail;
        this.assignedProjectId = assignedProjectId;
        this.assignedLocalDate = assignedLocalDate;
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

    public LocalDate getAssignedLocalDate() {
        return assignedLocalDate;
    }

    public void setAssignedLocalDate(LocalDate assignedLocalDate) {
        this.assignedLocalDate = assignedLocalDate;
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
                ", assignedLocalDate=" + assignedLocalDate +
                ", minutesWorked=" + minutesWorked +
                ", status=" + status +
                ", description='" + description + '\'';
    }
}
