package com.kolej.bartosz.zalrest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
class RestAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expTime;
    private final String secret;

    RestAuthSuccessHandler(@Value("${jwt.expTime}") long expTime, @Value("${jwt.secret}") String secret) {
        this.expTime = expTime;
        this.secret = secret;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess");
        String token = JWT.create()
                .withSubject(authentication.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
    }
}
