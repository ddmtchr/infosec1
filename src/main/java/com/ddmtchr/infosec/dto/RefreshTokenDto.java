package com.ddmtchr.infosec.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenDto {
    private String refreshToken;
}
