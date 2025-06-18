package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.dtos.AddressDto;
import com.lcwd.game.turf.GameTurf.dtos.ContactDto;
import com.lcwd.game.turf.GameTurf.entities.Game;
import com.lcwd.game.turf.GameTurf.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class UserSignUpRequestDto {

    // --- User fields ---

    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public String emailId;

    @NotBlank(message = "Password is required")
    public String password;

    @NotNull(message = "Date of Birth is required")
    public LocalDate dob;

    @NotNull(message = "Role is required")
    public User.Role role;

    @Valid
    @NotNull(message = "Contact is required")
    public ContactDto contactDto;

    @Valid
    @NotNull(message = "Address is required")
    public AddressDto addressDto;


    // --- Optional Player fields ---
    public boolean isPlayer; // helper flag, set true if role is PLAYER or PLAYERADMIN

    // You can later add extra player fields here if needed
    @NotNull(message = "Please enter favourite games")
    public List<GameDto> favouriteGameDtos;

    public UserSignUpRequestDto() {
    }

    public UserSignUpRequestDto(String name, String emailId, String password, LocalDate dob, User.Role role, ContactDto contactDto, AddressDto addressDto, boolean isPlayer, List<GameDto> favouriteGameDtos) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.dob = dob;
        this.role = role;
        this.contactDto = contactDto;
        this.addressDto = addressDto;
        this.isPlayer = isPlayer;
        this.favouriteGameDtos = favouriteGameDtos;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public ContactDto getContactDto() {
        return contactDto;
    }

    public void setContactDto(ContactDto contactDto) {
        this.contactDto = contactDto;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public List<GameDto> getFavouriteGameDtos() {
        return favouriteGameDtos;
    }

    public void setFavouriteGameDtos(List<GameDto> favouriteGameDtos) {
        this.favouriteGameDtos = favouriteGameDtos;
    }

    @Override
    public String toString() {
        return "UserSignUpRequestDto{" +
                "name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", role=" + role +
                ", contactDto=" + contactDto +
                ", addressDto=" + addressDto +
                ", isPlayer=" + isPlayer +
                ", favouriteGameDtos=" + favouriteGameDtos +
                '}';
    }
}
