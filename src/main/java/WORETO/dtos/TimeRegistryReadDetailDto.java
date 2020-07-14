package WORETO.dtos;

import WORETO.documents.TimeRegistry;
import WORETO.documents.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeRegistryReadDetailDto extends TimeRegistryCommonDto {

    private String id;
    private String assignedProjectClientId;
    private String assignedProjectMatterId;
    private String assignedProjectProjectName;
    private String createdByUserEmail;
    private LocalDateTime createdAtLocalDateTime;
    private String lastModifiedByUserEmail;
    private LocalDateTime lastModifiedLocalDateTime;

    public TimeRegistryReadDetailDto() {
    }

    public TimeRegistryReadDetailDto(TimeRegistry timeRegistry) {
        super(timeRegistry.getAssignedUser().getEmail(),
                timeRegistry.getAssignedProject().getId(),
                timeRegistry.getAssignedLocalDateTime(),
                timeRegistry.getMinutesWorked(),
                timeRegistry.getStatus(),
                timeRegistry.getDescription());
        this.id = timeRegistry.getId();
        this.assignedProjectClientId = timeRegistry.getAssignedProject().getClientId();
        this.assignedProjectMatterId = timeRegistry.getAssignedProject().getMatterId();
        this.assignedProjectProjectName = timeRegistry.getAssignedProject().getProjectName();
        this.createdByUserEmail = timeRegistry.getCreatedByUser().getEmail();
        this.createdAtLocalDateTime = timeRegistry.getCreatedAtLocalDateTime();
        if (Objects.nonNull(timeRegistry.getLastModifiedByUser())) {
            this.lastModifiedByUserEmail = timeRegistry.getLastModifiedByUser().getEmail();
        }
        this.lastModifiedLocalDateTime = timeRegistry.getLastModifiedLocalDateTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignedProjectClientId() {
        return assignedProjectClientId;
    }

    public void setAssignedProjectClientId(String assignedProjectClientId) {
        this.assignedProjectClientId = assignedProjectClientId;
    }

    public String getAssignedProjectMatterId() {
        return assignedProjectMatterId;
    }

    public void setAssignedProjectMatterId(String assignedProjectMatterId) {
        this.assignedProjectMatterId = assignedProjectMatterId;
    }

    public String getAssignedProjectProjectName() {
        return assignedProjectProjectName;
    }

    public void setAssignedProjectProjectName(String assignedProjectProjectName) {
        this.assignedProjectProjectName = assignedProjectProjectName;
    }

    public String getCreatedByUserEmail() {
        return createdByUserEmail;
    }

    public void setCreatedByUserEmail(String createdByUserEmail) {
        this.createdByUserEmail = createdByUserEmail;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return createdAtLocalDateTime;
    }

    public void setCreatedAtLocalDateTime(LocalDateTime createdAtLocalDateTime) {
        this.createdAtLocalDateTime = createdAtLocalDateTime;
    }

    public String getLastModifiedByUserEmail() {
        return lastModifiedByUserEmail;
    }

    public void setLastModifiedByUserEmail(String lastModifiedByUserEmail) {
        this.lastModifiedByUserEmail = lastModifiedByUserEmail;
    }

    public LocalDateTime getLastModifiedLocalDateTime() {
        return lastModifiedLocalDateTime;
    }

    public void setLastModifiedLocalDateTime(LocalDateTime lastModifiedLocalDateTime) {
        this.lastModifiedLocalDateTime = lastModifiedLocalDateTime;
    }

    @Override
    public String toString() {
        return "TimeRegistryReadDetailDto{" +
                "id='" + id + '\'' +
                ", assignedProjectClientId=" + assignedProjectClientId +
                ", assignedProjectMatterId=" + assignedProjectMatterId +
                ", assignedProjectProjectName=" + assignedProjectProjectName +
                super.toString() +
                ", createdByUserEmail=" + createdByUserEmail +
                ", createdAtLocalDateTime=" + createdAtLocalDateTime +
                ", lastModifiedByUserEmail=" + lastModifiedByUserEmail +
                ", lastModifiedLocalDateTime=" + lastModifiedLocalDateTime +
                '}';
    }
}
