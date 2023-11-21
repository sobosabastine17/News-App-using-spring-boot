//package com.example.OceanNews.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtTokenService jwtTokenService;
//    private final UserDetailsService userDetailsService;
//    @Override
//    protected void doFilterInternal(
//         @NonNull HttpServletRequest request,
//         @NonNull  HttpServletResponse response,
//         @NonNull  FilterChain filterChain
//    ) throws ServletException, IOException {
//      final String authorizationHeader = request.getHeader("Authorization");
//      final String tokenPrefix = "Bearer ";
//      final String jwt;
//      final  String userEmail;
//      if (authorizationHeader == null || !authorizationHeader.startsWith(tokenPrefix)) {
//          filterChain .doFilter(request,response);
//          return;
//      }
//        jwt = authorizationHeader.substring(tokenPrefix.length());
//      userEmail = jwtTokenService.extractUserUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             UserDetails userDetails =this.userDetailsService.loadUserByUsername(userEmail);
//            if (jwtTokenService.isValidToken(jwt,userDetails)) {
//                final UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//                        authToken.setDetails(
//                        new WebAuthenticationDetailsSource()/*.buildDetails(request)*/
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//}
