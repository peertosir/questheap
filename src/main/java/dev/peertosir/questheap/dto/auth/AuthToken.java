package dev.peertosir.questheap.dto.auth;

import lombok.Getter;


@Getter
public class AuthToken {
    private final String token;

    public AuthToken(String token) {
        this.token = token;
    }
}
