package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.User;

import java.time.LocalDate;

public class UserSignUpResponseDto {

    public String id;

    public String name;
    public String emailId;
    public LocalDate dob;
    public User.Role role;
    public ContactDto contact;
    public AddressDto address;
    public PlayerDto playerDto;
    public boolean isPlayer;

    public UserSignUpResponseDto() {
    }

    public UserSignUpResponseDto(String id, String name, String emailId, LocalDate dob, User.Role role, ContactDto contact, AddressDto address, PlayerDto playerDto, boolean isPlayer) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.dob = dob;
        this.role = role;
        this.contact = contact;
        this.address = address;
        this.playerDto = playerDto;
        this.isPlayer = isPlayer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    @Override
    public String toString() {
        return "UserSignUpResponseDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dob=" + dob +
                ", role=" + role +
                ", contact=" + contact +
                ", address=" + address +
                ", playerDto=" + playerDto +
                ", isPlayer=" + isPlayer +
                '}';
    }
}
