package com.nexjot.nexjot.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenStorageService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final long TOKEN_EXPIRY_TIME = 15;

    @Autowired
    public TokenStorageService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeEmailVerificationToken(String email, String token) {
        // Store email -> token mapping
        redisTemplate.opsForValue().set(
                "email:" + email,
                token,
                TOKEN_EXPIRY_TIME,
                TimeUnit.MINUTES
        );

        // Store token -> email mapping
        redisTemplate.opsForValue().set(
                "token:" + token,
                email,
                TOKEN_EXPIRY_TIME,
                TimeUnit.MINUTES
        );
    }

    public String getEmailByToken(String token) {
        return redisTemplate.opsForValue().get("token:" + token);
    }

    public String getTokenByEmail(String email) {
        return redisTemplate.opsForValue().get("email:" + email);
    }

    public void removeToken(String token) {
        String email = getEmailByToken(token);

        if (email != null) {
            redisTemplate.delete("email_token:" + token);
            redisTemplate.delete("email:" + email);
        }

    }
}
