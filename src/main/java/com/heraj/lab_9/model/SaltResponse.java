package com.heraj.lab_9.model;

public class SaltResponse {
    private String salt;

    public SaltResponse(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
