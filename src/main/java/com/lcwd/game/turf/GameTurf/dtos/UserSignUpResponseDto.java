package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.User;

import java.time.LocalDate;

public class UserSignUpResponseDto {
    private String id;
    private String name;
    private String emailId;
    private LocalDate dob;
    private User.Role role;
    private ContactDto contact;
    private AddressDto address;

    public UserSignUpResponseDto() {
    }

    public UserSignUpResponseDto(String id, String name, String emailId, LocalDate dob, User.Role role, ContactDto contact, AddressDto address) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.dob = dob;
        this.role = role;
        this.contact = contact;
        this.address = address;
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
}
