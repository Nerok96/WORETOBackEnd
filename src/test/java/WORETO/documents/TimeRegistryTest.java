package WORETO.documents;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimeRegistryTest {

    @Test
    void testTimeRegistryBuilder(){
        String id  = "1";
        User assignedUser = new User();
        assignedUser.setEmail("assigned@useremail.com");
        Project project = new Project();
        project.setProjectName("Project");
        LocalDateTime ldtAssigned = LocalDateTime.now();
        Integer minutesWorked = 12;
        Status status = Status.BILLED;
        String description = "Working hard";
        User createdByUser = new User();
        createdByUser.setEmail("created@useremail.com");
        User lastModifiedByUser = new User();
        createdByUser.setEmail("lastmodified@useremail.com");
        LocalDateTime ldtLastModified = LocalDateTime.now().plusDays(1);

        TimeRegistry timeRegistry = new TimeRegistry();
        timeRegistry.setId(id);
        timeRegistry.setAssignedUser(assignedUser);
        timeRegistry.setAssignedProject(project);
        timeRegistry.setAssignedLocalDateTime(ldtAssigned);
        timeRegistry.setMinutesWorked(minutesWorked);
        timeRegistry.setStatus(status);
        timeRegistry.setDescription(description);
        timeRegistry.setCreatedByUser(createdByUser);
        timeRegistry.setLastModifiedByUser(lastModifiedByUser);
        timeRegistry.setLastModifiedLocalDateTime(ldtLastModified);

        TimeRegistry timeRegistryBuilder = TimeRegistry.builder()
                .id(id)
                .assignedUser(assignedUser)
                .assignedProject(project)
                .assignedLocalDateTime(ldtAssigned)
                .minutesWorked(minutesWorked)
                .status(status)
                .description(description)
                .createdByUser(createdByUser)
                .lastModifiedByUser(lastModifiedByUser)
                .lastModifiedLocalDateTime(ldtLastModified)
                .build();

        assertEquals(timeRegistry.getId(),timeRegistryBuilder.getId());
        assertEquals(timeRegistry.getAssignedUser().getEmail(),timeRegistryBuilder.getAssignedUser().getEmail());
        assertEquals(timeRegistry.getAssignedProject().getProjectName(),
                timeRegistryBuilder.getAssignedProject().getProjectName());
        assertEquals(timeRegistry.getAssignedLocalDateTime(),timeRegistryBuilder.getAssignedLocalDateTime());
        assertEquals(timeRegistry.getMinutesWorked(),timeRegistryBuilder.getMinutesWorked());
        assertEquals(timeRegistry.getStatus(),timeRegistryBuilder.getStatus());
        assertEquals(timeRegistry.getDescription(),timeRegistryBuilder.getDescription());
        assertEquals(timeRegistry.getCreatedByUser().getEmail(),
                timeRegistryBuilder.getCreatedByUser().getEmail());
        assertEquals(timeRegistry.getLastModifiedLocalDateTime(),
                timeRegistryBuilder.getLastModifiedLocalDateTime());
        assertEquals(timeRegistry.getLastModifiedByUser().getEmail(),
                timeRegistryBuilder.getLastModifiedByUser().getEmail());
    }

    @Test
    void TestSetMinutesWorkedFromString(){
        TimeRegistry tr = new TimeRegistry(new User());
        assertTrue(tr.setMinutesWorkedFromString("12:23"));
        assertFalse(tr.setMinutesWorkedFromString("Hello"));
        assertFalse(tr.setMinutesWorkedFromString("123:12"));
        assertFalse(tr.setMinutesWorkedFromString("1a:12"));
        assertFalse(tr.setMinutesWorkedFromString("12:123"));
        assertFalse(tr.setMinutesWorkedFromString("12:1a"));
        assertFalse(tr.setMinutesWorkedFromString("123:123"));
    }

}
