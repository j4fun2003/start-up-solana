package com.startuptank.wbteam.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieUtil {
    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpServletRequest request;

    public Cookie setCookie(String value, String name, int hours) {
        Cookie cookie = new Cookie(value, name);
        cookie.setMaxAge(hours *60*10);
        response.addCookie(cookie);
        return cookie;
    }

    public Optional<String> getCookie(String key) {
        return   Arrays.stream(request.getCookies())
                .filter(c -> equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
