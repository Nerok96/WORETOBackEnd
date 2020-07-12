package WORETO.resources.users;

import WORETO.documents.Status;
import WORETO.dtos.TimeRegistryDto;
import WORETO.resources.ApiTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

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
        TimeRegistryDto timeRegistryDto = this.webTestClient
                .get().uri(contextPath + TIME_REGISTRIES + TIME_REGISTRIES_ID, id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeRegistryDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody()
                ;
        assertNotNull(timeRegistryDto);
        assertEquals(id, timeRegistryDto.getId());
        assertEquals("TimeRecorderA", timeRegistryDto.getAssignedUser().getName());
        assertEquals("Project ONE 1", timeRegistryDto.getAssignedProject().getProjectName());
        assertEquals(fixedLdt, timeRegistryDto.getAssignedLocalDateTime());
        assertEquals(Integer.valueOf(10), timeRegistryDto.getMinutesWorked());
        assertEquals(Status.DRAFT, timeRegistryDto.getStatus());
        assertEquals("Working hard", timeRegistryDto.getDescription());
        assertEquals("TimeRecorderA", timeRegistryDto.getCreatedByUser().getName());
        assertNotNull(timeRegistryDto.getCreatedAtLocalDateTime());
        assertNull(timeRegistryDto.getLastModifiedByUser());
        assertNull(timeRegistryDto.getLastModifiedLocalDateTime());
    }

}
