package WORETO.documents;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

public class TimeRegistryTest {

    @Test
    void testTimeRegistryBuilder(){
        LocalDateTime ldt = LocalDateTime.now();
        User createdByUser = new User();
        createdByUser.setEmail("test@test.com");
        Project project = new Project();
        project.setProjectName("Project test");
        Status statusDraft = Status.DRAFT;
        TimeRegistry timeRegistry = TimeRegistry.builder(createdByUser)
                .assignedUser(createdByUser)
                .assignedProject(project)
                .assignedLocalDateTime(ldt)
                .minutesWorked(10)
                .status(statusDraft)
                .lastModifiedByUser(createdByUser)
                .lastModifiedLocalDateTime(ldt.plus(Period.ofDays(1)))
                .build();
        assertEquals(createdByUser.getEmail(),timeRegistry.getAssignedUser().getEmail());
        assertEquals(project.getProjectName(),timeRegistry.getAssignedProject().getProjectName());
        assertEquals(ldt,timeRegistry.getAssignedLocalDateTime());
        assertEquals(10,timeRegistry.getMinutesWorked().intValue());
        assertEquals(statusDraft,timeRegistry.getStatus());
        assertEquals(createdByUser.getEmail(),timeRegistry.getCreatedByUser().getEmail());
        assertEquals(createdByUser.getEmail(),timeRegistry.getLastModifiedByUser().getEmail());
        assertEquals(ldt.plus(Period.ofDays(1)),timeRegistry.getLastModifiedLocalDateTime());
        assertNotEquals("NO_EMAIL", timeRegistry.getAssignedUser().getEmail());
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
