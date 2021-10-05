package be.pxl.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public ClientController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping
    public Map<String, Object> clientInfo(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient("yellowb", authentication.getName());

        return Map.of(
                "accessToken", authorizedClient.getAccessToken(),
                "principalName", authorizedClient.getPrincipalName(),
                "authorities", authentication.getAuthorities(),
                "clientRegistration", authorizedClient.getClientRegistration()
        );
    }
}
