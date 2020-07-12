package WORETO.dtos;

import WORETO.documents.Project;
import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;

import java.time.LocalDateTime;

public class TimeRegistryDto {

    private String id;
    private User assignedUser;
    private Project assignedProject;
    private LocalDateTime assignedLocalDateTime;
    private Integer minutesWorked;
    private Status status;
    private User createdByUser;
    private LocalDateTime createdAtLocalDateTime;
    private User lastModifiedByUser;
    private LocalDateTime lastModifiedLocalDateTime;

    public TimeRegistryDto(){}

    public TimeRegistryDto (TimeRegistry timeRegistry){
        this.id = timeRegistry.getId();
        this.assignedUser = timeRegistry.getAssignedUser();
        this.assignedProject = timeRegistry.getAssignedProject();
        this.assignedLocalDateTime = timeRegistry.getAssignedLocalDateTime();
        this.minutesWorked = timeRegistry.getMinutesWorked();
        this.status = timeRegistry.getStatus();
        this.createdByUser = timeRegistry.getCreatedByUser();
        this.createdAtLocalDateTime = timeRegistry.getCreatedAtLocalDateTime();
        this.lastModifiedByUser = timeRegistry.getLastModifiedByUser();
        this.lastModifiedLocalDateTime = timeRegistry.getLastModifiedLocalDateTime();
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

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return createdAtLocalDateTime;
    }

    public void setCreatedAtLocalDateTime(LocalDateTime createdAtLocalDateTime) {
        this.createdAtLocalDateTime = createdAtLocalDateTime;
    }

    public User getLastModifiedByUser() {
        return lastModifiedByUser;
    }

    public void setLastModifiedByUser(User lastModifiedByUser) {
        this.lastModifiedByUser = lastModifiedByUser;
    }

    public LocalDateTime getLastModifiedLocalDateTime() {
        return lastModifiedLocalDateTime;
    }

    public void setLastModifiedLocalDateTime(LocalDateTime lastModifiedLocalDateTime) {
        this.lastModifiedLocalDateTime = lastModifiedLocalDateTime;
    }
}
