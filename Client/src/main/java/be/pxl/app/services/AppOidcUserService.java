package be.pxl.app.services;

import be.pxl.app.userdetails.AppOidcUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class AppOidcUserService extends OidcUserService {

    final OidcUserService delegate = new OidcUserService();

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        AppOidcUser user = new AppOidcUser(super.loadUser(userRequest));


        return user;
    }
}
