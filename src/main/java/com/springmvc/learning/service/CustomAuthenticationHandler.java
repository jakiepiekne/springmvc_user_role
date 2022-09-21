package com.springmvc.learning.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        SimpleUrlAuthenticationSuccessHandler adminUrl =
                new SimpleUrlAuthenticationSuccessHandler("/admin");
        SimpleUrlAuthenticationSuccessHandler userUrl =
                new SimpleUrlAuthenticationSuccessHandler("/user");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String authorityName = authority.getAuthority();
            if (authorityName.equals("ROLE_ADMIN")) {
                adminUrl.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
            } else if (authorityName.equals("ROLE_USER")) {
                userUrl.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
            }
        }
    }
}
