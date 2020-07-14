package WORETO.dtos;

import WORETO.documents.Project;
import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;

import java.time.LocalDateTime;

public class TimeRegistryUpdateDto {

    private String id;
    private User assignedUser;
    private Project assignedProject;
    private LocalDateTime assignedLocalDateTime;
    private Integer minutesWorked;
    private Status status;
    private String description;
    private User lastModifiedByUser;

    public TimeRegistryUpdateDto() {
    }

    public TimeRegistryUpdateDto(TimeRegistry timeRegistry) {
        this.id = timeRegistry.getId();
        this.assignedUser = timeRegistry.getAssignedUser();
        this.assignedProject = timeRegistry.getAssignedProject();
        this.assignedLocalDateTime = timeRegistry.getAssignedLocalDateTime();
        this.minutesWorked = timeRegistry.getMinutesWorked();
        this.status = timeRegistry.getStatus();
        this.description = timeRegistry.getDescription();
        this.lastModifiedByUser = timeRegistry.getLastModifiedByUser();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getLastModifiedByUser() {
        return lastModifiedByUser;
    }

    public void setLastModifiedByUser(User lastModifiedByUser) {
        this.lastModifiedByUser = lastModifiedByUser;
    }

    @Override
    public String toString() {
        return "TimeRegistryUpdateDto{" +
                "id='" + id + '\'' +
                ", assignedUser=" + assignedUser +
                ", assignedProject=" + assignedProject +
                ", assignedLocalDateTime=" + assignedLocalDateTime +
                ", minutesWorked=" + minutesWorked +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", lastModifiedByUser=" + lastModifiedByUser +
                '}';
    }
}
