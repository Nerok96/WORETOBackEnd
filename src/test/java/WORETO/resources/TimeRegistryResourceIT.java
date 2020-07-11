package WORETO.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

import static WORETO.resources.TimeRegistryResource.TIME_REGISTRIES;

@ApiTestConfig
public class TimeRegistryResourceIT {

    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetHelloWorld() {
        this.webTestClient
                .get().uri(contextPath + TIME_REGISTRIES)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
             ;
    }

    @Test
    void testGettHelloWorldNotFound() {
        this.webTestClient
                .get().uri(contextPath + "/things")
                .exchange()
                .expectStatus().isNotFound()
        ;
    }
}
