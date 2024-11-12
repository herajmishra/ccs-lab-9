package com.heraj.lab_9.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordUtil {

    private SecureRandom random = new SecureRandom();

    private final String version = "$2a";

    private final int logRounds = 10;

    public String getSalt() {
        return this.random != null ? BCrypt.gensalt(version, logRounds, this.random) : BCrypt.gensalt(version, logRounds);
    }
}
