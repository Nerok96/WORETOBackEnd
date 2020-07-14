package WORETO.dtos;

import WORETO.documents.TimeRegistry;
import WORETO.documents.User;

public class TimeRegistryCreationDto extends TimeRegistryCommonDto {

    private User createdByUser;

    public TimeRegistryCreationDto() {
    }

    public TimeRegistryCreationDto(TimeRegistry timeRegistry) {
        super(timeRegistry.getAssignedUser(),
                timeRegistry.getAssignedProject(),
                timeRegistry.getAssignedLocalDateTime(),
                timeRegistry.getMinutesWorked(),
                timeRegistry.getStatus(),
                timeRegistry.getDescription());
        this.createdByUser = timeRegistry.getCreatedByUser();
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    @Override
    public String toString() {
        return "TimeRegistryCreationDto{" +
                super.toString() +
                ", createdByUser=" + createdByUser +
                '}';
    }
}
