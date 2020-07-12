package WORETO.documents;

import WORETO.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ProjectTest {

    @Test
    void testProjectBuilder() {

        String id = "1";
        String clientId = "1111";
        String matterId = "111";
        String projectName = "Project ONE";
        User partner = new User();
        partner.setEmail("partner@partner.com");
        User[] partnerList = new User[]{partner};

        Project project = new Project();
        project.setId(id);
        project.setClientId(clientId);
        project.setMatterId(matterId);
        project.setProjectName(projectName);
        project.setPartnerList(partnerList);

        Project projectBuilder = Project.builder()
                .id(id)
                .clientId(clientId)
                .matterId(matterId)
                .projectName(projectName)
                .partnerList(partnerList)
                .build();

        assertEquals(project.getId(), projectBuilder.getId());
        assertEquals(project.getClientId(), projectBuilder.getClientId());
        assertEquals(project.getMatterId(), projectBuilder.getMatterId());
        assertEquals(project.getProjectName(), projectBuilder.getProjectName());
        assertEquals(project.getPartnerList(), projectBuilder.getPartnerList());
    }

}
