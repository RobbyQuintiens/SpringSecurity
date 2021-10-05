package be.pxl.authserver.details;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserInfo {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserInfo(String username, String firstName, String lastName, String email, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
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

    public String getEmail() {
        return email;
    }

    public String getFirstAndLastName() {
        return firstName + " " + lastName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
