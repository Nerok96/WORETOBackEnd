package WORETO.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Scanner;

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

    public static Builder builder(User createdByUser) {
        return new Builder(createdByUser);
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

    public boolean setMinutesWorkedFromString(String stringMinutesWorked){
        if(isValidStringMinutesWorked(stringMinutesWorked)) {
            Scanner in = new Scanner(stringMinutesWorked).useDelimiter(":");
            Integer hoursInMinutes = in.nextInt() * 60;
            Integer minutes = in.nextInt();
            this.minutesWorked = hoursInMinutes + minutes;
            return true;
        }else {
            this.minutesWorked = -1;
            return false;
        }
    }

    private boolean isValidStringMinutesWorked(String stringMinutesWorked){
        //Checks format HH:MM, eg: 12:34
        return stringMinutesWorked.matches("\\d{2}:\\d{2}");
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

    public static class Builder {
        private TimeRegistry timeRegistry;

        private Builder(User createdByUser){
            this.timeRegistry = new TimeRegistry(createdByUser);
        }

        public Builder assignedUser(User assignedUser){
            this.timeRegistry.setAssignedUser(assignedUser);
            return this;
        }

        public Builder assignedProject (Project assignedProject){
            this.timeRegistry.setAssignedProject(assignedProject);
            return this;
        }

        public Builder assignedLocalDateTime (LocalDateTime assignedLocalDateTime){
            this.timeRegistry.setAssignedLocalDateTime(assignedLocalDateTime);
            return this;
        }

        public Builder minutesWorked (Integer minutesWorked){
            this.timeRegistry.setMinutesWorked(minutesWorked);
            return this;
        }

        public Builder minutesWorked (String stringMinutesWorked){
            this.timeRegistry.setMinutesWorkedFromString(stringMinutesWorked);
            return this;
        }

        public Builder status (Status status){
            this.timeRegistry.setStatus(status);
            return this;
        }

        public Builder lastModifiedByUser (User lastModifiedByUser){
            this.timeRegistry.setLastModifiedByUser(lastModifiedByUser);
            return this;
        }

        public Builder lastModifiedLocalDateTime (LocalDateTime lastModifiedLocalDateTime){
            this.timeRegistry.setLastModifiedLocalDateTime(lastModifiedLocalDateTime);
            return this;
        }

        public TimeRegistry build() {
            return this.timeRegistry;
        }
    }
}
