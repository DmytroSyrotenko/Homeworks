package com.example.config.security;

import com.example.persistence.entity.token.Token;
import com.example.persistence.repository.token.TokenRepository;
import com.example.util.SecurityUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        if (servletPath.contains("/api/open") || servletPath.contains("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authHeader.substring(7);
        final String login = jwtService.extractUserName(jwt);
        if (StringUtils.isNotBlank(login) && !SecurityUtil.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            Token token = tokenRepository
                    .findByToken(jwt)
                    .orElseThrow(() -> new RuntimeException("Invalid token"));
//            boolean isExpiredToken = token.getExpired();
            if (jwtService.isNotExpiredToken(jwt)) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityUtil.setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}