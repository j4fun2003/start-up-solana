package com.startuptank.wbteam.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieUtil {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    public Cookie setCookie(String value, String name, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(hours * 60 * 60); // Fix the calculation
        response.addCookie(cookie);
        return cookie;
    }

    public Optional<String> getCookie(String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) { // Change "equals" to "equals"
                    return Optional.ofNullable(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }
}
