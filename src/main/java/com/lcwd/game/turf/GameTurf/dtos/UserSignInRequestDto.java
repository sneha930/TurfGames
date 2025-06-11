package com.lcwd.game.turf.GameTurf.dtos;

import jakarta.validation.constraints.*;

public class UserSignInRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public String emailId;

    @NotBlank(message = "Password is required")
    public String password;

    // Getters and setters
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
