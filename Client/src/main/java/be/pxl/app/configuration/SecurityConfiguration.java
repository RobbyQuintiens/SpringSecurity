package be.pxl.app.configuration;

import be.pxl.app.handler.Oauth2AuthenticationSuccessHandler;
import be.pxl.app.services.AppOidcUserService;
import be.pxl.app.userdetails.FacebookConnectUser;
import be.pxl.app.userdetails.GoogleConnectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
                               OAuth2AuthorizedClientRepository authorizedClientRepository) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2FilterFunction =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                        clientRegistrationRepository, authorizedClientRepository);

        oauth2FilterFunction.setDefaultClientRegistrationId("yellowb");

        return WebClient.builder()
                .apply(oauth2FilterFunction.oauth2Configuration())
                .build();
    }

    @Autowired
    private Oauth2AuthenticationSuccessHandler oauthSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .requiresChannel().mvcMatchers("/login").requiresSecure()
//                .and()
                .oauth2Login()
                .successHandler(oauthSuccessHandler)
                .userInfoEndpoint()
                .customUserType(FacebookConnectUser.class, "facebook")
                .customUserType(GoogleConnectUser.class, "google")
                .oidcUserService(new AppOidcUserService())
                .and()
                .and()
                .authorizeRequests()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/team").hasAnyAuthority("SCOPE_admin", "SCOPE_team", "ROLE_USER")
                .mvcMatchers("/gedicht").hasAuthority("SCOPE_admin")
                .anyRequest().authenticated();
    }

    @Bean
    public RedirectStrategy getRedirectStrategy() {
        return new DefaultRedirectStrategy();
    }

}
