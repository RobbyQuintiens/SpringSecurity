package be.pxl.app.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleConnectUser implements OAuth2User, AppAuthenticatedPrincipal {

    private String name;
    private String id;
    private String email;
    private List<GrantedAuthority> authorities =
            AuthorityUtils.createAuthorityList("ROLE_USER");
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap<String, Object>();
            this.attributes.put("id", this.getId());
            this.attributes.put("name", this.getName());
            this.attributes.put("email", this.getEmail());
            this.attributes.put("given_name", this.getName().split(" ")[0]);
            this.attributes.put("family_name", this.getName().split(" ")[1]);
        }
        return attributes;
    }


    @Override
    public String getFirstName() {
        return this.getAttributes().get("name").toString();
    }


    @Override
    public String getLastName() {
        return this.getAttributes().get("family_name").toString();
    }


    @Override
    public String getFirstAndLastName() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
