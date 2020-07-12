package WORETO.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Arrays;

public class Project {

    @Transient
    public static final String SEQUENCE_NAME = "projects_sequence";

    @Id
    private String id;
    private String clientId;
    private String matterId;
    private String projectName;
    private User[] partnerList;

    public Project() {}

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdFromLong(Long id) {
        this.id = "" + id;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMatterId() {
        return matterId;
    }

    public void setMatterId(String matterId) {
        this.matterId = matterId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
                ", ClientId='" + clientId + '\'' +
                ", MatterId='" + matterId + '\'' +
                ", ProjectName='" + projectName + '\'' +
                ", partnerList=" + Arrays.toString(partnerList) +
                '}';
    }

    public static class Builder {
        private Project project;

        private Builder() {
            this.project = new Project();
        }

        public Builder id(String id){
            this.project.id = id;
            return this;
        }

        public Builder id(Long id){
            this.project.id = ""+id;
            return this;
        }

        public Builder clientId(String clientId){
            this.project.clientId = clientId;
            return this;
        }

        public Builder matterId(String matterId){
            this.project.matterId = matterId;
            return this;
        }

        public Builder projectName(String projectName){
            this.project.projectName = projectName;
            return this;
        }

        public Builder partnerList(User... partnerList){
            this.project.partnerList = partnerList;
            return this;
        }

        public Project build() {
            return this.project;
        }
    }
}
