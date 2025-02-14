package com.yulun.library.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {

    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
