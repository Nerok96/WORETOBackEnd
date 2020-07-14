package WORETO.resources.users;

import WORETO.documents.Status;
import WORETO.dtos.TimeRegistryReadDetailDto;
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

}
