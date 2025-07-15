package com.hirrao.health.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }
        var token = authHeader.replace("Bearer ", "");
        var user = jwtUtil.decodeTokenUser(token);
        var authentication = new UsernamePasswordAuthenticationToken(user, null,
                                                                     null);
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
