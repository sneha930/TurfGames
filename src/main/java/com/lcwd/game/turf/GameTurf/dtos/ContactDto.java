package com.lcwd.game.turf.GameTurf.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactDto {

    public String id;

    @NotBlank(message = "Primary contact is required")
    @Pattern(regexp = "\\d{10}", message = "Primary contact must be a valid 10-digit number")
    public String primaryContact;

    @Pattern(regexp = "\\d{10}", message = "Home contact must be a valid 10-digit number")
    public String homeContact;

    @Pattern(regexp = "\\d{10}", message = "Emergency contact must be a valid 10-digit number")
    public String emergencyContact;

    public ContactDto(String id, String primaryContact, String homeContact, String emergencyContact) {
        this.id = id;
        this.primaryContact = primaryContact;
        this.homeContact = homeContact;
        this.emergencyContact = emergencyContact;
    }

    public ContactDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getHomeContact() {
        return homeContact;
    }

    public void setHomeContact(String homeContact) {
        this.homeContact = homeContact;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    // ✅ MANUAL BUILDER CLASS
    public static class Builder {
        private String id;
        private String primaryContact;
        private String homeContact;
        private String emergencyContact;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder primaryContact(String primaryContact) {
            this.primaryContact = primaryContact;
            return this;
        }

        public Builder homeContact(String homeContact) {
            this.homeContact = homeContact;
            return this;
        }

        public Builder emergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
            return this;
        }

        public ContactDto build() {
            return new ContactDto(id, primaryContact, homeContact, emergencyContact);
        }
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "id=" + id +
                ", primaryContact='" + primaryContact + '\'' +
                ", homeContact='" + homeContact + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                '}';
    }
}
