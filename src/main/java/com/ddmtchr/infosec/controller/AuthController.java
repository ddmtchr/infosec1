package com.ddmtchr.infosec.controller;

import com.ddmtchr.infosec.dto.JwtResponseDto;
import com.ddmtchr.infosec.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtProvider jwtProvider;

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refreshToken(@RequestBody String refreshToken) {
        return jwtProvider.refreshTokens(refreshToken)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}


