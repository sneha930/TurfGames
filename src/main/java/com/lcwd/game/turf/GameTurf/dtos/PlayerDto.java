package com.lcwd.game.turf.GameTurf.dtos;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PlayerDto {

    public String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    public String name;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of Birth must be in the past")
    public LocalDate dob;

    public ContactDto contact;

    public AddressDto address;

    public PlayerDto(String id, String name, LocalDate dob, ContactDto contact, AddressDto address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
    }

    public PlayerDto() {
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    // ✅ MANUAL BUILDER CLASS
    public static class Builder {
        private String id;
        private String name;
        private LocalDate dob;
        private ContactDto contact;
        private AddressDto address;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder contact(ContactDto contact) {
            this.contact = contact;
            return this;
        }

        public Builder address(AddressDto address) {
            this.address = address;
            return this;
        }

        public PlayerDto build() {
            return new PlayerDto(id, name, dob, contact, address);
        }
    }


    @Override
    public String toString() {
        return "PlayerDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", contact=" + contact +
                ", address=" + address +
                '}';
    }
}