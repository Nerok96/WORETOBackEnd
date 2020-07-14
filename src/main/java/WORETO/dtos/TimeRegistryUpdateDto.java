package WORETO.dtos;

import WORETO.documents.TimeRegistry;
import WORETO.documents.User;

public class TimeRegistryUpdateDto extends TimeRegistryCommonDto {

    private String id;
    private User lastModifiedByUser;

    public TimeRegistryUpdateDto() {
    }

    public TimeRegistryUpdateDto(TimeRegistry timeRegistry) {
        super(timeRegistry.getAssignedUser(),
                timeRegistry.getAssignedProject(),
                timeRegistry.getAssignedLocalDateTime(),
                timeRegistry.getMinutesWorked(),
                timeRegistry.getStatus(),
                timeRegistry.getDescription()
        );
        this.id = timeRegistry.getId();
        this.lastModifiedByUser = timeRegistry.getLastModifiedByUser();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                super.toString() +
                ", lastModifiedByUser=" + lastModifiedByUser +
                '}';
    }
}
