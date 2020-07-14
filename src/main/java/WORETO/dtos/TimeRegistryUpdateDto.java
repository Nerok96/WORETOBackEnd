package WORETO.dtos;

import WORETO.documents.TimeRegistry;

public class TimeRegistryUpdateDto extends TimeRegistryCommonDto {

    private String id;
    private String lastModifiedByUserEmail;

    public TimeRegistryUpdateDto() {
    }

    public TimeRegistryUpdateDto(TimeRegistry timeRegistry) {
        super(timeRegistry.getAssignedUser().getEmail(),
                timeRegistry.getAssignedProject().getId(),
                timeRegistry.getAssignedLocalDateTime(),
                timeRegistry.getMinutesWorked(),
                timeRegistry.getStatus(),
                timeRegistry.getDescription());
        this.id = timeRegistry.getId();
        this.lastModifiedByUserEmail = timeRegistry.getLastModifiedByUser().getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModifiedByUserEmail() {
        return lastModifiedByUserEmail;
    }

    public void setLastModifiedByUserEmail(String lastModifiedByUserEmail) {
        this.lastModifiedByUserEmail = lastModifiedByUserEmail;
    }

    @Override
    public String toString() {
        return "TimeRegistryUpdateDto{" +
                "id='" + id + '\'' +
                super.toString() +
                ", lastModifiedByUserEmail=" + lastModifiedByUserEmail +
                '}';
    }
}
