package WORETO.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Document
public class TimeRegistry {

    @Transient
    public static final String SEQUENCE_NAME = "time-registries-sequence";

    @Id
    private String id;
    @DBRef
    private User assignedUser;
    @DBRef
    private Project assignedProject;
    private LocalDate assignedLocalDate;
    private Integer minutesWorked;
    private Status status;
    private String description;

    //Audit fields
    @DBRef
    private User createdByUser;
    private LocalDateTime createdAtLocalDateTime;
    @DBRef
    private User lastModifiedByUser;
    private LocalDateTime lastModifiedLocalDateTime;

    public TimeRegistry(){}

    public TimeRegistry(User createdByUser) {
        this.createdByUser = createdByUser;
        this.createdAtLocalDateTime = LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdFromLong(Long id) {
        this.id = "" + id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TimeRegistry{" +
                "id='" + id + '\'' +
                ", assignedUser=" + assignedUser +
                ", assignedProject=" + assignedProject +
                ", assignedLocalDate=" + assignedLocalDate +
                ", minutesWorked=" + minutesWorked +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createdByUser=" + createdByUser +
                ", createdAtLocalDateTime=" + createdAtLocalDateTime +
                ", lastModifiedByUser=" + lastModifiedByUser +
                ", lastModifiedLocalDateTime=" + lastModifiedLocalDateTime +
                '}';
    }

    public static class Builder {
        private TimeRegistry timeRegistry;

        private Builder(){
            this.timeRegistry = new TimeRegistry();
            this.timeRegistry.createdAtLocalDateTime = LocalDateTime.now();
        }

        public Builder id(String id){
            this.timeRegistry.id = id;
            return this;
        }

        public Builder id(long id){
            this.timeRegistry.id = "" + id;
            return this;
        }

        public Builder assignedUser(User assignedUser){
            this.timeRegistry.assignedUser = assignedUser;
            return this;
        }

        public Builder assignedProject (Project assignedProject){
            this.timeRegistry.assignedProject = assignedProject;
            return this;
        }

        public Builder assignedLocalDate(LocalDate assignedLocalDate) {
            this.timeRegistry.assignedLocalDate = assignedLocalDate;
            return this;
        }

        public Builder minutesWorked (Integer minutesWorked){
            this.timeRegistry.minutesWorked = minutesWorked;
            return this;
        }

        public Builder status (Status status){
            this.timeRegistry.status = status;
            return this;
        }

        public Builder description(String description){
            this.timeRegistry.description = description;
            return this;
        }

        public Builder createdByUser(User createdByUser){
            this.timeRegistry.createdByUser = createdByUser;
            return this;
        }

        public Builder lastModifiedByUser (User lastModifiedByUser){
            this.timeRegistry.lastModifiedByUser = lastModifiedByUser;
            return this;
        }

        public Builder lastModifiedLocalDateTime (LocalDateTime lastModifiedLocalDateTime){
            this.timeRegistry.lastModifiedLocalDateTime = lastModifiedLocalDateTime;
            return this;
        }

        public TimeRegistry build() {
            return this.timeRegistry;
        }
    }
}
