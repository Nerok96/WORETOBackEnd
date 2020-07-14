package WORETO.dtos;

import WORETO.documents.Project;
import WORETO.documents.Status;
import WORETO.documents.User;

import java.time.LocalDateTime;

public class TimeRegistryCommonDto {

    private User assignedUser;
    private Project assignedProject;
    private LocalDateTime assignedLocalDateTime;
    private Integer minutesWorked;
    private Status status;
    private String description;

    public TimeRegistryCommonDto() {
    }

    public TimeRegistryCommonDto(User assignedUser,
                                 Project assignedProject,
                                 LocalDateTime assignedLocalDateTime,
                                 Integer minutesWorked, Status status,
                                 String description) {
        this.assignedUser = assignedUser;
        this.assignedProject = assignedProject;
        this.assignedLocalDateTime = assignedLocalDateTime;
        this.minutesWorked = minutesWorked;
        this.status = status;
        this.description = description;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
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
        return ", assignedUser=" + assignedUser +
                ", assignedProject=" + assignedProject +
                ", assignedLocalDateTime=" + assignedLocalDateTime +
                ", minutesWorked=" + minutesWorked +
                ", status=" + status +
                ", description='" + description + '\'';
    }
}
