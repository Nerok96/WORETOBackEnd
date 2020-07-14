package WORETO.dtos;

import WORETO.documents.TimeRegistry;
import WORETO.documents.User;

import java.time.LocalDateTime;

public class TimeRegistryDto extends TimeRegistryCommonDto {

    private String id;
    private User createdByUser;
    private LocalDateTime createdAtLocalDateTime;
    private User lastModifiedByUser;
    private LocalDateTime lastModifiedLocalDateTime;

    public TimeRegistryDto(){}

    public TimeRegistryDto (TimeRegistry timeRegistry){
        super(timeRegistry.getAssignedUser(),
                timeRegistry.getAssignedProject(),
                timeRegistry.getAssignedLocalDateTime(),
                timeRegistry.getMinutesWorked(),
                timeRegistry.getStatus(),
                timeRegistry.getDescription());
        this.id = timeRegistry.getId();
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

    @Override
    public String toString() {
        return "TimeRegistryDto{" +
                "id='" + id + '\'' +
                super.toString() +
                ", createdByUser=" + createdByUser +
                ", createdAtLocalDateTime=" + createdAtLocalDateTime +
                ", lastModifiedByUser=" + lastModifiedByUser +
                ", lastModifiedLocalDateTime=" + lastModifiedLocalDateTime +
                '}';
    }
}
