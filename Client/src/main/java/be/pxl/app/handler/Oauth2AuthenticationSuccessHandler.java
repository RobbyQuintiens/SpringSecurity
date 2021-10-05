package be.pxl.app.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private final RedirectStrategy redirectStrategy;

	public Oauth2AuthenticationSuccessHandler(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		this.redirectStrategy.sendRedirect(request, response, "/main");
	}

}
