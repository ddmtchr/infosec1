package com.ddmtchr.infosec.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.ddmtchr.infosec.security.JwtProvider.*;

public class JwtUtil {

    public static Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER_STRING);
        return StringUtils.hasText(bearer) && bearer.startsWith(TOKEN_PREFIX) ?
                Optional.of(bearer.substring(7)) : Optional.empty();
    }

    public static String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public static String getLoginFromHttpRequest(HttpServletRequest httpServletRequest) {
        return getTokenFromRequest(httpServletRequest)
                .map(JwtUtil::getLoginFromToken)
                .orElse("");
    }
}
