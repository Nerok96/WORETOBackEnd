package WORETO.dtos;

import WORETO.documents.TimeRegistry;

public class TimeRegistryCreationDto extends TimeRegistryCommonDto {

    private String createdByUserEmail;

    public TimeRegistryCreationDto() {
    }

    public TimeRegistryCreationDto(TimeRegistry timeRegistry) {
        super(timeRegistry.getAssignedUser().getEmail(),
                timeRegistry.getAssignedProject().getId(),
                timeRegistry.getAssignedLocalDate(),
                timeRegistry.getMinutesWorked(),
                timeRegistry.getStatus(),
                timeRegistry.getDescription());
        this.createdByUserEmail = timeRegistry.getCreatedByUser().getEmail();
    }

    public String getCreatedByUserEmail() {
        return createdByUserEmail;
    }

    public void setCreatedByUserEmail(String createdByUserEmail) {
        this.createdByUserEmail = createdByUserEmail;
    }

    @Override
    public String toString() {
        return "TimeRegistryCreationDto{" +
                super.toString() +
                ", createdByUserEmail=" + createdByUserEmail +
                '}';
    }
}
