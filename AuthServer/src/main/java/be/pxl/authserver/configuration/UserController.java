package be.pxl.authserver.configuration;

import be.pxl.authserver.details.MFAUser;
import be.pxl.authserver.details.UserInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/userinfo")
    public UserInfo user(@AuthenticationPrincipal MFAUser principal) {
        return new UserInfo(principal.getUsername(), principal.getFirstName(), principal.getLastName(), principal.getEmail(),
                principal.getAuthorities());
    }

}
