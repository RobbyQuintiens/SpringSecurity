package be.pxl.authserver.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
//    private String email;
    private String password;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean verified;
    @Column(name = "role_id")
    private String roleId;

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
//
//    @Column(name = "email")
//    public String getEmail() {
//        return email;
//    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
