package WORETO.documents;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

public class Project {

    @Id
    private String id;
    private String ClientId;
    private String MatterId;
    private String ProjectName;
    private User[] partnerList;

    public Project() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getMatterId() {
        return MatterId;
    }

    public void setMatterId(String matterId) {
        MatterId = matterId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public User[] getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(User[] partnerList) {
        this.partnerList = partnerList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", ClientId='" + ClientId + '\'' +
                ", MatterId='" + MatterId + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", partnerList=" + Arrays.toString(partnerList) +
                '}';
    }
}
