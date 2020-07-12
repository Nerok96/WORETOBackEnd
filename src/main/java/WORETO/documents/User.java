package WORETO.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Arrays;

@Document
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String name;
    private String surname;
    private Boolean enable;
    private String password;
    private Role[] roles;

    public User(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", enable=" + enable +
                ", password='" + password + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }

    public static class Builder {
        private User user;

        private Builder() {
            this.user = new User();
        }

        public Builder id(String id){
            this.user.id = id;
            return this;
        }

        public Builder id(Long id){
            this.user.id = ""+id;
            return this;
        }

        public Builder email(String email){
            this.user.email = email;
            return this;
        }

        public Builder name(String name){
            this.user.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.user.surname = surname;
            return this;
        }

        public Builder enable(Boolean enable){
            this.user.enable = enable;
            return this;
        }

        public Builder password(String password){
            this.user.password = password;
            return this;
        }

        public Builder roles(Role... roles) {
            this.user.roles = roles;
            return this;
        }

        public User build() {
            return this.user;
        }
    }

}
