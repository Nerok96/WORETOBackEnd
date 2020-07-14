package WORETO.resources.users;

import WORETO.documents.Status;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryReadDetailDto;
import WORETO.dtos.TimeRegistryUpdateDto;
import WORETO.resources.ApiTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

import static WORETO.resources.users.TimeRegistryResource.TIME_REGISTRIES;
import static WORETO.resources.users.TimeRegistryResource.TIME_REGISTRIES_ID;
import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
public class TimeRegistryResourceIT {

    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadTimeRegistryDetailById() {
        String id = "1";
        LocalDateTime fixedLdt = LocalDateTime.of(2022, 4, 1, 1, 1, 1);
        TimeRegistryReadDetailDto timeRegistryReadDetailDto = this.webTestClient
                .get().uri(contextPath + TIME_REGISTRIES + TIME_REGISTRIES_ID, id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeRegistryReadDetailDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody()
                ;
        assertNotNull(timeRegistryReadDetailDto);
        assertEquals(id, timeRegistryReadDetailDto.getId());
        assertEquals("timerecorderA@timerecorderA.com", timeRegistryReadDetailDto.getAssignedUserEmail());
        assertEquals("Project ONE 1", timeRegistryReadDetailDto.getAssignedProjectProjectName());
        assertEquals(fixedLdt, timeRegistryReadDetailDto.getAssignedLocalDateTime());
        assertEquals(Integer.valueOf(10), timeRegistryReadDetailDto.getMinutesWorked());
        assertEquals(Status.DRAFT, timeRegistryReadDetailDto.getStatus());
        assertEquals("Working hard", timeRegistryReadDetailDto.getDescription());
        assertEquals("timerecorderA@timerecorderA.com", timeRegistryReadDetailDto.getCreatedByUserEmail());
        assertNotNull(timeRegistryReadDetailDto.getCreatedAtLocalDateTime());
        assertNull(timeRegistryReadDetailDto.getLastModifiedByUserEmail());
        assertNull(timeRegistryReadDetailDto.getLastModifiedLocalDateTime());
    }

    @Test
    void testCreateTimeRegistry() {
        LocalDateTime ldt = LocalDateTime.now();
        String email = "admin@admin.com";
        Integer minutesWorked = 10;
        String projectId = "1";
        String description = "Working hard";
        TimeRegistryCreationDto timeRegistryCreationDto = new TimeRegistryCreationDto();
        timeRegistryCreationDto.setAssignedUserEmail(email);
        timeRegistryCreationDto.setAssignedProjectId(projectId);
        timeRegistryCreationDto.setAssignedLocalDateTime(ldt);
        timeRegistryCreationDto.setMinutesWorked(minutesWorked);
        timeRegistryCreationDto.setStatus(Status.DRAFT);
        timeRegistryCreationDto.setDescription(description);
        timeRegistryCreationDto.setCreatedByUserEmail(email);
        TimeRegistryReadDetailDto timeRegistryReadDetailDto = this.webTestClient
                .post().uri(contextPath + TIME_REGISTRIES)
                .body(BodyInserters.fromObject(timeRegistryCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeRegistryReadDetailDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody();
        assertNotNull(timeRegistryReadDetailDto);
        assertEquals(email, timeRegistryReadDetailDto.getAssignedUserEmail());
        assertEquals(projectId, timeRegistryReadDetailDto.getAssignedProjectId());
        assertEquals(ldt, timeRegistryReadDetailDto.getAssignedLocalDateTime());
        assertEquals(minutesWorked, timeRegistryReadDetailDto.getMinutesWorked());
        assertEquals(Status.DRAFT, timeRegistryReadDetailDto.getStatus());
        assertEquals(email, timeRegistryReadDetailDto.getCreatedByUserEmail());
        assertEquals(description, timeRegistryReadDetailDto.getDescription());
    }

    @Test
    void testUpdateTimeRegistry() {
        String assignedUserEmail = "admin@admin.com";
        String assignedProject = "1";
        LocalDateTime assignedLocalDateTime = LocalDateTime.now();
        Integer minutesWorked = 22;
        Status status = Status.DRAFT;
        String description = "Working not so hard";
        String timeRegistryId = "2";
        String lastModifiedEmail = "admin@admin.com";

        TimeRegistryUpdateDto timeRegistryUpdateDto = new TimeRegistryUpdateDto();
        timeRegistryUpdateDto.setId(timeRegistryId);
        timeRegistryUpdateDto.setAssignedUserEmail(assignedUserEmail);
        timeRegistryUpdateDto.setAssignedProjectId(assignedProject);
        timeRegistryUpdateDto.setAssignedLocalDateTime(assignedLocalDateTime);
        timeRegistryUpdateDto.setMinutesWorked(minutesWorked);
        timeRegistryUpdateDto.setStatus(status);
        timeRegistryUpdateDto.setDescription(description);
        timeRegistryUpdateDto.setLastModifiedByUserEmail(lastModifiedEmail);

        TimeRegistryReadDetailDto timeRegistryReadDetailDtoBeforeEdit = this.webTestClient
                .get().uri(contextPath + TIME_REGISTRIES + TIME_REGISTRIES_ID, timeRegistryId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeRegistryReadDetailDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody();
        assertNotNull(timeRegistryReadDetailDtoBeforeEdit);
        assertNotEquals(assignedUserEmail, timeRegistryReadDetailDtoBeforeEdit.getAssignedUserEmail());

        TimeRegistryReadDetailDto timeRegistryReadDetailDtoAfterEdit = this.webTestClient
                .put().uri(contextPath + TIME_REGISTRIES, timeRegistryId)
                .body(BodyInserters.fromObject(timeRegistryUpdateDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeRegistryReadDetailDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody();
        assertNotNull(timeRegistryReadDetailDtoAfterEdit);
        assertEquals(timeRegistryReadDetailDtoBeforeEdit.getId(),
                timeRegistryReadDetailDtoAfterEdit.getId());
        assertNotEquals(timeRegistryReadDetailDtoBeforeEdit.getMinutesWorked(),
                timeRegistryReadDetailDtoAfterEdit.getMinutesWorked());
        assertEquals(assignedUserEmail, timeRegistryReadDetailDtoAfterEdit.getAssignedUserEmail());
    }
}
