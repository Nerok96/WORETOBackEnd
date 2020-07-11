package WORETO.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class TimeRegistry {

    @Id
    private String id;
    @DBRef
    private User assignedUser;
    @DBRef
    private Project assignedProject;
    private LocalDateTime assignedLocalDateTime;
    private Integer minutesWorked;
    private Status status;

    //Audit fields
    private User createdByUser;
    private LocalDateTime cretatedAtLocalDateTime;
    private User lastModifiedByUser;
    private LocalDateTime lastModifiedLocalDateTime;

    public TimeRegistry(User createdByUser) {
        this.createdByUser = createdByUser;
        this.cretatedAtLocalDateTime = LocalDateTime.now();
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

    public LocalDateTime getCretatedAtLocalDateTime() {
        return cretatedAtLocalDateTime;
    }

    public void setCretatedAtLocalDateTime(LocalDateTime cretatedAtLocalDateTime) {
        this.cretatedAtLocalDateTime = cretatedAtLocalDateTime;
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

    @Override
    public String toString() {
        return "TimeRegistry{" +
                "id='" + id + '\'' +
                ", assignedUser=" + assignedUser +
                ", assignedProject=" + assignedProject +
                ", assignedLocalDateTime=" + assignedLocalDateTime +
                ", minutesWorked=" + minutesWorked +
                ", status=" + status +
                ", createdByUser=" + createdByUser +
                ", cretatedAtLocalDateTime=" + cretatedAtLocalDateTime +
                ", lastModifiedByUser=" + lastModifiedByUser +
                ", lastModifiedLocalDateTime=" + lastModifiedLocalDateTime +
                '}';
    }
}
