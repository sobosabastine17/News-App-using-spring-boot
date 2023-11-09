package com.example.OceanNews.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    @Override
    protected void doFilterInternal(
         @NonNull HttpServletRequest request,
         @NonNull  HttpServletResponse response,
         @NonNull  FilterChain filterChain
    ) throws ServletException, IOException {
      final String authorizationHeader = request.getHeader("Authorization");
      final String tokenPrefix = "Bearer ";
      final String jwt;
      final  String userEmail;
      if (authorizationHeader == null || !authorizationHeader.startsWith(tokenPrefix)) {
          filterChain .doFilter(request,response);
          return;
      }
        jwt = authorizationHeader.substring(tokenPrefix.length());
      userEmail = jwtTokenService.extractUserUsername(jwt);
    }
}
