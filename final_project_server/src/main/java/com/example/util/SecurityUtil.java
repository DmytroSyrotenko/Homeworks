package com.example.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static boolean isAuthenticated() {
        return getAuthentication() != null;
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static void setAuthentication(UsernamePasswordAuthenticationToken upat){
        SecurityContextHolder.getContext().setAuthentication(upat);
    }

    public static String getUsername() {
        return getAuthentication().getName();
    }
}
